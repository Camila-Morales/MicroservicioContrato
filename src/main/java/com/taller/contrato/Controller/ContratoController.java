package com.taller.contrato.Controller;

import com.taller.contrato.Entities.Contrato;
import com.taller.contrato.Service.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    // Obtener todos los contratos
    @GetMapping
    public ResponseEntity<List<Contrato>> getAllContratos() {
        List<Contrato> contratos = contratoService.findAll();
        return ResponseEntity.ok(contratos);
    }

    // Obtener contrato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContratoById(@PathVariable int id) {
        Optional<Contrato> contrato = contratoService.findById(id);
        return contrato.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Crear un nuevo contrato con validación
    @PostMapping
    public ResponseEntity<Object> createContrato(@Valid @RequestBody Contrato contrato) {
        Contrato newContrato = contratoService.save(contrato);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContrato);
    }

    // Actualizar un contrato existente con validación
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContrato(@PathVariable int id, @Valid @RequestBody Contrato contrato) {
        Optional<Contrato> existingContrato = contratoService.findById(id);
        if (existingContrato.isPresent()) {
            contrato.setId(id);
            Contrato updatedContrato = contratoService.save(contrato);
            return ResponseEntity.ok(updatedContrato);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato no encontrado con ID: " + id);
        }
    }

    // Eliminar un contrato por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContrato(@PathVariable int id) {
        Optional<Contrato> contrato = contratoService.findById(id);
        if (contrato.isPresent()) {
            contratoService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Contrato eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato no encontrado con ID: " + id);
        }
    }

    // Manejo de excepciones para validaciones
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
