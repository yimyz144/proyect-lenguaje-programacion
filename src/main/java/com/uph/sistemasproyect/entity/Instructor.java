package com.uph.sistemasproyect.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "instructor")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "legal_identifier", unique = true)
    private String legalIdentifier;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "profession")
    private String profession;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_address")
    private Address address;
}
