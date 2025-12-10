package com.example.EcomerceUribe.modelos.DTOS;

import com.example.EcomerceUribe.ayudas.Sedes;

public class EmpleadoDTO {
    private Integer id;
    private Sedes sede;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(Integer id, Sedes sede) {
        this.id = id;
        this.sede = sede;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sedes getSede() {
        return sede;
    }

    public void setSede(Sedes sede) {
        this.sede = sede;
    }
}
