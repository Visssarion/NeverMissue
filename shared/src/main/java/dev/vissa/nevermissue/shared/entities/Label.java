package dev.vissa.nevermissue.shared.entities;

import java.util.List;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "Labels")
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "labelID")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectID")
	private Project project;
	@Column(name = "displayName", length = 64)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "IssueLabels",
			joinColumns = {@JoinColumn(name = "labelID")},
			inverseJoinColumns = { @JoinColumn(name = "issueID") }
			)
	private List<Issue> issues;
}
