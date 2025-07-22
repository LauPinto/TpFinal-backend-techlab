package com.talentotech.tpfinal.dtos;

import java.util.List;

public class PedidoRequest {
    private List<Long> productoIds;
    private List<Integer> cantidades;

    public PedidoRequest() {}

    public List<Long> getProductoIds() { return productoIds; }

    public void setProductoIds(List<Long> productoIds) { this.productoIds = productoIds; }

    public List<Integer> getCantidades() { return cantidades; }

    public void setCantidades(List<Integer> cantidades) { this.cantidades = cantidades; }
}