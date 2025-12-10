package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapa {

    //convertir el modelo a DTO
    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "fotografia", target = "fotografia")
    @Mapping(source = "descripción", target = "descripción")
    @Mapping(source = "precioUnitario", target = "precioUnitario")
    @Mapping(source = "marca", target = "marca")
    ProductoDTO convertir_producto_a_productoDTO (Producto producto);

    //volver una Lista<modelo> a List<dto>
    List<ProductoDTO> convertirList_a_ListDTO (List<Producto> Lista);
}
