package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {
    //llamar al repositorio
    @Autowired
    private IProductoRepositorio  repositorio;

    //llamar al mapa
    @Autowired
    private IProductoMapa mapa;

    //activar el servicio de guardado de productos
    public ProductoDTO guardarProductoDTO (Producto datosDelProducto){
        //verificar que la categoria sea seleccioanda
        if (datosDelProducto.getCategotia()==null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "la categoria es obligatoria"
            );
        }
        //intentar guardar
        Producto guardad_producto= this.repositorio.save(datosDelProducto);
        if (guardad_producto==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error al guardar el usuario en la base de datos"
            );
        }

        //retorar el dto al controlador
        return this.mapa.convertir_producto_a_productoDTO(guardad_producto);
    }
    //buscar todos los productos
    public List<ProductoDTO> BuscarAllProductos(){
        List<Producto> ListaProductos = this.repositorio.findAll();
        return this.mapa.convertirList_a_ListDTO(ListaProductos);
    }

    //Buscar un producto por id
    public ProductoDTO buscarProducto(Integer id){
        Optional<Producto> productoOptional= this.repositorio.findById(id);
        if (!productoOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos asociados a el id ingresado"
            );
        }
        Producto productoEncontrado= productoOptional.get();
        return this.mapa.convertir_producto_a_productoDTO(productoEncontrado);
    }
    //eliminar producto
    public void eliminarProducto (Integer id){
        Optional<Producto> optionalProducto= this.repositorio.findById(id);
        if (!optionalProducto.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos asociados a el id ingresado"
            );
        }
        Producto productoBorrar= optionalProducto.get();
        try {
            this.repositorio.delete(productoBorrar);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se puedo borrar, "+ error.getMessage()
            );
        }
    }
    //Actulizar algunos datos
    public ProductoDTO actualizarProducto (Integer id, Producto datosProducto){
        Optional<Producto> productoOptional= this.repositorio.findById(id);
        if (!productoOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos con el id ingresado"
            );
        }
        Producto productoEncontrado= productoOptional.get();
        //datos a actualizar
        productoEncontrado.setDescripción(datosProducto.getDescripción());
        productoEncontrado.setFotografia(datosProducto.getFotografia());

        //validar que se haya actualizado
        Producto productoActualizado= this.repositorio.save(productoEncontrado);
        if (productoActualizado == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo actualizar, intenta más tarde");
        }
        return  this.mapa.convertir_producto_a_productoDTO(productoActualizado);
    }

}
