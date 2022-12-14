package com.odontologov2.Odontologo.controller;


import com.odontologov2.Odontologo.Exceptions.ResourceNotFoundException;
import com.odontologov2.Odontologo.entity.Odontologo;
import com.odontologov2.Odontologo.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarTodosOdontologos());
    }
    @GetMapping("{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            logger.error("Error tratando de buscar odontologo por id");
            throw new ResourceNotFoundException("El Id ingresado no existe");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se eliminĂ³ al odontologo con id= "+id);
    }








}
