package com.odontologov2.Odontologo.DTO;

import com.odontologov2.Odontologo.entity.Domicilio;

public class DomicilioDTO {

    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String departamento;

    public DomicilioDTO(){}

    public DomicilioDTO(Integer id) {
        this.id = id;
    }

    public DomicilioDTO(String calle, String numero, String localidad, String departamento) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.departamento = departamento;
    }

    public DomicilioDTO(Domicilio d) {
        id = Math.toIntExact(d.getId());
        calle = d.getCalle();
        numero = String.valueOf(d.getNumero());
        localidad = d.getLocalidad();
        departamento = d.getProvincia();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Domicilio toEntity() {
        Domicilio entity = new Domicilio();

        entity.setId(Long.valueOf(id));
        entity.setCalle(calle);
        entity.setLocalidad(localidad);
        entity.setProvincia(departamento);
        entity.setNumero(Integer.valueOf(numero));

        return entity;
    }
}

