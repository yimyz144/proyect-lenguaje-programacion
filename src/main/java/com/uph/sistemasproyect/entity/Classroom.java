package com.uph.sistemasproyect.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "classroom")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "number")
    private String number;

}
