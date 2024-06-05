package com.riwi.Examen.api.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Documentación de ApiRestful",
    version = "1.0",
    description = "Se documentará cada endPoint y se organizará de manera estructurada"
))
public class OpenApiConfiguration {

}
