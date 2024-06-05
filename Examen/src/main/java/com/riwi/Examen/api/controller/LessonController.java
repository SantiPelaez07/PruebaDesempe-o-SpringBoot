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

import com.riwi.Examen.api.dto.request.LessonRequest;
import com.riwi.Examen.api.dto.response.primaryResponse.LessonResponse;
import com.riwi.Examen.infrastructure.service.LessonService;
import com.riwi.Examen.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Controller
@RestController
@RequestMapping(path = "/lesson")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    
    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAllandGetName(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.lessonService.getAll(page - 1, size, sortType));
    }

     @GetMapping(path = "/{id}")
     public ResponseEntity<LessonResponse> get(
             @PathVariable Long id) {
         return ResponseEntity.ok(this.lessonService.findById(id));
     }

    @PostMapping
    public ResponseEntity<LessonResponse> insert(
            @Validated @RequestBody LessonRequest request) {
        return ResponseEntity.ok(this.lessonService.create(request));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(
            @Validated @RequestBody LessonRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.lessonService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
