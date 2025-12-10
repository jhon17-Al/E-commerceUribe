package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
public class ClienteControlador {
    @Autowired
    ClienteServicio servicio;

    //guardar
    @Operation(summary = "Crear un cliente en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteDTO> guardar (@RequestBody Cliente datos){
        ClienteDTO respuesta= this.servicio.guardarUsuarioDTO(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    //buscarTodo
    @Operation(summary = "Listar todos los clientes")
    @GetMapping(produces = "aplication/json")
    public ResponseEntity <List<ClienteDTO>> busacarTodo (){
        List<ClienteDTO> respuesta= this.servicio.BuscarAllClientes();
        return  ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //bsucar//id
    @Operation( summary = "Bucsar por Id")
    @GetMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<ClienteDTO> buscarId (@PathVariable Integer id){
        ClienteDTO respuesta= this.servicio.buscarCLienteId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //eliminar
    @Operation(summary = "elimminar cliente")
    @DeleteMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<ClienteDTO> eliminar (@PathVariable Integer id){
        this.servicio.eliminarCliente(id);
        return  ResponseEntity.noContent().build();
    }


    //modificar
    @Operation(summary = "actualizar cliente")
    @PutMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<ClienteDTO> modificar (@PathVariable Integer id, @RequestBody Cliente datos){
        ClienteDTO respuesta= this.servicio.actualizarCliente(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

}
