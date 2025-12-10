package com.example.EcomerceUribe.modelos.DTOS;

public class ClienteDTO {
    private String direccion;
    private Double calificacion;
    private String ciudad;

    public ClienteDTO() {
    }

    public ClienteDTO(String direccion, Double calificacion, String ciudad) {
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
