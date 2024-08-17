/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ista.edu.practica.Facturacion.controller;

import ista.edu.practica.Facturacion.model.Cliente;
import ista.edu.practica.Facturacion.service.ClienteService;
import java.util.List;

import javax.validation.Valid;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author 59398
 */
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    // @Autowired
    ClienteService clienteService;

    private final ObjectMapper objectMapper;

    public ClienteController(ClienteService clienteService, ObjectMapper objectMapper) {
        this.clienteService = clienteService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/listar")
    public ResponseEntity<String> obtenerLista() {
        List<Cliente> clientes = clienteService.findByAll();
        try {
            // Convertir la lista de clientes a JSON
            String jsonResponse = objectMapper.writeValueAsString(clientes);
    
            // Codificar la respuesta JSON
            String safeJsonResponse = Encode.forHtmlContent(jsonResponse);
    
            return new ResponseEntity<String>(safeJsonResponse, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error al procesar la respuesta JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente c) {
        return new ResponseEntity<>(clienteService.save(c), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Integer id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cliente> actualizarUsuario(@PathVariable Integer id, @RequestBody Cliente c) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                cliente.setNombre(c.getNombre());
                cliente.setApellido(c.getApellido());
                cliente.setCedula(c.getCedula());
                return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }
}
