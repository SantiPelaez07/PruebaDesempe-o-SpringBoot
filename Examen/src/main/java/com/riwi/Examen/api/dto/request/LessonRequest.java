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
public class LessonRequest {
    @NotBlank(message = "El titulo de la lección es obligatorio")
    @Size(min = 5, max = 40, message = "El titulo de la lección debe tener entre 5 y 40 caracteres")
    private String title;
    @NotBlank(message = "El contenido de la lección es obligatorio")
    @Size(min = 10, max = 500, message = "El contenido de la lección debe tener entre 10 y 500 caracteres")
    private String content;
    private boolean active;
    private Long classEntityId;

}
