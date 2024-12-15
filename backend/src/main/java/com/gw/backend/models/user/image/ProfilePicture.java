package org.launchcode.TheGitWits.MuseMatch.models.user.image;


import jakarta.persistence.*;
import org.launchcode.TheGitWits.MuseMatch.models.abstraction.AbstractIdentifiableModel;


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
