package com.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracker.entity.BugEntity;
import com.bugtracker.service.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

//	public ProjectController(ProjectService projectService) {
//		this.projectService = projectService;
//	}

//	@GetMapping("/{projectId}/bugs")
//	public ResponseEntity<List<BugEntity>> getBugsForProject(@PathVariable("projectId") Integer projectId) {
//		List<BugEntity> bugs = projectService.getBugsForProject(projectId);
//		return new ResponseEntity<>(bugs, HttpStatus.OK);
//	}
	
	@GetMapping("/bugs/{projectid}")
	public ResponseEntity<List<BugEntity>> getBugsForProject(@PathVariable int projectid) {
		List<BugEntity> bugs = projectService.getBugsForProject(projectid);
		return new ResponseEntity<>(bugs, HttpStatus.OK);
	}

}
