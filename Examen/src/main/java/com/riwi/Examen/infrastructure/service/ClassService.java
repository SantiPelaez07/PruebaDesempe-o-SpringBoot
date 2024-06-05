package com.riwi.Examen.infrastructure.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.Examen.api.dto.request.ClassRequest;
import com.riwi.Examen.api.dto.response.BasicResponse.LessonBasicResponse;
import com.riwi.Examen.api.dto.response.BasicResponse.StudentBasicResponse;
import com.riwi.Examen.api.dto.response.primaryResponse.ClassResponse;
import com.riwi.Examen.domain.entities.ClassEntity;
import com.riwi.Examen.domain.entities.Lesson;
import com.riwi.Examen.domain.entities.Student;
import com.riwi.Examen.domain.repositories.ClassRepository;
import com.riwi.Examen.infrastructure.abstractService.IClassService;
import com.riwi.Examen.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassService implements IClassService {

    private final ClassRepository classRepository;

    @Override
    public ClassResponse create(ClassRequest request) {
        ClassEntity classEntity = this.requestToEntity(request);
        classEntity.setStudents(new ArrayList<>());
        classEntity.setCreated_at(LocalDateTime.now());
        return this.entityToResponse(this.classRepository.save(classEntity));
    }


    @Override
    public Page<ClassResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.classRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public ClassResponse update(ClassRequest request, Long id) {
        ClassEntity classEntity = this.getById(id);

        ClassEntity classUpdate = this.requestToEntity(request);
        classUpdate.setId(id);
        classUpdate.setStudents(classEntity.getStudents());
        classUpdate.setLessons(classEntity.getLessons());
        return this.entityToResponse(this.classRepository.save(classEntity));
    }

    @Override
    public void delete(Long id) {
        ClassEntity classEntity = this.getById(id);
        this.classRepository.delete(classEntity);
    }

    private ClassEntity getById(Long id) {
        return this.classRepository.findById(id).orElseThrow();
    }

    // Convertimos de una request a una entidad
    private ClassEntity requestToEntity(ClassRequest request) {

        return ClassEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(request.isActive())
                .build();
    }

    private ClassResponse entityToResponse(ClassEntity entity) {

        List<StudentBasicResponse> students = entity.getStudents()
                .stream()
                .map(this::entityToResponseStudents)
                .collect(Collectors.toList());

        List<LessonBasicResponse> lessons = entity.getLessons()
                .stream()
                .map(this::entityToResponseLesson)
                .collect(Collectors.toList());

        return ClassResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .students(students)
                .lessons(lessons).build();

    }

    private StudentBasicResponse entityToResponseStudents(Student entity) {
        return StudentBasicResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .build();
    }

    private LessonBasicResponse entityToResponseLesson(Lesson entity) {
        return LessonBasicResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .build();
    }

    @Override
    public ClassResponse findById(Long id) {
        return this.entityToResponse(this.getById(id));
    }

}
