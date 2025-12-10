package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.CategoriaProducto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "fotografia", nullable = false)
    private String fotografia;
    @Column(name = "descripcion", nullable = false)
    private String descripción;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProducto categoria;
    @Column(name = "precioUnitario", nullable = false)
    private Integer precioUnitario;
    @Column(name = "marca", nullable = false, length = 20)
    private String marca;
    @Column(name = "aplicaDescuento", nullable = false)
    private Boolean aplicaDescuento;


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
