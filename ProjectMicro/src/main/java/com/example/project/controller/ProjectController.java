package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.BugEntity;
import com.example.project.exception.BugNotFoundException;
import com.example.project.service.ProjectService;


@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	
	@GetMapping("/bugs/{projectid}")
	public ResponseEntity<List<BugEntity>> getBugsForProject(@PathVariable int projectid) throws BugNotFoundException {
		List<BugEntity> bugs = projectService.getBugsForProject(projectid);
		return new ResponseEntity<>(bugs, HttpStatus.OK);
	}

}
