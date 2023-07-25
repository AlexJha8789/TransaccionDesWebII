package com.hipermaxi.dtos;

import com.hipermaxi.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoDetalleDTO {

    private Long id;
    private Producto producto;
    private Long cantidad;
    private BigDecimal preciovta;
    private BigDecimal importe;
}
