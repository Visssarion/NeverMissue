package dev.vissa.nevermissue.shared.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
	//private Integer authorID;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issueID")
	private Integer projectID;
	@Column(name = "displayName")
	private String name;
	@Column(name = "displayDescription")
	private String description;
	
	@XmlElementWrapper(name = "issues")
	@XmlElement(name = "issue")
	@OneToMany
	@JoinTable(
			name = "PROJECT_ISSUES",
			joinColumns = @JoinColumn(name = ""))
	private List<Issue> issues;

	public Project(Integer projectID, String name, String description) {
		this.projectID = projectID;
		this.name = name;
		this.description = description;
	}

	public Project() {
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
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

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	
}
