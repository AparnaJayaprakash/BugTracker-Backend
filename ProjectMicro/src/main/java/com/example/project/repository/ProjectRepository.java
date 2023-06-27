package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	

}
