package com.riwi.Examen.api.dto.response.primaryResponse;

import java.time.LocalDateTime;

import com.riwi.Examen.api.dto.response.BasicResponse.ClassBasicResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private boolean active;
    private ClassBasicResponse classEntity;
}
