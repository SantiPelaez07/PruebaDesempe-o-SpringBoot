package com.riwi.Examen.domain.entities;

import java.time.LocalDateTime;

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

@Entity(name = "student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 90, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

    //Recibimos la relación con clase
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId", referencedColumnName = "id") 
    private ClassEntity classEntity;
}
