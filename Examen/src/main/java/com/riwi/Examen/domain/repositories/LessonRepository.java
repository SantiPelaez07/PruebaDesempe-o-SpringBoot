package com.riwi.Examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.Examen.domain.entities.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>{

}
