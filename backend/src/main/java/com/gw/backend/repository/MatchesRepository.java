package com.gw.backend.repository;

import com.gw.backend.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<UserModel, Long> {

}
