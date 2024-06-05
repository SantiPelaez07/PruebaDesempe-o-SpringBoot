package com.riwi.Examen.infrastructure.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.Examen.api.dto.request.StudentRequest;
import com.riwi.Examen.api.dto.response.BasicResponse.ClassBasicSecundaryResponse;
import com.riwi.Examen.api.dto.response.primaryResponse.StudentResponse;
import com.riwi.Examen.domain.entities.Student;
import com.riwi.Examen.domain.repositories.StudentRepository;
import com.riwi.Examen.infrastructure.abstractService.IStudentService;
import com.riwi.Examen.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = this.requestToEntity(request);
        student.setCreated_at(LocalDateTime.now());
        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public Page<StudentResponse> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.studentRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    public StudentResponse findById(Long id){
        return this.entityToResponse(this.getById(id));
    }

    @Override
    public StudentResponse update(StudentRequest request, Long id) {
        Student student = this.getById(id);
        Student studentUpdate = this.requestToEntity(request);
        studentUpdate.setId(id);
        studentUpdate.setClassEntity(student.getClassEntity());

        return this.entityToResponse(this.studentRepository.save(studentUpdate));

    }

    @Override
    public void delete(Long id) {
        Student student = this.getById(id);
        this.studentRepository.delete(student);    
    }

    private Student getById(Long id){
        return (Student) this.studentRepository.findById(id).orElseThrow();
    }

    private Student requestToEntity(StudentRequest request){
        return Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .active(request.isActive()).build();
    }

    private StudentResponse entityToResponse(Student entity){
        ClassBasicSecundaryResponse classBasicSecundary = new ClassBasicSecundaryResponse();
        BeanUtils.copyProperties(entity, classBasicSecundary);

        return StudentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .created_at(entity.getCreated_at())
                .active(entity.isActive())
                .classEntity(classBasicSecundary).build();
    }
}
