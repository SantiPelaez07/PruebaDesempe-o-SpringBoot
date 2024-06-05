package com.riwi.Examen.infrastructure.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.Examen.api.dto.request.LessonRequest;
import com.riwi.Examen.api.dto.response.BasicResponse.ClassBasicResponse;
import com.riwi.Examen.api.dto.response.BasicResponse.MultimediaBasicResponse;
import com.riwi.Examen.api.dto.response.primaryResponse.LessonResponse;
import com.riwi.Examen.domain.entities.ClassEntity;
import com.riwi.Examen.domain.entities.Lesson;
import com.riwi.Examen.domain.entities.Multimedia;
import com.riwi.Examen.domain.repositories.LessonRepository;
import com.riwi.Examen.infrastructure.abstractService.ILessonService;
import com.riwi.Examen.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    private final LessonRepository lessonRepository;

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = this.requestToEntity(request);
        lesson.setMultimedias(new ArrayList<>());
        lesson.setClassEntity(new ClassEntity());
        lesson.setCreated_at(LocalDateTime.now());
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.lessonRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public LessonResponse update(LessonRequest request, Long id) {
        Lesson lesson = this.getById(id);
        Lesson lessonUpdate = this.requestToEntity(request);
        lessonUpdate.setId(id);
        lessonUpdate.setClassEntity(lesson.getClassEntity());
        lessonUpdate.setMultimedias(lesson.getMultimedias());
        return this.entityToResponse(this.lessonRepository.save(lessonUpdate));
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = this.getById(id);
        this.lessonRepository.delete(lesson);
    }

    private Lesson getById(Long id) {
        return this.lessonRepository.findById(id).orElseThrow();
    }

    private Lesson requestToEntity(LessonRequest request) {
        return Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .active(request.isActive())
                .build();
    }

    private LessonResponse entityToResponse(Lesson entity) {
        ClassBasicResponse classBasic = new ClassBasicResponse();
        BeanUtils.copyProperties(entity, classBasic);

        List<MultimediaBasicResponse> multimedias = entity.getMultimedias()
                .stream()
                .map(this::entityToResponseMultimedia)
                .collect(Collectors.toList());

        return LessonResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .classEntity(classBasic)
                .multimedias(multimedias).build();

    }


    private MultimediaBasicResponse entityToResponseMultimedia(Multimedia entity) {
        return MultimediaBasicResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .url(entity.getUrl())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .build();
    }

    @Override
    public LessonResponse findById(Long id) {
        return this.entityToResponse(this.getById(id));
    }

}
