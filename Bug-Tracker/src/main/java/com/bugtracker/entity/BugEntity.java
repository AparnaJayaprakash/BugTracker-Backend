package com.bugtracker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "BUG_TBL")
public class BugEntity implements Serializable{
	private static final long serialVersionUID = 3382528179548773084L;
	
//	@ManyToOne
//	@JoinColumn(name = "project_id")
//	private Project project;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bug_id")
	private int id;
	
	private String name;

	private String type;

	@Column(name = "assigned_to")
	private String assignedTo;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "created_by")
	private String createdBy;

	private String status;
	private String description;
	@Column(name="project_id")
	private int projectId;


}
