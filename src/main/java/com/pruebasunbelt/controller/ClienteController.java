package com.pruebasunbelt.controller;

import com.pruebasunbelt.exception.ClienteNotFoundException;
import com.pruebasunbelt.model.Cliente;
import com.pruebasunbelt.model.ConsultaClienteRequest;
import com.pruebasunbelt.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/consultarCliente")
    public ResponseEntity<Object> consultarCliente(@RequestBody ConsultaClienteRequest request) {
        try {
            Cliente clienteConsultado = clienteService.consultarCliente(request.getTipoDocumento(), request.getNumeroDocumento());
            return new ResponseEntity<>(clienteConsultado, HttpStatus.OK);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

