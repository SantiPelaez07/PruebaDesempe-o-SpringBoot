package com.riwi.Examen.api.dto.request;

import java.time.LocalDateTime;

import com.riwi.Examen.domain.entities.ClassEntity;

import jakarta.validation.constraints.Email;
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
public class StudentRequest {
    @NotBlank(message = "El nombre del estudiante es obligatorio")
    @Size(min = 20, max = 90, message = "El nombre del estudiante debe tener entre 20 y 90 caracteres")
    private String name;
    @NotBlank(message = "El email del estudiante s obligatorio")
    @Size(min = 30, max = 70, message = "El email del estudiante debe tener entre 30 y 70 caracteres")
    @Email
    private String email;
    @NotBlank(message = "La fecha y hora de inscripción del estudinte es obligatoria")
    private LocalDateTime created_at;
    @NotBlank(message = "El estado del estudiante es obligatorio")
    private boolean active; 
    @NotBlank(message = "El id de la clase a la que pertenece el estudiante es obligatorio")
    @Size(min = 32, max = 40, message = "El id de la clase debe contener entre 32 y 40 caracteres")
    private ClassEntity classEntity;
}
