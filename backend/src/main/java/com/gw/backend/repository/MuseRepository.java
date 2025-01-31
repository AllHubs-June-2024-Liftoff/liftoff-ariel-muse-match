package com.gw.backend.repository;

import com.gw.backend.models.Muse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseRepository extends JpaRepository<Muse, Long> {

}