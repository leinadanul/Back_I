package com.odontologov2.Odontologo.service;


import com.odontologov2.Odontologo.Exceptions.ResourceNotFoundException;
import com.odontologov2.Odontologo.entity.Domicilio;
import com.odontologov2.Odontologo.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar= new Paciente("Luis",
                "Gomez","121212", LocalDate.of(2022,10,1),
                "luisgommez@mail.com",new Domicilio("Calle 1",123,"su Casa",
                "SuCasa"));
        Paciente pacienteGuardado=pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idBuscado = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idBuscado);
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void buscarPacientesTest(){
        List<Paciente> pacientes=pacienteService.buscarPacientes();
        assertEquals(1,pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteAActualizar= new Paciente(1L,"Daniel",
                "Ramirez","3223", LocalDate.of(2022,10,27),
                "daniel@gmail.com",new Domicilio(1L,"Calle b",6465,"san cristobal",
                "kaza"));
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado=pacienteService.buscarPaciente(1L);
        assertEquals("Daniel",pacienteActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        Long idAEliminar=1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado=pacienteService.buscarPaciente(idAEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }
    }

