package org.launchcode.TheGitWits.MuseMatch.repository.user;

import org.launchcode.TheGitWits.MuseMatch.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    UserModel findByUsername (String username);

}
