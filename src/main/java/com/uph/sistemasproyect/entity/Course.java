package com.uph.sistemasproyect.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code_courses")
    private UUID codeCourses;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "number_hour")
    private int numberHour;
    @Column(name = "schedule")
    private String schedule;
/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_classroom")
    private Classroom classroom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_classroom")
    private Instructor instructor;*/


}
