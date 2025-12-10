package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empleado")
public class EmpleadoControlador {
    @Autowired
    EmpleadoServicio servicio;

    //guardar
    @Operation(summary = "Crear un empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoDTO> guardar (@RequestBody Empleado datos){
        EmpleadoDTO respuesta=this.servicio.guardarEmpleadoDTO(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }


    //buscarTodo
    @Operation(summary = "listar todo")
    @GetMapping(produces = "aplication/json")
    public ResponseEntity<List<EmpleadoDTO>> buscarTodo (){
        List<EmpleadoDTO> respuesta = this.servicio.BuscarAllEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //bsucar//id
    @Operation(summary = "Buscar empleado por id")
    @GetMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<EmpleadoDTO> buscarId (@PathVariable Integer id){
        EmpleadoDTO repuesta= this.servicio.BucarEmpleadoId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(repuesta);
    }


    //eliminar
    @Operation(summary = "Eliminar empleado")
    @DeleteMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<EmpleadoDTO> eliminar (@PathVariable Integer id){
        this.servicio.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "modificar empleado")
    @PutMapping (value = "/{id}", produces = "aplication/json")
    public ResponseEntity<EmpleadoDTO> modificar (@PathVariable Integer id, @RequestBody Empleado datos){
        EmpleadoDTO respueata=this.servicio.actualizarEmpleado(id, datos);
        return  ResponseEntity.status(HttpStatus.OK).body(respueata);
    }

}
