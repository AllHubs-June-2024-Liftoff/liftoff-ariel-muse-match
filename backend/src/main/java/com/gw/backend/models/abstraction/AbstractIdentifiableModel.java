package com.gw.backend.models.abstraction;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractIdentifiableModel {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }


}
