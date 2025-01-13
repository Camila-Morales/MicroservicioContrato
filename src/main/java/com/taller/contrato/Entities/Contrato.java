package com.taller.contrato.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre del empleado no puede estar vacío.")
    @Column(nullable = false)
    private String empleado;

    @NotBlank(message = "El nombre de la empresa no puede estar vacío.")
    @Column(nullable = false)
    private String empresa;

    @Min(value = 1, message = "La duración debe ser al menos de 1 mes.")
    @Column(nullable = false)
    private int duracionMeses;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Column(nullable = false)
    private Date fechaInicio;

    // GETTERS y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
