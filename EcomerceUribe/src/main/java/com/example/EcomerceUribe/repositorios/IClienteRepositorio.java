package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByCalificacion (Double calificacion);
    Optional<Cliente> findByDireccion (String direccion);
}
