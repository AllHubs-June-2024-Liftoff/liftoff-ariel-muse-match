package org.launchcode.TheGitWits.MuseMatch.repository.images;


import org.launchcode.TheGitWits.MuseMatch.models.user.image.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;


@Repository
public interface ImageRepository extends JpaRepository<ProfilePicture, Long> {

}
