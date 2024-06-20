package com.uph.sistemasproyect.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "courses_student")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CoursesStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private int idCourse;

}
