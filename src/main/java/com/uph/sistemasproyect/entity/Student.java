package com.uph.sistemasproyect.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "student")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "dni", unique = true)
    private String dni;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_address")
    private Address address;

}
