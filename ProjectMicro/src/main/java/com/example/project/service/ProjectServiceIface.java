package com.example.project.service;

import java.util.List;


import com.example.project.entity.BugEntity;
import com.example.project.exception.BugNotFoundException;

public interface ProjectServiceIface {
	
	List<BugEntity> getBugsForProject(int projectid) throws BugNotFoundException;

}
