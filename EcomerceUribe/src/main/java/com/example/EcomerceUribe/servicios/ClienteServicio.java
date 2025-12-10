package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {
    @Autowired
    private IClienteRepositorio repositorio;
    @Autowired
    private IClienteMapa mapa;

    //Activar el servicio
    public ClienteDTO guardarUsuarioDTO(Cliente datosCliente){
            //validación
        if (datosCliente.getCalificacion()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Estamos comprometidos con mejorar. Es necesaria la calificación"
            );
        }
        //intentar guardar en el repo
        Cliente guardarClienteRepo = this.repositorio.save(datosCliente);
            if (guardarClienteRepo==null){
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Lo sentimos, ocurrio un error al guardar. Intenta luego"
                );
            }

            //retornar dto
            return this.mapa.convertirModelo_a_Dto(guardarClienteRepo);
    }
    //buscar todos los clientes List<>
    public List<ClienteDTO> BuscarAllClientes (){
        List<Cliente> ListaDeClientes=this.repositorio.findAll();
        return this.mapa.convertirListaModelo_a_listaDto(ListaDeClientes);
    }

    //buscar cliente por id
    public ClienteDTO buscarCLienteId(Integer id){
        Optional<Cliente> ClienteOption= this.repositorio.findById(id);
        if (!ClienteOption.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El id ingresado no coincide con ningún cliente registrado");

        }
        Cliente clienteEncontrado=ClienteOption.get();
        return  this.mapa.convertirModelo_a_Dto(clienteEncontrado);
    }
    //eliminar cliente
    public void eliminarCliente (Integer id){
        Optional<Cliente> optionalCliente=this.repositorio.findById(id);
        if (!optionalCliente.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El id ingresado no coincide con ningún cliente registrado"
            );
        }
        Cliente clienteEncontrado=optionalCliente.get();
        try {
            this.repositorio.delete(clienteEncontrado);
        }catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo elimina, " + error.getMessage()

            );
        }

    }
    //Actulizar algunos datos
    public ClienteDTO actualizarCliente (Integer id, Cliente datosCliente){
        Optional<Cliente> clienteOptional= this.repositorio.findById(id);
        if (!clienteOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "El id ingresado no coincide con ningún cliente registrado");
        }
        Cliente clienteEncontrado=clienteOptional.get();
//Espacio donde van las validaciones.

        //datos que se pueden acualizar
        clienteEncontrado.setCalificacion(datosCliente.getCalificacion());
        clienteEncontrado.setDireccion(datosCliente.getDireccion());
        //actualizar la base de datos (asegurar que la clase no sea null)
        Cliente clienteActualizado= this.repositorio.save(clienteEncontrado);
        if (clienteActualizado==null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ha ocurrido un problema al intentar guardar");
        }
        return  this.mapa.convertirModelo_a_Dto(clienteActualizado);
    }
}
