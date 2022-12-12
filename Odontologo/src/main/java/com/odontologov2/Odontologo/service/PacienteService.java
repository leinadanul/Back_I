package com.odontologov2.Odontologo.service;

import com.odontologov2.Odontologo.Exceptions.ResourceNotFoundException;
import com.odontologov2.Odontologo.entity.Odontologo;
import com.odontologov2.Odontologo.entity.Paciente;
import com.odontologov2.Odontologo.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class PacienteService {

    private PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository ){
        this.pacienteRepository= pacienteRepository;}
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id ){
        return  pacienteRepository.findById((id));
    }
    public void eliminarPacientes(Long id){
        pacienteRepository.deleteById((id));
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAEliminar = buscarPaciente(id);
        if (pacienteAEliminar.isPresent()) {
            pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se puede eliminar el paciente " +
                    "con id= " + id + " ya que no existe un odontologo en la base de datos" +
                    "con el mencionado id");
        }
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public List<Paciente> buscarPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorCorreo(String correo){
        return pacienteRepository.findByEmail(correo);
    }
}
/*
    private PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository ){
        this.pacienteRepository= pacienteRepository;}
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id ){
        return  pacienteRepository.findById(id);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public List<Paciente> buscarPacientes(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacientePorCorreo(String correo){
        return pacienteRepository.findByEmail(correo);
    }
}
*/

