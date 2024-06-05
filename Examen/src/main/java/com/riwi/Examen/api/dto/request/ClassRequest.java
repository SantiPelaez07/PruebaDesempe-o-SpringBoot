package com.riwi.Examen.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {
    @NotBlank(message = "El nombre de la clase es obligatorio")
    @Size(min = 5, max = 40, message = "El nombre debe tener entre 5 y 40 caracteres")
    private String name;
    @NotBlank(message = "La descripción de la clase es obligatoria")
    @Size(min = 20, max = 500, message = "El nombre debe tener entre 20 y 500 caracteres")
    private String description;
    private boolean active;
}

