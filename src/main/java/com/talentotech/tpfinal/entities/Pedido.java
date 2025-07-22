package com.talentotech.tpfinal.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaPedido> lineas = new ArrayList<>();

    private LocalDateTime fechaCreacion;

    public Pedido() {
        this.fechaCreacion = LocalDateTime.now();
    }
    public Long getId() { return id; }

    public List<LineaPedido> getLineas() { return lineas; }


    public void agregarLinea(LineaPedido linea) {
        this.lineas.add(linea);
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaPedido::calcularSubtotal).sum();
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
