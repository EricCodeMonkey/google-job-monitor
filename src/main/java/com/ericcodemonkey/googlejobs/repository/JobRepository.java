package com.ericcodemonkey.googlejobs.repository;

import com.ericcodemonkey.googlejobs.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, String> {
}
