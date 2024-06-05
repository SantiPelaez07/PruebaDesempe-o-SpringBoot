package com.riwi.Examen.api.dto.response.BasicResponse;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentBasicResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private boolean active;
}
