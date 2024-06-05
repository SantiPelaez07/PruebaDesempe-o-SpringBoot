package com.riwi.Examen.api.dto.request;




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
    @Size(min = 10, max = 90, message = "El nombre del estudiante debe tener entre 20 y 90 caracteres")
    private String name;
    @NotBlank(message = "El email del estudiante s obligatorio")
    @Size(min = 10, max = 70, message = "El email del estudiante debe tener entre 30 y 70 caracteres")
    @Email
    private String email;
    private boolean active; 
    private Long classEntity;
}
