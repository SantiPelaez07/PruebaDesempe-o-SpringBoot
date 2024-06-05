package com.riwi.Examen.infrastructure.abstractService;

import com.riwi.Examen.api.dto.request.StudentRequest;
import com.riwi.Examen.api.dto.response.primaryResponse.StudentResponse;

public interface IStudentService extends ICrudService<StudentRequest, StudentResponse, Long>{
    public final String FIELD_BY_SORT = "name";
}
