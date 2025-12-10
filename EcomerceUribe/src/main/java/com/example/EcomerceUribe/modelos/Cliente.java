package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.Departamentos;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Integer id;
    @Column(name = "direccion", nullable = false,unique = false, length = 50)
    @Schema (description = "direccion de residencia", example = "Cra.17A #56-8")
    private String direccion;
    @Column(name = "calificacion", nullable = false, unique = false )
    @Schema (description = "Calificación entre 1 y 5", example = "4.5")
    private Double calificacion;
    @Column(name = "referenciaPago", nullable = false, unique = false, length = 30)
    @Schema (description = "referencia del pago", example = "H01564")
    private String referenciaPago;
    @Enumerated(EnumType.STRING)
    @Column(name = "departament", unique = false, nullable = false)
    private Departamentos departamentos;
    @Column(name = "ciudad", nullable = false, unique = false, length = 40)
    @Schema (description = "Ciudad de rersidencia", example = " Medellin")
    private String ciudad;

    //relacion Cliente 1 pedido N
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference(value = "pedidoacliente")
    @Schema(hidden = true)
    private List<Pedido> pedidos;



    public Cliente() {
    }

    public Cliente(Integer id, String direccion, Double calificacion, String referenciaPago, Departamentos departamentos, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamentos = departamentos;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
