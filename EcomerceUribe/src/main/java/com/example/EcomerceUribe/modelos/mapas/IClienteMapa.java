package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface   IClienteMapa {
    //convertie modelo a dto
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source="calificacion",target = "calificacion")
    @Mapping(source="ciudad", target="ciudad")
    ClienteDTO convertirModelo_a_Dto (Cliente cliente);

    //convertir una List<modelo> a Lista<dto>
    List<ClienteDTO> convertirListaModelo_a_listaDto (List<Cliente> lista);
}
