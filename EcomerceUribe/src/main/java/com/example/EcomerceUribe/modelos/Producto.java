package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.CategoriaProducto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Integer id;
    @Column(name = "nombres", nullable = false)
    @Schema(description ="nombre del producto", example = "camisa")
    private String nombres;
    @Column(name = "fotografia", nullable = false)
    @Schema(description = "Enlace a la imagen", example = "https://imagen-generica.jpg")
    private String fotografia;
    @Column(name = "descripcion", nullable = false)
    @Schema(description = "Descripción del producto", example = "En algodon")
    private String descripción;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProducto categoria;
    @Column(name = "precioUnitario", nullable = false)
    @Schema(description = "valor de producto por unidad", example = "$90.000")
    private Integer precioUnitario;
    @Column(name = "marca", nullable = false, length = 20)
    @Schema(description = "Marca del producto", example = "chevignon")
    private String marca;
    @Column(name = "aplicaDescuento", nullable = false)
    @Schema(description = "aplica para descuento (si/no)", example = "si")
    private Boolean aplicaDescuento;

    @Schema(hidden = true)
    @ManyToOne
    @JoinColumn(name = "fk_pedido", referencedColumnName = "id")
    @JsonBackReference(value = "relacionpedidoproducto")
    private Pedido pedido;

    public Producto() {
    }

    public Producto(Integer id, String nombres, String fotografia, String descripción, CategoriaProducto categoria, Integer precioUnitario, String marca, Boolean aplicaDescuento, Pedido pedido) {
        this.id = id;
        this.nombres = nombres;
        this.fotografia = fotografia;
        this.descripción = descripción;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
        this.aplicaDescuento = aplicaDescuento;
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CategoriaProducto getCategotia() {
        return categoria;
    }

    public void setCategotia(CategoriaProducto categotia) {
        this.categoria = categotia;
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

    public Boolean getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(Boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
