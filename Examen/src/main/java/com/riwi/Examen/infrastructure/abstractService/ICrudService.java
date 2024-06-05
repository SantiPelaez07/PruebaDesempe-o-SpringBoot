package com.riwi.Examen.infrastructure.abstractService;

import org.springframework.data.domain.Page;

import com.riwi.Examen.utils.enums.SortType;

public interface ICrudService <RQ, RS, ID>{
    public RS create(RQ request);
    public Page<RS> getAll(int page, int size, SortType sortType);
    public RS update(RQ request, Long id);
    public void delete(ID id);
    public RS findById(ID id);
}
