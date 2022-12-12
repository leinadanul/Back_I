package com.odontologov2.Odontologo.service;

import com.odontologov2.Odontologo.Exceptions.ResourceNotFoundException;
import com.odontologov2.Odontologo.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontoloServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void GuardarOdontologoTest() {
        Odontologo odontologoTest = new Odontologo("Maria", "Del mar", "23fdd");
        odontologoService.guardarOdontologo(odontologoTest);
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoTest);
        assertEquals(1L, odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Long idBuscado = 1L;
        Optional<Odontologo> pacienteBuscado = odontologoService.buscarOdontologo(idBuscado);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarOdontologoTest() {
        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
        assertEquals(1, odontologos.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologo(){
        Odontologo odontologoActualizado = new Odontologo(1l, "Carlos", "Dua5rte", "w22212w1");
        odontologoService.actualizarOdontologo(odontologoActualizado);
        Optional<Odontologo> odontologoActualizados = odontologoService.buscarOdontologo(1L);
        assertEquals("Carlos", odontologoActualizados.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarOdonotlogoTest() throws ResourceNotFoundException {
        Long idAEliminar=1L;
        odontologoService.eliminarOdontologo(idAEliminar);
        Optional<Odontologo> OdontologoEliminado=odontologoService.buscarOdontologo(idAEliminar);
        assertFalse(OdontologoEliminado.isPresent());
    }
}



