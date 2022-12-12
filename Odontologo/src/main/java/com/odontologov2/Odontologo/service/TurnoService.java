package com.odontologov2.Odontologo.service;

import com.odontologov2.Odontologo.DTO.TurnoDTO;
import com.odontologov2.Odontologo.entity.Odontologo;
import com.odontologov2.Odontologo.entity.Paciente;
import com.odontologov2.Odontologo.entity.Turno;
import com.odontologov2.Odontologo.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }



    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }
    /*
    public List<TurnoDTO> listarTurnos(){
        List<Turno> turnoEncontrado=turnoRepository.findAll();
        //recorrer la lista para ir convirtiendo cada elemento
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno turno: turnoEncontrado){
            respuesta.add(turnoATurnoDTO(turno));
        }
        return respuesta;

     */

    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado= turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else {
            return Optional.empty();
        }
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }


    public void actualizarTurno(TurnoDTO turnodto){
        turnoRepository.save(turnoDTOATurno(turnodto));
    }
    public TurnoDTO guardarTurno(TurnoDTO turnodto){
        Turno turnoGuardado= turnoRepository.save(turnoDTOATurno(turnodto));
        return turnoATurnoDTO(turnoGuardado);
    }

    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir el turno a un turnoDTO
        TurnoDTO respuesta= new TurnoDTO();
        //cargar la información de turno al turno DTO
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFecha(turno.getFecha());
        //devolución
        return respuesta;
    }
    private Turno turnoDTOATurno(TurnoDTO turnodto){
        Turno respuesta= new Turno();
        //cargar la información de turno DTO al turno
        Odontologo odontologo= new Odontologo();
        Paciente paciente= new Paciente();
        odontologo.setId((long) Math.toIntExact(turnodto.getOdontologoId()));
        paciente.setId(turnodto.getPacienteId());
        respuesta.setFecha(turnodto.getFecha());
        respuesta.setId(turnodto.getId());
        //no debemos olvidarnos de agregar ambos objetos
        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        //salida
        return respuesta;
    }

}


