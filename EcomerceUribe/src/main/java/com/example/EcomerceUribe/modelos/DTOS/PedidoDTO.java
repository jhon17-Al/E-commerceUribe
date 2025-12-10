package com.example.EcomerceUribe.modelos.DTOS;

import java.time.LocalDate;

public class PedidoDTO {
    private Integer id;
    private LocalDate fechaEntrega;
    private Integer costoEnvio;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer id, LocalDate fechaEntrega, Integer costoEnvio) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.costoEnvio = costoEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Integer costoEnvio) {
        this.costoEnvio = costoEnvio;
    }
}

