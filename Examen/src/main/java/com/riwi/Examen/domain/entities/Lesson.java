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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "lesson")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    @Lob
    private String content;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

    // Recibimos la relación con clase
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId", referencedColumnName = "id")
    private ClassEntity classEntity;

    // Creamos la relación con multimedia
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "lesson", orphanRemoval = false)
    private List<Multimedia> multimedias;
}
