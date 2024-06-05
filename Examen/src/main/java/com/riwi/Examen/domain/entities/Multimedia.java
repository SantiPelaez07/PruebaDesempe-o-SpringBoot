package com.riwi.Examen.domain.entities;

import java.time.LocalDateTime;

import com.riwi.Examen.utils.enums.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "multimedia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Type type;
    @Column(length = 150, nullable = false)
    private String url;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

    //Recibimos la relación con las lecciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId", referencedColumnName = "id") 
    private Lesson lesson;
}
