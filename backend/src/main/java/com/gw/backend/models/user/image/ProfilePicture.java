package com.gw.backend.models.user.image;


import com.gw.backend.models.abstraction.AbstractIdentifiableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class ProfilePicture extends AbstractIdentifiableModel {

    @Lob
    @Column(name = "content", columnDefinition = "BLOB")
    byte[] content;

    @Column(name = "name", nullable = false)
    String name;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
