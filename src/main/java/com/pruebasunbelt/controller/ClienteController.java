package com.pruebasunbelt.controller;

import com.pruebasunbelt.model.Cliente;
import com.pruebasunbelt.model.ConsultaClienteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @PostMapping("/consultarCliente")
    public ResponseEntity<?> consultarCliente(@RequestBody ConsultaClienteRequest request) {
        if (request.getTipoDocumento() == null || request.getNumeroDocumento() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo y número de documento son obligatorios");
        }
        if (!"C".equals(request.getTipoDocumento()) && !"P".equals(request.getTipoDocumento())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de documento no válido");
        }
        if (!"10121314".equals(request.getNumeroDocumento())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }

        Cliente cliente = new Cliente();
        cliente.setPrimerNombre("Juan");
        cliente.setSegundoNombre("Pablo");
        cliente.setPrimerApellido("Gómez");
        cliente.setSegundoApellido("López");
        cliente.setTelefono("1234567890");
        cliente.setDireccion("Calle 123");
        cliente.setCiudadResidencia("Bogotá");

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
}

