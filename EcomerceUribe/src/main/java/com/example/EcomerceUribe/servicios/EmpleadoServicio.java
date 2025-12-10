package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {
    @Autowired
    private IEmpleadoRepositorio repositorio;
    @Autowired
    private IEmpleadoMapa mapa;

    //activar el servicio
    public EmpleadoDTO guardarEmpleadoDTO (Empleado datosEmpleado){
        //validación
        if (datosEmpleado.getCargo()==null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe seleccionar un cargo para levar a cabo el registro"
            );
        }

        //intentar guardar en el repo
        Empleado guardarEnRepo= this.repositorio.save(datosEmpleado);
        if (guardarEnRepo==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Lo sentimos, ocurrio un error al guardar. Intenta luego"
            );
        }
        //retornar dto
        return this.mapa.convertirModelo_a_DTO(datosEmpleado);
    }
    //buscar todos los empleados List<>
    public List<EmpleadoDTO>BuscarAllEmpleados(){
        List<Empleado> ListaDeEmpleados = this.repositorio.findAll();
        return this.mapa.convertirList_a_ListDTO(ListaDeEmpleados);
    }

    //buscar empleado por id
    public EmpleadoDTO BucarEmpleadoId(Integer id){
        Optional<Empleado> empleadoOptional=this.repositorio.findById(id);
        if (!empleadoOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ningun empleado registrado coincide con el id ingresado"
            );
        }
        Empleado empleadoEncontrado=empleadoOptional.get();
        return this.mapa.convertirModelo_a_DTO(empleadoEncontrado);
    }
    //eliminar empleado
    public void eliminarEmpleado (Integer id){
        Optional<Empleado> optionalEmpleado= this.repositorio.findById(id);
        if (!optionalEmpleado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ningun empleado registrado coincide con el id ingresado"
            );
        }
        Empleado empleadoEncontrado= optionalEmpleado.get();
        try {
            this.repositorio.delete(empleadoEncontrado);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "no se pudo eliminar, "+ error.getMessage()
            );
        }
    }
    //Actulizar algunos datos
    public EmpleadoDTO actualizarEmpleado(Integer id, Empleado datosEmpleado){
        Optional<Empleado> optionalEmpleado=this.repositorio.findById(id);
        if (!optionalEmpleado.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No hay empleados registrados con el id ingresado");
        }
        Empleado empleadoEncontrado = optionalEmpleado.get();
        //espacio para validaciones

        //datos a actualizar
        empleadoEncontrado.setSalario(datosEmpleado.getSalario());
        empleadoEncontrado.setSede(datosEmpleado.getSede());

        //validar que no sea null y actualizar en la base de datos
        Empleado empleadoActualizado= this.repositorio.save(empleadoEncontrado);
        if (empleadoActualizado==null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo actualizar, intente más tarde");
        }
        return this.mapa.convertirModelo_a_DTO(empleadoActualizado);
    }
}
