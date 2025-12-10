package com.example.EcomerceUribe.modelos.DTOS;

public class ProductoDTO {
    private String nombres;
    private String fotografia;
    private String descripción;
    private Integer precioUnitario;
    private String marca;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombres, String fotografia, String descripción, Integer precioUnitario, String marca) {
        this.nombres = nombres;
        this.fotografia = fotografia;
        this.descripción = descripción;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
