package com.bugtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.entity.BugEntity;
import com.bugtracker.exception.BugNotFoundException;
import com.bugtracker.model.BugRequest;
import com.bugtracker.model.BugVO;
import com.bugtracker.repository.BugRepository;

@Service
public class BugServiceImpl implements BugServiceIface {

	private static final Logger LOGGER = LoggerFactory.getLogger(BugServiceImpl.class);

	@Autowired
	BugRepository bugRepository;

	@Override
	public List<BugVO> findAll() {

		LOGGER.info("Inside BugServiceImpl findAll method ...");
		List<BugEntity> bugs = bugRepository.findAll();
		LOGGER.info("Fetching all bugs response : {}", bugs);

		List<BugVO> bugVOS = bugs.parallelStream().map(bug -> {

			BugVO bugVO = new BugVO();

			bugVO.setId(bug.getId());
			bugVO.setName(bug.getName());
			bugVO.setStatus(bug.getStatus());
			bugVO.setType(bug.getType());
			bugVO.setAssignedTo(bug.getAssignedTo());
			bugVO.setCreatedBy(bug.getCreatedBy());
			bugVO.setCreatedOn(bug.getCreatedOn());
			bugVO.setDescription(bug.getDescription());
			bugVO.setProjectId(bug.getProjectId());

			return bugVO;
		}).collect(Collectors.toList());
		return bugVOS;
	}

	@Override
	public BugVO findId(int bugId) throws BugNotFoundException {
		LOGGER.info("Inside BugServiceImpl findById method ...");
		Optional<BugEntity> bug = bugRepository.findById(bugId);
		LOGGER.info("Fetching an bug response : {}", bug);
		if (!bug.isPresent()) {
			LOGGER.error("No such bug present !!!");
			throw new BugNotFoundException("No such bug present !!!");
		} else {
			BugVO bugVO = new BugVO();

			bugVO.setId(bug.get().getId());
			bugVO.setName(bug.get().getName());
			bugVO.setStatus(bug.get().getStatus());
			bugVO.setType(bug.get().getType());
			bugVO.setAssignedTo(bug.get().getAssignedTo());
			bugVO.setCreatedBy(bug.get().getCreatedBy());
			bugVO.setCreatedOn(bug.get().getCreatedOn());
			bugVO.setDescription(bug.get().getDescription());
			bugVO.setProjectId(bug.get().getProjectId());
			

			return bugVO;
		}
	}
	
	public List<BugVO> findName(String name) throws BugNotFoundException {
	    LOGGER.info("Inside BugServiceImpl findByName method...");
	    if (name == null) {
	        LOGGER.error("Invalid bug name: {}", name);
	        throw new BugNotFoundException("Bug name is not valid");
	    }
	    List<BugEntity> bugs = bugRepository.findByName(name);
	    LOGGER.info("Fetching bug responses: {}", bugs);
	    if (bugs.isEmpty()) {
	        LOGGER.error("No bugs found with the given name");
	        throw new BugNotFoundException("No bug found with the given name");
	    } else {
	        List<BugVO> bugVOList = new ArrayList<>();
	        for (BugEntity bug : bugs) {
	            BugVO bugVO = new BugVO();
	            bugVO.setId(bug.getId());
	            bugVO.setName(bug.getName());
	            bugVO.setStatus(bug.getStatus());
	            bugVO.setType(bug.getType());
	            bugVO.setAssignedTo(bug.getAssignedTo());
	            bugVO.setCreatedBy(bug.getCreatedBy());
	            bugVO.setCreatedOn(bug.getCreatedOn());
	            bugVO.setDescription(bug.getDescription());
	            bugVO.setProjectId(bug.getProjectId());
	            bugVOList.add(bugVO);
	        }
	        return bugVOList;
	    }
	}
	

	@Override
	public BugVO save(BugRequest bugRequest) throws BugNotFoundException {
		LOGGER.info("Inside the BugServiceImpl.save method and params, bugRequest:{}", bugRequest);

		if (bugRequest == null) {
			LOGGER.info("Invalid bug request");
			throw new BugNotFoundException("Invalid bug request");
		}

		BugEntity bug = new BugEntity();
		if (bugRequest.getId() > 0) {
			bug.setId(bugRequest.getId());
		}

		bug.setId(bugRequest.getId());
		bug.setName(bugRequest.getName());
		bug.setStatus(bugRequest.getStatus());
		bug.setType(bugRequest.getType());
		bug.setAssignedTo(bugRequest.getAssignedTo());
		bug.setCreatedBy(bugRequest.getCreatedBy());
		bug.setCreatedOn(bugRequest.getCreatedOn());
		bug.setDescription(bugRequest.getDescription());
		bug.setProjectId(bugRequest.getProjectId());

		BugEntity bugResponse = bugRepository.save(bug);
		BugVO bugVO = null;
		if (bugResponse != null) {
			LOGGER.info("Bug Response, bugResponse:{}", bugResponse);
			bugVO = new BugVO();

			bugVO.setId(bug.getId());
			bugVO.setName(bug.getName());
			bugVO.setStatus(bug.getStatus());
			bugVO.setType(bug.getType());
			bugVO.setAssignedTo(bug.getAssignedTo());
			bugVO.setCreatedBy(bug.getCreatedBy());
			bugVO.setCreatedOn(bug.getCreatedOn());
			bugVO.setDescription(bug.getDescription());
			bugVO.setProjectId(bug.getProjectId());
		}
		return bugVO;
	}

	// Delete an Existing bug.
	@Override
	public String delete(int id) throws BugNotFoundException {
		LOGGER.info("Input to BugServiceImpl.delete, id:{}", id);
		if (id < 0) {
			LOGGER.info("Invalid bug id");
			throw new BugNotFoundException("Invalid bug id");
		}
		try {
			bugRepository.deleteById(id);
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting bug");
			throw new BugNotFoundException("Exception while deleting bug");
		}
		return "Bug has been deleted";
	}

	@Override
	public List<BugVO> findByStatus(String status) throws BugNotFoundException {
	    LOGGER.info("Inside BugServiceImpl.findByStatus and status: {}", status);
	    List<BugVO> bugVOList = new ArrayList<>();
	    if (status == null) {
	        LOGGER.info("Invalid bug status: {}", status);
	        throw new BugNotFoundException("Bug status is not valid");
	    }
	    List<BugEntity> bugs = bugRepository.findByStatus(status);
	    LOGGER.info("Default findByStatus invoked Successfully !!!");

	    for (BugEntity bug : bugs) {
	        BugVO bugVO = new BugVO();
	        bugVO.setId(bug.getId());
	        bugVO.setName(bug.getName());
	        bugVO.setStatus(bug.getStatus());
	        bugVO.setType(bug.getType());
	        bugVO.setAssignedTo(bug.getAssignedTo());
	        bugVO.setCreatedBy(bug.getCreatedBy());
	        bugVO.setCreatedOn(bug.getCreatedOn());
	        bugVO.setDescription(bug.getDescription());
	        bugVO.setProjectId(bug.getProjectId());
	        bugVOList.add(bugVO);
	    }
	    return bugVOList;
	}

	 @Override
	    public void updateBugStatus(BugVO bugVO) throws BugNotFoundException {
	        LOGGER.info("Inside BugServiceImpl.updateBugStatus for bug ID: {} and new status: {}", bugVO.getId(), bugVO.getStatus());
	        Optional<BugEntity> bugOptional = bugRepository.findById(bugVO.getId());
	        if (!bugOptional.isPresent()) {
	            LOGGER.error("Bug not found with ID: {}", bugVO.getId());
	            throw new BugNotFoundException("Bug not found");
	        }
	        BugEntity bugEntity = bugOptional.get();
	        bugEntity.setStatus(bugVO.getStatus());
	        bugRepository.save(bugEntity);
	        LOGGER.info("Bug status updated successfully. Bug ID: {}, New Status: {}", bugVO.getId(), bugVO.getStatus());
	    }


//	@Override
//	public List<BugVO> findMyBugByStatus(String status) throws BugNotFoundException {
//		LOGGER.info("Inside BugServiceImpl.findMyBugByStatus and status: {}", status);
//		BugVO bugVO = null;
//		if (status == null) {
//			LOGGER.info("Invalid bug status:{}", status);
//			throw new BugNotFoundException("Bug status is not valid");
//		}
		//Optional<BugEntity> bug = bugRepository.findBugByStatusCustom(status);
		// Custom method called.
		//LOGGER.info("Custom findMyBugByStatus invoked Successfully !!!");

//		if (bug.isPresent()) {
//			LOGGER.info("Bug details for the status {} and the values :{}", status, bug.get());
//
//			bugVO = new BugVO();
//
//			bugVO.setId(bug.get().getId());
//			bugVO.setName(bug.get().getName());
//			bugVO.setStatus(bug.get().getStatus());
//			bugVO.setType(bug.get().getType());
//			bugVO.setAssignedTo(bug.get().getAssignedTo());
//			bugVO.setCreatedBy(bug.get().getCreatedBy());
//			bugVO.setCreatedOn(bug.get().getCreatedOn());
//			bugVO.setDescription(bug.get().getDescription());
//	 		bugVO.setProjectId(bug.get().getProjectId());
//		}
//		return bugVO;
//	}

}
