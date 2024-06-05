package com.riwi.Examen.api.dto.response.primaryResponse;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private boolean active;
}
