package dev.vissa.nevermissue.shared.entities;

import java.util.List;

import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalClass;
import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalField;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@BidirectionalClass
@Entity
@Table(name = "Issues")
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issueID")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectID")
	@BidirectionalField
	private Project project;
	@Column(name = "displayName")
	private String name;
	@Column(name = "displayDescription")
	private String description;
	@OneToMany(mappedBy = "issue", fetch = FetchType.LAZY)
	private List<Task> task;
	@ManyToMany(mappedBy = "issues", fetch = FetchType.LAZY)
	private List<Label> labels;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
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
	public List<Task> getTask() {
		return task;
	}
	public void setTask(List<Task> task) {
		this.task = task;
	}
	public List<Label> getLabels() {
		return labels;
	}
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
}
