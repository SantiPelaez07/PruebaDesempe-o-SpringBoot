package com.riwi.Examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.Examen.domain.entities.ClassEntity;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long>{
    
}
