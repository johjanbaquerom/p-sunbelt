package com.pruebasunbelt;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConsultarClienteExitoso() throws Exception {
        String requestBody = "{\"tipoDocumento\": \"C\", \"numeroDocumento\": \"10121314\"}";
        String clienteResponse = "{\"primerNombre\": \"Juan\", \"segundoNombre\": \"Pablo\", \"primerApellido\": " +
                "\"Gómez\", \"segundoApellido\": \"López\", \"telefono\": \"1234567890\", " +
                "\"direccion\": \"Calle 123\", \"ciudad\": \"Bogotá\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/consultarCliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/consultarCliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.primerNombre").value("Juan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.segundoNombre").value("Pablo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.primerApellido").value("Gómez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.segundoApellido").value("López"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefono").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.direccion").value("Calle 123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ciudad").value(Matchers.equalToIgnoringCase("Bogotá")));
    }

    @Test
    public void testConsultarClienteBadRequest() throws Exception {
        String requestBody = "{\"numeroDocumento\": \"10121314\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/consultarCliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testConsultarClienteTipoDocumentoInvalido() throws Exception {
        String requestBody = "{\"tipoDocumento\": \"X\", \"numeroDocumento\": \"10121314\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/consultarCliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
