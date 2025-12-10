package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoControlador {
    @Autowired
    ProductoServicio servicio;
    //guardar
    @Operation(summary = "crear un producto BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoDTO> guardar (@RequestBody Producto datos){
        ProductoDTO respuesta =this.servicio.guardarProductoDTO(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listarTodo
    @Operation(summary = "Listar todos los productos")
    @GetMapping(produces = "aplication/json")
    public ResponseEntity<List<ProductoDTO>> listarTodo(){
        List<ProductoDTO> respuesta= this.servicio.BuscarAllProductos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscarPorId
    @Operation(summary = "Buscar por Id")
    @GetMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Integer id){
        ProductoDTO respuesta= this.servicio.buscarProducto(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "eliminar un producto")
    @DeleteMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<Void> eliminar (@PathVariable Integer id){
        this.servicio.eliminarProducto(id);
        return  ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "modificar un producto")
    @PutMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<ProductoDTO> modificar(@PathVariable Integer id, @RequestBody Producto datos){
        ProductoDTO respuesta= this.servicio.actualizarProducto(id,datos);
        return  ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
