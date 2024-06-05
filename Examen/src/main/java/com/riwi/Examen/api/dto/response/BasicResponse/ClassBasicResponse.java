package com.riwi.Examen.api.dto.response.BasicResponse;

import java.time.LocalDateTime;
import java.util.List;

import com.riwi.Examen.api.dto.response.StudentBasicResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassBasicResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private boolean active;
}
