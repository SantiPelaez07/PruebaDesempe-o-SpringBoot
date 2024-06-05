package com.riwi.Examen.api.controller;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.Examen.api.dto.request.StudentRequest;
import com.riwi.Examen.api.dto.response.primaryResponse.StudentResponse;
import com.riwi.Examen.infrastructure.abstractService.IStudentService;
import com.riwi.Examen.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Controller
@RestController
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAllandGetName(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType,
            @PathVariable(name = "/{name}") String name) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.studentService.getAll(page - 1, size, sortType));
    }

     @GetMapping(path = "/{id}")
     public ResponseEntity<StudentResponse> get(
             @PathVariable Long id) {
         return ResponseEntity.ok(this.studentService.findById(id));
     }

    @PostMapping
    public ResponseEntity<StudentResponse> insert(
            @Validated @RequestBody StudentRequest request) {
        return ResponseEntity.ok(this.studentService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> update(
            @Validated @RequestBody StudentRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
