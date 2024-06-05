package com.riwi.Examen.api.dto.request;

import java.time.LocalDateTime;


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
public class LessonRequest {
    @NotBlank(message = "El titulo de la lección es obligatorio")
    @Size(min = 5, max = 40, message = "El titulo de la lección debe tener entre 5 y 40 caracteres")
    private String title;
    @NotBlank(message = "El contenido de la lección es obligatorio")
    @Size(min = 10, max = 500, message = "El contenido de la lección debe tener entre 10 y 500 caracteres")
    private String content;
    @NotBlank(message = "La fecha y hora de esta lección es obligatoria")
    private LocalDateTime created_at;
    @NotBlank(message = "El estado de esta lección es obligatorio")
    private boolean active;
    @NotBlank(message = "El id de la clase de esta lección es obligatorio")
    @Size(min = 32, max = 40, message = "El id de la clase debe tener entre 32 y 40 caracteres")
    private Long classEntityId;
    @NotBlank(message = "El id de la multimedia de esta lección es obligatorio")
    @Size(min = 32, max = 40, message = "El id del contenido multimedia debe tener entre 32 y 40 caracteres")
    private Long multimediaId;
}
