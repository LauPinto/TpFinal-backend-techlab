package com.talentotech.tpfinal.services;

import com.talentotech.tpfinal.entities.Producto;
import com.talentotech.tpfinal.repositories.LineaPedidoRepository;
import com.talentotech.tpfinal.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repo;
    private final LineaPedidoRepository pedidoRepo;

    public ProductoService(ProductoRepository repo, LineaPedidoRepository pedidoRepo) {
        this.repo = repo;
        this.pedidoRepo = pedidoRepo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Producto buscarPorNombre(String nombre) {
        return repo.findByNombreIgnoreCase(nombre).orElse(null);
    }

    public Producto crear(Producto producto) {
        return repo.save(producto);
    }

    public Producto actualizar(Long id, Producto actualizado) {
        Optional<Producto> existenteOpt = repo.findById(id);
        if (existenteOpt.isPresent()) {
            Producto existente = existenteOpt.get();
            existente.setNombre(actualizado.getNombre());
            existente.setDescripcion(actualizado.getDescripcion());
            existente.setPrecio(actualizado.getPrecio());
            existente.setCategoria(actualizado.getCategoria());
            existente.setimagen(actualizado.getimagen());
            existente.setStock(actualizado.getStock());
            return repo.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        long pedidosConProducto = pedidoRepo.countByProductoId(id);
        if (pedidosConProducto > 0) {
            throw new IllegalStateException("No se puede eliminar el producto porque tiene pedidos asociados");
        }
        repo.deleteById(id);
    }

    public void actualizarStock(Long id, int nuevoStock) {
        if (nuevoStock < 0) throw new IllegalArgumentException("El stock no puede ser negativo");
        Producto p = obtenerPorId(id);
        if (p != null) {
            p.setStock(nuevoStock);
            repo.save(p);
        }
    }
}
