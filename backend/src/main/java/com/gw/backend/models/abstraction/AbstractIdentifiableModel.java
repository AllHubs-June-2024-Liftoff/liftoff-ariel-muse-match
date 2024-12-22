package com.gw.backend.models.abstraction;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public abstract class AbstractIdentifiableModel {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentifiableModel that = (AbstractIdentifiableModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
