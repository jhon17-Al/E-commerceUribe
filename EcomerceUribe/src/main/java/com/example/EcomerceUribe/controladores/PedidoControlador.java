package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoControlador {
    @Autowired
    PedidoServicio servicio;

    //guardar
    @Operation(summary = "crear pedido en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoDTO> guardar (@RequestBody Pedido datos){
        PedidoDTO respuesta =this.servicio.guaradar_pedidoDTO(datos);
        return  ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    //buscarTodo
    @Operation (summary = "Listar todo los pepidos")
    @GetMapping(produces = "aplication/json")
    public ResponseEntity<List<PedidoDTO>> buscarTodo (){
        List<PedidoDTO> respuesta= this.servicio.BuasacrPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //bsucar//id
    @Operation(summary = "Buscar por id")
    @GetMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<PedidoDTO> buscarPorID (@PathVariable Integer id){
        PedidoDTO respuesta= this.servicio.buscarPedidoId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //eliminar
    @Operation(summary = "eliminar pedidos")
    @DeleteMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<PedidoDTO> eliminar(@PathVariable Integer id){
        this.servicio.PedidoEliminar(id);
        return ResponseEntity.noContent().build();
    }


    //modificar
    @Operation(summary=" Modificar un pedido")
    @PutMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<PedidoDTO> modificar(@PathVariable Integer id, @RequestBody Pedido datos) {
        PedidoDTO respuesta=this.servicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
