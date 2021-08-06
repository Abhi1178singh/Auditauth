package com.cognizant.repository;

import com.cognizant.model.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<ProjectManager, String>{
}
