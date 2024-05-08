package com.pruebasunbelt.service;

import com.pruebasunbelt.exception.ClienteNotFoundException;
import com.pruebasunbelt.model.Cliente;

public interface ClienteService {

    Cliente consultarCliente(String tipoDocumento, String numeroDocumento) throws ClienteNotFoundException;
}
