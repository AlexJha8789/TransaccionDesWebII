package com.hipermaxi.dtos;


import com.hipermaxi.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private Date fechaPedido;
    private Cliente cliente;
    private List<PedidoDetalleDTO> pedidoDetalleDTO;


}
