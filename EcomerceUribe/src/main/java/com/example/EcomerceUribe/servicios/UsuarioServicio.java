package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    private IUsuarioRepositorio repositorio;
    @Autowired
    private IUsuarioMapa mapa;

    //declaro funciones para activar los servicios disponibles del API
     //ACTIVAR EL SERVICIO DE GUARDADO
    public UsuarioGenericoDTO guardarUsuarioGenerico(Usuario datosUsuario){
        //validación de correo replicado
        if (this.repositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un usuario registrado con el correo ingresado"
            );
        }

        //validar de que el nombre no esta vacio
        if (datosUsuario.getNombres()==null || datosUsuario.getNombres().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "el nombre del usuario es obligatorio"
            );
        }


        //validación de que la contraseña cumple con lo mimimo
        if (datosUsuario.getContrasena().length()<6){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "la contraseña debe tener al menos 6 caracteres"
            );
        }

        //intentar guardar el usuario
        Usuario usuarioQueGuardaElRepo=this.repositorio.save(datosUsuario);
        if (usuarioQueGuardaElRepo==null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error al guardar el usuario en la base de datos"
            );
        }

        //retornar el dto al controlador.
        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioQueGuardaElRepo);
    }
    //Buscar todos los usuarios List<>
    public List<UsuarioGenericoDTO> BuscarTodosLosUsuarios (){
        List<Usuario> listaUsuariosConsultados=this.repositorio.findAll();
        return this.mapa.convertir_lista_a_listadtogenerico(listaUsuariosConsultados);
    }

    //Buscar un usuario por id
    public UsuarioGenericoDTO BuscarUsurioId (Integer id){
        Optional<Usuario> UsuarioQueEstoyBuscando = this.repositorio.findById(id);
        if (!UsuarioQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro usuario con el id "+id+" suministrado"
            );
        }
        Usuario usuarioEncontrado= UsuarioQueEstoyBuscando.get();
        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioEncontrado);
    }

    //Eliminar usuario
    public void eliminarUsuario(Integer id){
        Optional<Usuario> usuarioOptional=this.repositorio.findById(id);
        if (!usuarioOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay usuarios relacionado a ese id"
            );
        }
        Usuario usuarioEncontrado=usuarioOptional.get();
        try {
            this.repositorio.delete(usuarioEncontrado);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo elimina, "+error.getMessage()
            );
        }
    }
    //Editar algunos datos del usuario
    public UsuarioGenericoDTO actualizarUsuario (Integer id, Usuario usuarioActualizado){
        Optional<Usuario> usuarioOptional= this.repositorio.findById(id);
        if (!usuarioOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay usuarios relacionado a ese id"
            );
        }
        Usuario usuarioEncontrado= usuarioOptional.get();
        //Validaciones van aca.

        //datos que se pueden actualizar.
        usuarioEncontrado.setNombres(usuarioActualizado.getNombres());
        usuarioEncontrado.setCorreo(usuarioActualizado.getCorreo());
        //ACTULIZAR LA BASE DE DATOS

        Usuario usuarioModificado = this.repositorio.save(usuarioEncontrado);
        if (usuarioModificado== null){
            throw  new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error al actualizar en la base de datos, intenta más tarde"
            );
        }
        return  this.mapa.convertir_usuario_a_usuariogenericodto(usuarioModificado);

    }
}
