package dev.vissa.nevermissue.shared.entities;

import java.util.List;

import dev.vissa.nevermissue.shared.gson.annotations.BidirectionalClass;
import dev.vissa.nevermissue.shared.gson.annotations.SecretField;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@BidirectionalClass
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	protected Integer id;
	@Column(name = "login", length = 64)
	protected String login;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	protected List<Role> roles;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	protected List<Project> createdProjects;
	
	@ManyToMany(mappedBy = "allowedUsers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	protected List<Project> allowedProjects;

	@SecretField(neverShow = false)
	@Column(name = "passwordHash")
	byte[] passwordHash;
	@SecretField(neverShow = false)
	@Column(name = "salt")
	byte[] salt;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Project> getCreatedProjects() {
		return createdProjects;
	}

	public void setCreatedProjects(List<Project> createdProjects) {
		this.createdProjects = createdProjects;
	}

	public List<Project> getAllowedProjects() {
		return allowedProjects;
	}

	public void setAllowedProjects(List<Project> allowedProjects) {
		this.allowedProjects = allowedProjects;
	}
	
	public byte[] getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
}
