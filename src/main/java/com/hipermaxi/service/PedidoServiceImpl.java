package com.hipermaxi.service;

import com.hipermaxi.dtos.PedidoCreateDTO;
import com.hipermaxi.dtos.PedidoDTO;
import com.hipermaxi.dtos.PedidoDetalleCreateDTO;
import com.hipermaxi.dtos.PedidoDetalleDTO;
import com.hipermaxi.mappers.PedidoDetalleMapper;
import com.hipermaxi.mappers.PedidoMapper;
import com.hipermaxi.model.Pedido;
import com.hipermaxi.model.PedidoDetalle;
import com.hipermaxi.repository.PedidoDetalleRepository;
import com.hipermaxi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;


    @Override
    public List<PedidoDTO> listarPedidos() {
        List<PedidoDTO> lista= PedidoMapper.instancia.listaPedidoAListaPedidoDTO(  pedidoRepository.findAll()  );
        for ( PedidoDTO pedidoDTO: lista  ) {
            List<PedidoDetalleDTO> pedidoDetalleDTOs = PedidoDetalleMapper.instancia.listaPedidoDetalleAListaPedidoDetalleDTO(
                    pedidoDetalleRepository.getPedidoDetalleByPedidoId(pedidoDTO.getId()));

            pedidoDTO.setPedidoDetalleDTO(pedidoDetalleDTOs);
        }
        return lista;
    }


    @Override
    public PedidoDTO obtenerPedidoPorID(long id) {

        Optional<Pedido> pedido= pedidoRepository.findById(id);
        PedidoDTO pedidoDTO= null;
        if ( pedido.isPresent() ){
            pedidoDTO= PedidoMapper.instancia.pedidoAPedidoDTO(pedido.get());
            pedidoDTO.setPedidoDetalleDTO(
                    PedidoDetalleMapper.instancia.listaPedidoDetalleAListaPedidoDetalleDTO( pedidoDetalleRepository.getPedidoDetalleByPedidoId(pedido.get().getId()))
            );
        }
        return pedidoDTO;
    }



    @Override
    @Transactional
    public PedidoDTO registrarPedido(PedidoCreateDTO pedidoCreateDTO) {
        //Registro la cabecera
        Pedido pedido= PedidoMapper.instancia.pedidoCreateDTOAPedido(pedidoCreateDTO);
        Pedido respuestaEntity= pedidoRepository.save(pedido);

        //Registrando el detalle
        for (PedidoDetalleCreateDTO pedDetalleCreateDTO : pedidoCreateDTO.getPedidoDetalleCreateDTO()) {
            PedidoDetalle pd= PedidoDetalleMapper.instancia.pedidoDetalleCreateDTOAPedidoDetalle(pedDetalleCreateDTO);
            pd.setPedido(respuestaEntity);
            pedidoDetalleRepository.save(pd);
        }

        //armar la respuesta DTO
        PedidoDTO respuestaDTO = PedidoMapper.instancia.pedidoAPedidoDTO(  pedidoRepository.getById( respuestaEntity.getId() ) );
        respuestaDTO.setPedidoDetalleDTO(
                PedidoDetalleMapper.instancia.listaPedidoDetalleAListaPedidoDetalleDTO( pedidoDetalleRepository.getPedidoDetalleByPedidoId(respuestaEntity.getId()))
        );
        return respuestaDTO;

    }
}




















