package com.riwi.Examen.api.dto.response.primaryResponse;

import java.time.LocalDateTime;
import java.util.List;

import com.riwi.Examen.api.dto.response.BasicResponse.ClassBasicResponse;
import com.riwi.Examen.api.dto.response.BasicResponse.MultimediaBasicResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private boolean active;
    private ClassBasicResponse classEntity;
    private List<MultimediaBasicResponse> multimedias;
}
