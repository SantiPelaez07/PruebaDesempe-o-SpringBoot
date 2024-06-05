package com.riwi.Examen.infrastructure.abstractService;

import com.riwi.Examen.api.dto.request.ClassRequest;
import com.riwi.Examen.api.dto.response.primaryResponse.ClassResponse;

public interface IClassService extends ICrudService<ClassRequest, ClassResponse, Long>{
    public final String FIELD_BY_SORT = "name";
}
