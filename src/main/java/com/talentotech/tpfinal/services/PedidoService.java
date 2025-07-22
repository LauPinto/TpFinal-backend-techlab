package com.talentotech.tpfinal.services;

import com.talentotech.tpfinal.dtos.PedidoRequest;
import com.talentotech.tpfinal.dtos.PedidoResponse;
import com.talentotech.tpfinal.entities.LineaPedido;
import com.talentotech.tpfinal.entities.Pedido;
import com.talentotech.tpfinal.entities.Producto;
import com.talentotech.tpfinal.repositories.PedidoRepository;
import com.talentotech.tpfinal.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProductoRepository productoRepo;

    public PedidoService(PedidoRepository pedidoRepo, ProductoRepository productoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.productoRepo = productoRepo;
    }

    public PedidoResponse crearPedido(PedidoRequest request) {
        List<Long> ids = request.getProductoIds();
        List<Integer> cantidades = request.getCantidades();

        if (ids.size() != cantidades.size()) {
            throw new IllegalArgumentException("La cantidad de productos y cantidades no coincide.");
        }

        Pedido pedido = new Pedido();
        pedido.setFechaCreacion(LocalDateTime.now()); // Seteo fecha de creación

        for (int i = 0; i < ids.size(); i++) {
            Long idProducto = ids.get(i);
            int cantidad = cantidades.get(i);

            Producto producto = productoRepo.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("Producto con ID " + idProducto + " no encontrado"));

            if (producto.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - cantidad);
            productoRepo.save(producto);

            LineaPedido linea = new LineaPedido(producto, cantidad);
            pedido.agregarLinea(linea);
        }

        Pedido pedidoGuardado = pedidoRepo.save(pedido);
        return mapToPedidoResponse(pedidoGuardado);
    }

    public List<PedidoResponse> listarPedidos() {
        return pedidoRepo.findAll().stream()
                .map(this::mapToPedidoResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse mapToPedidoResponse(Pedido pedido) {
        List<PedidoResponse.LineaPedidoResponse> lineasResponse = pedido.getLineas().stream()
                .map(linea -> {
                    Producto producto = linea.getProducto();
                    PedidoResponse.ProductoResponse productoResp = new PedidoResponse.ProductoResponse(
                            producto.getId(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            producto.getPrecio()
                    );
                    return new PedidoResponse.LineaPedidoResponse(
                            linea.getId(),
                            productoResp,
                            linea.getCantidad(),
                            linea.calcularSubtotal()
                    );
                })
                .collect(Collectors.toList());

        return new PedidoResponse(
                pedido.getId(),
                pedido.getFechaCreacion(),
                lineasResponse,
                pedido.calcularTotal()
        );
    }
}
