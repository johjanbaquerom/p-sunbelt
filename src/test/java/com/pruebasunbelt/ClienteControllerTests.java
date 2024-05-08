package com.pruebasunbelt;


import com.pruebasunbelt.controller.ClienteController;
import com.pruebasunbelt.exception.ClienteNotFoundException;
import com.pruebasunbelt.model.Cliente;
import com.pruebasunbelt.model.ConsultaClienteRequest;
import com.pruebasunbelt.service.ClienteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTests {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void consultarCliente_ClienteExistente_DebeRetornarCliente() throws ClienteNotFoundException {
        ConsultaClienteRequest request = new ConsultaClienteRequest();
        request.setTipoDocumento("C");
        request.setNumeroDocumento("10121314");

        Cliente clienteMock = new Cliente();
        clienteMock.setPrimerNombre("Juan");
        clienteMock.setPrimerApellido("Perez");
        when(clienteService.consultarCliente(request.getTipoDocumento(), request.getNumeroDocumento())).thenReturn(clienteMock);

        ResponseEntity<Object> responseEntity = clienteController.consultarCliente(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clienteMock, responseEntity.getBody());
    }

    @Test
    void consultarCliente_ClienteNoExistente_DebeRetornarNotFound() throws ClienteNotFoundException {
        ConsultaClienteRequest request = new ConsultaClienteRequest();
        request.setTipoDocumento("C");
        request.setNumeroDocumento("12345678");
        when(clienteService.consultarCliente(request.getTipoDocumento(), request.getNumeroDocumento()))
                .thenThrow(ClienteNotFoundException.class);

        ResponseEntity<Object> responseEntity = clienteController.consultarCliente(request);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
