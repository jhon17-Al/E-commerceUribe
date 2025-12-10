package com.example.EcomerceUribe.controladores;


import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name="Controlador para operaciones en la tabla usuarios")
public class UsuarioControlador {
    @Autowired
    UsuarioServicio servicio;
    //guardar
    @Operation(summary = "Crear un usuario en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<UsuarioGenericoDTO> guardar (@RequestBody Usuario datos){
        UsuarioGenericoDTO respuesta=this.servicio.guardarUsuarioGenerico(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listarTodo
    @Operation(summary = "Listar todos los usuarios")
    @GetMapping(produces = "aplication/json")
    public ResponseEntity<List<UsuarioGenericoDTO>>listar (){
        List<UsuarioGenericoDTO> respuesta = this.servicio.BuscarTodosLosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscarPorId
    @Operation(summary = "Buscar por Id")
    @GetMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<UsuarioGenericoDTO> buscarPorID (@PathVariable Integer id){
        UsuarioGenericoDTO respuesta = this.servicio.BuscarUsurioId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "Eliminar por Id")
    @DeleteMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<Void> eliminar (@PathVariable Integer id){
        this.servicio.eliminarUsuario(id);
    return ResponseEntity.noContent().build();
    }

    //Modificar
    @Operation(summary = "Crear un usuario en la BD")
    @PutMapping(value = "/{id}", produces = "aplication/json")
    public ResponseEntity<UsuarioGenericoDTO> modificar (@PathVariable Integer id, @RequestBody Usuario datos){
        UsuarioGenericoDTO respuesta=this.servicio.actualizarUsuario(id,datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
