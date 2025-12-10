package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {
    @Autowired
    private IPedidoRepositorio repo;
    @Autowired
    private IPedidoMapa mapa;

    public PedidoDTO guaradar_pedidoDTO(Pedido datosPedido) {
        if (datosPedido.getCostoEnvio() == null || datosPedido.getCostoEnvio() < 10000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No completa el monto minimo"
            );
        }
        //intentar guardar pedido
        Pedido pedidoToDTO = this.repo.save(datosPedido);
        if (pedidoToDTO == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo almacenar el pedido correctamente. Intenta luego"
            );
        }
        //retornar el DTO al respositorio
        return this.mapa.convertirModelo_a_DTO(datosPedido);
    }
    //buscar todos los pedidos List<>
    public List<PedidoDTO> BuasacrPedidos(){
        List<Pedido> ListaAllPedidos = this.repo.findAll();
        return this.mapa.convertirList_a_ListDTO(ListaAllPedidos);
    }

    //buscar pedido por id
    public PedidoDTO buscarPedidoId(Integer id){
        Optional<Pedido> pedidoOptional= this.repo.findById(id);
        if (!pedidoOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay coincidencias con el id ingresado"
            );
        }
        Pedido PedidoEncontrado= pedidoOptional.get();
        return this.mapa.convertirModelo_a_DTO(PedidoEncontrado);
    }
    //eliminar pedido
    public void PedidoEliminar (Integer id){
        Optional<Pedido> optionalPedido=this.repo.findById(id);
        if (!optionalPedido.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay coincidencias con el id ingresado"
            );
        }
        Pedido pedidoBorrar= optionalPedido.get();
        try {
            this.repo.delete(pedidoBorrar);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error al borrar, "+ error.getMessage()
            );
        }
    }
    //Actulizar algunos datos
    public PedidoDTO actualizarPedido (Integer id, Pedido datosPedidos){
        Optional<Pedido> pedidoOptional= this.repo.findById(id);
        if (!pedidoOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontraron productos con el id ingresado"
            );
        }
        Pedido pedidoEncontrado= pedidoOptional.get();
        //datos a actualizar
        pedidoEncontrado.setCostoEnvio(datosPedidos.getCostoEnvio());
        pedidoEncontrado.setFechaEntrega(datosPedidos.getFechaEntrega());

        //validar que se haya actualizado
        Pedido pedidoActualizado= this.repo.save(pedidoEncontrado);
        if (pedidoActualizado == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo actualizar, intenta más tarde");
        }
        return  this.mapa.convertirModelo_a_DTO(pedidoActualizado);
    }
}
