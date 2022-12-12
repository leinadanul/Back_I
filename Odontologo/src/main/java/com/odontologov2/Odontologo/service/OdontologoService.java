package com.odontologov2.Odontologo.service;

import com.odontologov2.Odontologo.Exceptions.ResourceNotFoundException;
import com.odontologov2.Odontologo.entity.Odontologo;
import com.odontologov2.Odontologo.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    public List<Odontologo> listarOdontologo() {
        return odontologoRepository.findAll();
    }


    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologo(Long id) {
        return odontologoRepository.findById(id);
    }

    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoAEliminar = buscarOdontologo(id);
        if (odontologoAEliminar.isPresent()) {
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se puede eliminar el paciente " +
                    "con id= " + id + " ya que no existe un odontologo en la base de datos" +
                    "con el mencionado id");
        }
    }

    public List<Odontologo> buscarTodosOdontologos() {
        return odontologoRepository.findAll();
    }
}
