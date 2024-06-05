package com.riwi.Examen.infrastructure.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.Examen.api.dto.request.MultimediaRequest;
import com.riwi.Examen.api.dto.response.BasicResponse.LessonBasicResponse;
import com.riwi.Examen.api.dto.response.primaryResponse.MultimediaResponse;
import com.riwi.Examen.domain.entities.Lesson;
import com.riwi.Examen.domain.entities.Multimedia;
import com.riwi.Examen.domain.repositories.MultimediaRepository;
import com.riwi.Examen.infrastructure.abstractService.IMultimediaService;
import com.riwi.Examen.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MultimediaService implements IMultimediaService {
    

    private final MultimediaRepository multimediaRepository;

    @Override
    public MultimediaResponse create(MultimediaRequest request) {
        Multimedia multimedia = this.requestToEntity(request);
        multimedia.setLesson(new Lesson());
        return this.entityToResponse(this.multimediaRepository.save(multimedia));
    }

    @Override
    public Page<MultimediaResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.multimediaRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public MultimediaResponse update(MultimediaRequest request, Long id) {
        Multimedia multimedia = this.getById(id);
        Multimedia multimediaUpdate = this.requestToEntity(request);
        multimediaUpdate.setId(id);
        multimediaUpdate.setLesson(multimedia.getLesson());
        return this.entityToResponse(this.multimediaRepository.save(multimediaUpdate));
    }

    @Override
    public void delete(Long id) {
        Multimedia multimedia = this.getById(id);
        this.multimediaRepository.delete(multimedia);
    }

    private Multimedia getById( Long id){
        return this.multimediaRepository.findById(id).orElseThrow();
    }


    private Multimedia requestToEntity(MultimediaRequest request){
        return Multimedia.builder()
            .type(request.getType())
            .url(request.getUrl())
            .created_at(request.getCreated_at())
            .active(request.isActive()).build();
    }

    private MultimediaResponse entityToResponse(Multimedia entity){
        LessonBasicResponse lesson = new LessonBasicResponse();
        BeanUtils.copyProperties(entity, lesson);

        return MultimediaResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .url(entity.getUrl())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .active(entity.isActive())
                .lesson(lesson).build();
    }

    @Override
    public MultimediaResponse findById(Long id) {
       return this.entityToResponse(this.getById(id));
    }

}
