package com.riwi.Examen.api.dto.response.BasicResponse;

import java.time.LocalDateTime;

import com.riwi.Examen.utils.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaBasicResponse {
    private Long id;
    private Type type;
    private String url;
    private LocalDateTime created_at;
    private boolean active;
}
