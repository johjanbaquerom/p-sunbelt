package com.pruebasunbelt.service;

import com.pruebasunbelt.exception.ClienteNotFoundException;
import com.pruebasunbelt.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Override
    public Cliente consultarCliente(String tipoDocumento, String numeroDocumento) throws ClienteNotFoundException {
        if (!tipoDocumento.equals("C")) {
            throw new ClienteNotFoundException("Tipo de documento inválido. Solo se permite 'C' (Cédula de Ciudadanía).");
        }

        if (!numeroDocumento.equals("10121314")) {
            throw new ClienteNotFoundException("Cliente no encontrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setPrimerNombre("Juan");
        cliente.setSegundoNombre("Felipe");
        cliente.setPrimerApellido("Perez");
        cliente.setSegundoApellido("Garzon");
        cliente.setTelefono("54328983");
        cliente.setDireccion("calle 45 f sur ");
        cliente.setCiudadResidencia("Bogota");
        return cliente;
    }
}
