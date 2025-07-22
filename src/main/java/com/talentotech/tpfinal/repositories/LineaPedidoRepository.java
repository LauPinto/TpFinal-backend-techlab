package com.talentotech.tpfinal.repositories;

import com.talentotech.tpfinal.entities.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, Long> {
    long countByProductoId(Long productoId);
}