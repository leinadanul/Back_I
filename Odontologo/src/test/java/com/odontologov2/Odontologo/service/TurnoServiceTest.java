package com.odontologov2.Odontologo.service;

import com.odontologov2.Odontologo.DTO.TurnoDTO;
import com.odontologov2.Odontologo.entity.Domicilio;
import com.odontologov2.Odontologo.entity.Odontologo;
import com.odontologov2.Odontologo.entity.Paciente;
import com.odontologov2.Odontologo.entity.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    TurnoService turnoService;

    @Test
    @Order(1)
    public void agregarTurnoTest(){
        Paciente pacientePrueba = new Paciente("Luis",
                "Gomez","121212", LocalDate.of(2022,10,1),
                "luisgommez@mail.com",new Domicilio("Calle 1",123,"su Casa",
                "SuCasa"));;
        pacienteService.guardarPaciente(pacientePrueba);

        Odontologo odontologoPrueba = new Odontologo("Daniel", "Perejito", "w211");
        odontologoService.guardarOdontologo(odontologoPrueba);

        List<Paciente> listaPacientes = pacienteService.listarPacientes();
        Paciente pacientePruebaRecuperado = listaPacientes.get(0);
        List<Odontologo> listaOdontologos = odontologoService.listarOdontologo();
        Odontologo odontologoPruebaRecuperado = listaOdontologos.get(0);

        turnoService.guardarTurno(
                new Turno(odontologoPruebaRecuperado, pacientePruebaRecuperado, LocalDate.of(2022,10,10)));


        List<Turno> listaTurnos = turnoService.listarTurnos();
        Turno turnoRecuperado = listaTurnos.get(0);

        assertNotNull(turnoRecuperado);
        assertEquals(1L, turnoRecuperado.getOdontologo().getId());
        assertEquals(1L, turnoRecuperado.getPaciente().getId());
        assertEquals(LocalDate.of(2022, 10, 10), turnoRecuperado.getFecha());
    }

    @Test
    @Order(2)
    public void listarTurnosTest(){
        List<Turno> listaTurnos = turnoService.listarTurnos();
        Turno turnoRecuperado = listaTurnos.get(0);

        assertNotNull(turnoRecuperado);
        assertEquals(1L, turnoRecuperado.getOdontologo().getId());
        assertEquals(1L, turnoRecuperado.getPaciente().getId());
        assertEquals(LocalDate.of(2022, 10, 10), turnoRecuperado.getFecha());
    }
}


