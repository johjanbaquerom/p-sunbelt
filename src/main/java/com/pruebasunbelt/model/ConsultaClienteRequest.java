package com.pruebasunbelt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaClienteRequest {

    private String tipoDocumento;
    private String numeroDocumento;

}
