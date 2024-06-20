package com.uph.sistemasproyect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    HOSTELERIA("hosteleria"),
    INFORMATICA("informatica"),
    LIMPIEZA("limpieza");

    private String name;
}
