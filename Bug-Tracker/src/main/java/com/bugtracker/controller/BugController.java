package com.bugtracker.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracker.entity.BugEntity;
import com.bugtracker.model.BugRequest;
import com.bugtracker.model.BugVO;
import com.bugtracker.service.BugServiceIface;

//Aneetaaaaaaaa love!!!

@RestController
@RequestMapping("api/v1/bugs")
public class BugController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BugController.class);

	@Autowired
	private BugServiceIface bugService;

	// Fetching all Bugs.
	@GetMapping
	@RequestMapping("/all")
	public ResponseEntity<List<BugVO>> getBugs() {
		LOGGER.info("Inside BugController and calling the getBugs method ...");
		List<BugVO> bugVOS = null;

		try {
			bugVOS = bugService.findAll();
			LOGGER.info("Bug response :{}", bugVOS);

			if (CollectionUtils.isEmpty(bugVOS)) {
				LOGGER.info("No Bugs found !!!");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error(" Error while fetching Bug details !!! ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<BugVO>>(bugVOS, HttpStatus.OK);
	}

	// Get a bug by ID.
	// http://localhost:9090/api/v1/bugs/1
	// http://localhost:9090/api/v1/bugs?id=1&name=bugname
	@GetMapping("/{id}")
	public ResponseEntity<BugVO> getBugById(@PathVariable("id") int id) {
		LOGGER.info("Inside BugController and calling the getBugById method ...");
		BugVO bugVO = null;
		try {
			bugVO = bugService.findId(id);
			LOGGER.info("Bug response:{}", bugVO);
			if (bugVO == null) {
				LOGGER.info("Bug details are not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception while calling getBugs", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<BugVO>(bugVO, HttpStatus.OK);
	}
	
	//Get a bug by name
	@GetMapping("/name/{name}")
	public ResponseEntity<List<BugVO>> getBugsByName(@PathVariable("name") String name) {
	    LOGGER.info("Inside BugController and calling the getBugsByName method ...");
	    List<BugVO> bugVOList = null;
	    try {
	        bugVOList = bugService.findName(name);
	        LOGGER.info("Bug response: {}", bugVOList);
	        if (bugVOList.isEmpty()) {
	            LOGGER.info("No bugs found with the given name");
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception ex) {
	        LOGGER.error("Exception while calling getBugsByName", ex);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	    return ResponseEntity.ok(bugVOList);
	}
	
	// Find a Bug by status.
		@GetMapping("/status/{status}")
		public ResponseEntity<List<BugVO>> getBugByStatus(@PathVariable("status") String status) {
		    LOGGER.info("Inside the BugController.getBugByStatus");
		    List<BugVO> bugVOList = null;
		    try {
		        bugVOList = bugService.findByStatus(status);

		        LOGGER.info("Bug response: {}", bugVOList);
		        if (bugVOList.isEmpty()) {
		            LOGGER.info("Bug details are not found");
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    } catch (Exception ex) {
		        LOGGER.error("Exception while calling getBugByStatus", ex);
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		    return new ResponseEntity<>(bugVOList, HttpStatus.OK);
		}

	// Create a new bug.
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<BugVO> save(@RequestBody BugRequest bugRequest) {
		LOGGER.info("Inside BugController.save and bugVO;{}", bugRequest);
		if (bugRequest == null) {
			LOGGER.info("Invalid Bug request");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		BugVO bugVO = null;
		try {
			bugVO = bugService.save(bugRequest);
			if (bugVO == null) {
				LOGGER.info("Bug details are not saved");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
			LOGGER.error("Exception while saving bug");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BugVO>(bugVO, HttpStatus.OK);
	}

	// Update an existing Bug..
	@PutMapping
	@RequestMapping("/update")
	public ResponseEntity<BugVO> update(@RequestBody BugRequest bugRequest) {
		LOGGER.info("Inside BugController.update and bugVO;{}", bugRequest);
		if (bugRequest == null) {
			LOGGER.info("Invalid Bug request");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		BugVO bugVO = null;
		try {
			bugVO = bugService.save(bugRequest);
			if (bugVO == null) {
				LOGGER.info("Bug details are not updated");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
			LOGGER.error("Exception while updating the bug");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<BugVO>(bugVO, HttpStatus.OK);
	}

	// Delete an existing bug.
	@DeleteMapping("/delete/{id}")
//	@RequestMapping("/delete/{id}")
//	public ResponseEntity<String> delete(@RequestParam("id") int id) {
	public ResponseEntity<String> delete(@PathVariable int id) {
		LOGGER.info("Input to BugController.delete, id:{}", id);
		String response = null;
		try {
			response = bugService.delete(id);
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting bug");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	// Update the status
	@PutMapping("/status/{id}")
	public ResponseEntity<String> updateBugStatus(@PathVariable("id") int id) {
		String updated_status = "closed";
	    LOGGER.info("Inside BugController.updateBugStatus for bug ID: {} and new status: {}", id, updated_status);
	    try {
	        BugVO bugVO = bugService.findId(id);
	        if (bugVO == null) {
	            LOGGER.info("Bug not found with ID: {}", id);
	            return new ResponseEntity<>("Bug not found", HttpStatus.NOT_FOUND);
	        }
	        
	        bugVO.setStatus(updated_status);
	        bugService.updateBugStatus(bugVO); 

	        LOGGER.info("Bug status updated successfully. Bug ID: {}, New Status: {}", id,updated_status);
	        return new ResponseEntity<>("Bug status updated successfully", HttpStatus.OK);
	    } catch (Exception ex) {
	        LOGGER.error("Exception while updating bug status", ex);
	        return new ResponseEntity<>("Failed to update bug status", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
