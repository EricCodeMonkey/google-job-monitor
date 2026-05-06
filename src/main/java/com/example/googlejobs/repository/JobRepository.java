package com.example.googlejobs.repository;

import com.example.googlejobs.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, String> {
}
