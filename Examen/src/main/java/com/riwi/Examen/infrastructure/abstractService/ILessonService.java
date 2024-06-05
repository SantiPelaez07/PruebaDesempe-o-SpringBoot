package com.riwi.Examen.infrastructure.abstractService;

import com.riwi.Examen.api.dto.request.LessonRequest;
import com.riwi.Examen.api.dto.response.primaryResponse.LessonResponse;

public interface ILessonService extends ICrudService<LessonRequest, LessonResponse, Long> {
    public final String FIELD_BY_SORT = "title";
}
