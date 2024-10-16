package dev.vissa.nevermissue.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issueID")
	private Integer ID;
	//@Column(name = "projectID")
	private Integer projectID;
	@Column(name = "displayName")
	private String name;
	@Column(name = "displayDescription")
	private String description;
	/*
	private List<Task> tasks;*/
	
	public Issue(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public Issue() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	*/
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getProjectID() {
		return projectID;
	}
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
	
	
	
}
