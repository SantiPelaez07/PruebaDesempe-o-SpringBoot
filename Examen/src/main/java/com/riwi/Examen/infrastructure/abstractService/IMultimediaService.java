package com.riwi.Examen.infrastructure.abstractService;

import com.riwi.Examen.api.dto.request.MultimediaRequest;
import com.riwi.Examen.api.dto.response.primaryResponse.MultimediaResponse;

public interface IMultimediaService extends ICrudService<MultimediaRequest, MultimediaResponse, Long>{
    public final String FIELD_BY_SORT = "type";
}
