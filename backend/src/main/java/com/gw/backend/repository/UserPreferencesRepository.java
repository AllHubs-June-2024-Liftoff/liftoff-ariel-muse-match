package com.gw.backend.repository;

import com.gw.backend.models.UserPreferences;
import org.springframework.data.repository.CrudRepository;

public interface UserPreferencesRepository extends CrudRepository<UserPreferences, Long> {
}
