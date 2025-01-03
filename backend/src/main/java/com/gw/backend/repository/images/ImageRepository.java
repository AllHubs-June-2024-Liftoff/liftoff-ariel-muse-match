package com.gw.backend.repository.images;


import com.gw.backend.models.user.image.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ProfilePicture, Long> {

}
