package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.EstadosUsuario;
import com.example.EcomerceUribe.ayudas.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Integer id;
    @Column(name = "names", nullable = false, unique = false, length = 50)
    @Schema(description = "Nombres del usuario", example = "Pepito Perez")
    private String nombres;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    @Schema(description = "Correo del usuario", example = "pepito@example.com")
    private String correo;
    @Column(name = "password", nullable = false, unique = false)
    @Schema(description = "Contrasena del usuario", example = "contraPepito123")
    private String contrasena;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, unique = false)
    private EstadosUsuario estado;
    @Column(name = "dateOfBirth", nullable = true, unique = false)
    @Schema(description = "Fecha de nacimeinto del usuario", example = "2004-06-15")
    private LocalDate fechaNacimiento;
    @Enumerated(EnumType.STRING)
    @Column(name = "idType", nullable = false, unique = false)
    private TipoDocumento tipoDocumento;
    @Column(name = "document", nullable = false, unique = true, length = 10)
    @Schema(description = "Número de documento del usuario", example = "1029387465")
    private Integer documento;

    //Creando una relacion de 1 a 1 con Empleado
    @Schema(hidden = true)
    @OneToOne(mappedBy = "usuario")
    @JsonBackReference(value = "relacionempleadousuario")
    private Empleado empleado;

    public Usuario() {
    }

    public Usuario(Integer id, String nombres, String correo, String contrasena, EstadosUsuario estado, LocalDate fechaNacimiento, TipoDocumento tipoDocumento, Integer documento) {
        this.id = id;
        this.nombres = nombres;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public EstadosUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadosUsuario estado) {
        this.estado = estado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}