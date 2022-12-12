package com.odontologov2.Odontologo.controller;

import com.odontologov2.Odontologo.Exceptions.ResourceNotFoundException;
import com.odontologov2.Odontologo.entity.Paciente;
import com.odontologov2.Odontologo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    /*
    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email ){
        //busqueda del paciente
        Paciente paciente=pacienteService.buscarPacientePorCorreo(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        //devolver el nombre del template
        return "index";
    }
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    /*@GetMapping("/buscar/correo/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(email);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }

     */

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarPacientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Se eliminó al odontologo con id= "+id);
    }



    /*

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            //existe el id con un paciente registrado
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el paciente" +
                    " con id= "+id);
        }
        else{
            //no existe
            //arrojar la excepción
            throw new ResourceNotFoundException("Error. No existe" +
                    " el ID= "+id+" asociado a un paciente en la base de datos.");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No existe" +
            //        " el ID= "+id+" asociado a un paciente en la base de datos.");
        }
    }

     */



    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        //chequeo para controlar que sea un correo sin usar
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPacientePorCorreo(paciente.getEmail());
        if (pacienteBuscado.isPresent()){
            //existe un paciente con ese email
            return ResponseEntity.badRequest().build();
        }
        else{
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }

    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        //preguntar si existe el paciente
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó al paciente con id= " + paciente.getId());

        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar al paciente con id= " + paciente.getId() +
                    " ya que el mismo no existe en la base de datos. :(");
        }
    }


}
