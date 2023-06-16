package com.bugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.entity.BugEntity;
import com.bugtracker.repository.BugRepository;
import com.bugtracker.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private BugRepository repo;
	
	 public List<BugEntity> getBugsForProject(int projectid) {
	        return repo.findAllByProjectId(projectid);
	    }

}
