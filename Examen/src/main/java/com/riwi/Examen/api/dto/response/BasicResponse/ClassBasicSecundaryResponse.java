package com.riwi.Examen.api.dto.response.BasicResponse;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassBasicSecundaryResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private boolean active;
    private List<LessonBasicResponse> lessons;
}
