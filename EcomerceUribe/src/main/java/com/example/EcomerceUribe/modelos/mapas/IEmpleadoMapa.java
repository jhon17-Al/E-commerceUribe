package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapa {

    //convertir modelo a DTO
    @Mapping(source="id", target="id")
    @Mapping(source="sede", target="sede")
    EmpleadoDTO convertirModelo_a_DTO (Empleado empleado);

    //transformar una listModelo a ListDTO
    List<EmpleadoDTO> convertirList_a_ListDTO (List<Empleado> lista);
}
