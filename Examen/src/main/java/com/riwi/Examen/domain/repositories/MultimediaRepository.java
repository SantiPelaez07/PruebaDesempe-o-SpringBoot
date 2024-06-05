package com.riwi.Examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.Examen.domain.entities.Multimedia;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long>{

}
