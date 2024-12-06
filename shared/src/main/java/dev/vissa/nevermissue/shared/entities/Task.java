package dev.vissa.nevermissue.shared.entities;

import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalClass;
import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalField;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@BidirectionalClass
@Entity
@Table(name = "Tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskID")
	private Integer id;
	@Column(name = "displayDescription")
	private String description;
	//private String name;
	//private Integer priority;
	@BidirectionalField
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issueID")
	private Issue issue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	
}
