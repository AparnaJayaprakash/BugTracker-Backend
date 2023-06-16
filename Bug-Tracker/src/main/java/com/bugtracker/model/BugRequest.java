package com.bugtracker.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugRequest {
	private int id;
	private String name;
	private String type;
	private String assignedTo;
	private Date createdOn;
	private String createdBy;
	private String status;
	private String description;
	private int projectId;

}
