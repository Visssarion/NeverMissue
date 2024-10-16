package dev.vissa.nevermissue.shared.entities;


public class Task {
	private String name;
	private Integer priority;
	
	public Task(String name, Integer priority) {
		this.name = name;
		this.priority = priority;
	}
	public Task() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	
}
