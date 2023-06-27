package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.project.entity.BugEntity;
import com.example.project.exception.BugNotFoundException;
import com.example.project.repository.BugRepository;



@Service
public class ProjectService {
	@Autowired
	private BugRepository repo;
	
	 public List<BugEntity> getBugsForProject(int projectid) throws BugNotFoundException {
//	        return repo.findAllByProjectId(projectid);
		 try {
		        List<BugEntity> bugs = repo.findAllByProjectId(projectid);
		        if (bugs.isEmpty()) {
		            throw new BugNotFoundException("No bugs found for project ID: " + projectid);
		        }
		        return bugs;
		    } catch (BugNotFoundException e) {
		        throw e;
		    } catch (Exception e) {
		        throw new BugNotFoundException("Failed to get bugs for the project", e);
		    }
	    }

}
