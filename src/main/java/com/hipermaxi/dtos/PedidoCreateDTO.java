package com.hipermaxi.dtos;

import com.hipermaxi.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoCreateDTO {

    private Date fechaPedido;
    private Cliente cliente;
    private List<PedidoDetalleCreateDTO> pedidoDetalleCreateDTO;
}
