package com.uph.sistemasproyect.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "number_street")
    private String numberStreet;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "code_postal")
    private String codePostal;

}
