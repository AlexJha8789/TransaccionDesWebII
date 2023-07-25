package com.hipermaxi.repository;


import com.hipermaxi.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle,Long> {

    public List<PedidoDetalle> getPedidoDetalleByPedidoId(Long pedido_id);
}
