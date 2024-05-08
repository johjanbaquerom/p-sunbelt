package com.pruebasunbelt;

import com.pruebasunbelt.exception.ClienteNotFoundException;
import com.pruebasunbelt.model.Cliente;
import com.pruebasunbelt.service.ClienteService;
import com.pruebasunbelt.service.ClienteServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceImplTest {

    private final ClienteService clienteService = new ClienteServiceImpl();

    @Test
    void consultarCliente_ClienteExistente_DebeRetornarCliente() throws ClienteNotFoundException {
        String tipoDocumento = "C";
        String numeroDocumento = "10121314";

        Cliente cliente = clienteService.consultarCliente(tipoDocumento, numeroDocumento);
        assertNotNull(cliente);
        assertEquals("Juan", cliente.getPrimerNombre());
        assertEquals("Perez", cliente.getPrimerApellido());
    }

    @Test
    void consultarCliente_ClienteNoExistente_DebeLanzarClienteNotFoundException() {
        String tipoDocumento = "C";
        String numeroDocumento = "12345678";

        assertThrows(ClienteNotFoundException.class, () -> clienteService.consultarCliente(tipoDocumento, numeroDocumento));
    }

    @Test
    void consultarCliente_TipoDocumentoInvalido_DebeLanzarClienteNotFoundException() {
        String tipoDocumento = "P";
        String numeroDocumento = "10121314";

        assertThrows(ClienteNotFoundException.class, () -> clienteService.consultarCliente(tipoDocumento, numeroDocumento));
    }
}
