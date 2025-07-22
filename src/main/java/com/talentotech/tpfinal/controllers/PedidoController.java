package com.talentotech.tpfinal.controllers;

import com.talentotech.tpfinal.dtos.PedidoRequest;
import com.talentotech.tpfinal.dtos.PedidoResponse;
import com.talentotech.tpfinal.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PedidoRequest request) {
        try {
            PedidoResponse nuevo = service.crearPedido(request);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            String mensaje = "No se pudo crear el pedido. No existe el producto.";
            return ResponseEntity.badRequest().body(mensaje);
        }
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return service.listarPedidos(); 
    }
}