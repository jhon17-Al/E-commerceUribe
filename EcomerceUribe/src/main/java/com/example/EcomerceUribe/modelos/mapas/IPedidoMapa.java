package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface IPedidoMapa {
    //Transformar modelo a DTO
    @Mapping(source = "id", target="id")
    @Mapping(source = "fechaEntrega", target="fechaEntrega")
    @Mapping(source = "costoEnvio", target="costoEnvio")
    PedidoDTO convertirModelo_a_DTO (Pedido pedido);


    //devolver lista DTO de la lista Modelo
    List <PedidoDTO> convertirList_a_ListDTO (List<Pedido> lista);
}
