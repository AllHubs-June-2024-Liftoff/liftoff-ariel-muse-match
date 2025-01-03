package com.gw.backend.repository;

import com.gw.backend.models.user.UserPreferences;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {

    // JPQL query to get distinct art movements by userId
    @Query("SELECT DISTINCT up.artMovement FROM UserPreferences up WHERE up.user.id = :userId")
    List<String> getDistinctArtMovementByUserId(Long userId);

    // JPQL query to get count of a specific art movement by userId
    @Query("SELECT COUNT(up) FROM UserPreferences up WHERE up.user.id = :userId AND up.artMovement = :artMovement")
    Long getCountArtMovementByUserId(Long userId, String artMovement);
}
