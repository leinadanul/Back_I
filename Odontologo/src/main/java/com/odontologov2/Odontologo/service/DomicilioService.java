package com.odontologov2.Odontologo.service;

import com.odontologov2.Odontologo.DTO.DomicilioDTO;
import com.odontologov2.Odontologo.entity.Domicilio;
import com.odontologov2.Odontologo.entity.Paciente;
import com.odontologov2.Odontologo.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {


    private DomicilioRepository domicilioRepository;
    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository){
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardarDomicilio(Domicilio domicilio){
        return  domicilioRepository.save(domicilio);
    }


    public Domicilio actualizarDomicilio (Domicilio domicilio){
        return domicilioRepository.save(domicilio);
    }


    public void eliminarDomicilio(Long id){
        DomicilioRepository.deleteById(id);
    }

    /*
    public Domicilio buscarDomicilio(Long id){
        return domicilioRepository.findIdByStreet()
        }
     */
    public DomicilioDTO buscarIdDomicilio(String calle, String numero){
        Domicilio domicilio = domicilioRepository.findIdByStreet(calle, numero);
        return domicilio != null ? new DomicilioDTO(domicilio) : null;
    }
    public Optional<Domicilio> buscarDomicilio(Long id ){
        return  domicilioRepository.findById(Math.toIntExact(id));
    }

    public List<DomicilioDTO> listarDomicilios(){
        List<Domicilio> domiciliosEncontrado= domicilioRepository.findAll();
        List<DomicilioDTO> respuesta = new ArrayList<>();
        for (Domicilio domicilio:domiciliosEncontrado) {
            respuesta.add(domicilioADomicilioDTO(domicilio));
        }
        return respuesta;
    }




    private DomicilioDTO domicilioADomicilioDTO(Domicilio domicilio){
        //convertir el turno a un turnoDTO
        DomicilioDTO respuesta= new DomicilioDTO();
        //cargar la información de turno al turno DTO
        respuesta.setId(respuesta.getId());
        respuesta.setCalle(domicilio.getCalle());
        respuesta.setDepartamento(domicilio.getProvincia());
        respuesta.setLocalidad(domicilio.getLocalidad());
        respuesta.getNumero();
        //devolución
        return respuesta;
    }
}
