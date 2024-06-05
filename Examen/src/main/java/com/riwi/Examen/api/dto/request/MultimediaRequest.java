package com.riwi.Examen.api.dto.request;

import java.time.LocalDateTime;


import com.riwi.Examen.utils.enums.Type;

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
public class MultimediaRequest {
    @NotBlank(message = "El tipo de multimedia es obligatorio")
    private Type type;
    @NotBlank(message = "La URL del contenido multimedia es obligatorio")
    private String url;
    @NotBlank(message = "La fecha y hora del contenido multimedia es obligatorio")
    private LocalDateTime created_at;
    @NotBlank(message = "El estado de este contenido multimedia es obligatorio")
    private boolean active;
    @NotBlank(message = "El id de la lección correspondiente a este contenido multimedia es obligatorio")
    @Size(min = 32)
    private Long lessonId;
}
