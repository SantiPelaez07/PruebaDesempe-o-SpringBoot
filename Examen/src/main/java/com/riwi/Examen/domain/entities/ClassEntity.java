package com.riwi.Examen.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "class")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 500, nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

    //Creamos la relación con los estudiantes 
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "classEntity", orphanRemoval = false)
    private List<Student> students;

    //Creamos la relación con las lecciones
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "classEntity", orphanRemoval = false)
    private List<Lesson> lessons;
}
