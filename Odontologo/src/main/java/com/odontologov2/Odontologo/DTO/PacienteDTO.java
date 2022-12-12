package com.odontologov2.Odontologo.DTO;

import com.odontologov2.Odontologo.entity.Domicilio;
import com.odontologov2.Odontologo.entity.Paciente;

import java.time.LocalDate;

public class PacienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;
    private Domicilio domicilio;

    public PacienteDTO(){}

    public PacienteDTO (Paciente paciente){
        id = paciente.getId();
        nombre = paciente.getNombre();
        apellido = paciente.getApellido();
        cedula = paciente.getCedula();
        fechaIngreso = paciente.getFechaIngreso();

    }

    public PacienteDTO(String nombre, String apellido, String cedula, LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    public Paciente toEntity() {
        Paciente entity = new Paciente();

        entity.setId(id);
        entity.setApellido(apellido);
        entity.setCedula(cedula);
        entity.setNombre(nombre);
        entity.setFechaIngreso(fechaIngreso);


        return entity;
    }

}
