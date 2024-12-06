package dev.vissa.nevermissue.shared.entities;

import java.util.List;

import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalClass;
import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalField;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@BidirectionalClass
@Entity
@Table(name = "Projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectID")
	private Integer id;
	@Column(name = "displayName", length = 64)
	private String name;
	@Column(name = "displayDescription")
	private String description;
	
	@BidirectionalField
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorID")
	private User author;	
	
	@BidirectionalField
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Issue> issues;
	@BidirectionalField
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Label> labels;

	
	
	@BidirectionalField
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AllowedUsers",
			joinColumns = {@JoinColumn(name = "projectID")},
			inverseJoinColumns = { @JoinColumn(name = "userID") }
			)
	private List<User> allowedUsers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<User> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(List<User> allowedUsers) {
		this.allowedUsers = allowedUsers;
	}
	
	
}
