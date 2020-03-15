package com.galvanize.repository;

import com.galvanize.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerDao extends JpaRepository<Officer, Integer> {
}
