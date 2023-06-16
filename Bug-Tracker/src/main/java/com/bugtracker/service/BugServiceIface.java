package com.bugtracker.service;

import java.util.List;

import com.bugtracker.exception.BugNotFoundException;
import com.bugtracker.model.BugRequest;
import com.bugtracker.model.BugVO;

public interface BugServiceIface {
	List<BugVO> findAll();

	BugVO findId(int id) throws BugNotFoundException;

	BugVO save(BugRequest bugRequest) throws BugNotFoundException;

	String delete(int id) throws BugNotFoundException;

	List<BugVO> findByStatus(String status) throws BugNotFoundException;

//	List<BugVO> findMyBugByStatus(String status) throws BugNotFoundException;

	List<BugVO> findName(String name) throws BugNotFoundException;

	void updateBugStatus(BugVO bugVO) throws BugNotFoundException;

}
