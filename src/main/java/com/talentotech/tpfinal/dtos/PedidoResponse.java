package com.talentotech.tpfinal.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {

    private Long id;
    private LocalDateTime fechaCreacion;
    private List<LineaPedidoResponse> lineas;
    private double total;

    public PedidoResponse() {}

    public PedidoResponse(Long id, LocalDateTime fechaCreacion, List<LineaPedidoResponse> lineas, double total) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.lineas = lineas;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<LineaPedidoResponse> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoResponse> lineas) {
        this.lineas = lineas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static class LineaPedidoResponse {
        private Long id;
        private ProductoResponse producto;
        private int cantidad;
        private double subtotal;

        public LineaPedidoResponse() {}

        public LineaPedidoResponse(Long id, ProductoResponse producto, int cantidad, double subtotal) {
            this.id = id;
            this.producto = producto;
            this.cantidad = cantidad;
            this.subtotal = subtotal;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public ProductoResponse getProducto() {
            return producto;
        }

        public void setProducto(ProductoResponse producto) {
            this.producto = producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(double subtotal) {
            this.subtotal = subtotal;
        }
    }

    public static class ProductoResponse {
        private Long id;
        private String nombre;
        private String descripcion;
        private double precio;

        public ProductoResponse() {}

        public ProductoResponse(Long id, String nombre, String descripcion, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }
    }
}
