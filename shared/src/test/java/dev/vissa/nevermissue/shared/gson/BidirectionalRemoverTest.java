package dev.vissa.nevermissue.shared.gson;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dev.vissa.nevermissue.shared.communication.Response;
import dev.vissa.nevermissue.shared.communication.Response.RespondResult;
import dev.vissa.nevermissue.shared.entities.Issue;
import dev.vissa.nevermissue.shared.entities.Project;
import dev.vissa.nevermissue.shared.entities.Task;
import dev.vissa.nevermissue.shared.entities.User;

public class BidirectionalRemoverTest {
	@Test
	public void test() {
		User user = new User();
		user.setLogin("UserName");
		Project project = new Project();
		user.setCreatedProjects(new ArrayList<Project>());
		user.getCreatedProjects().add(project);
		user.getCreatedProjects().add(project);
		project.setIssues(new ArrayList<Issue>());
		project.setName("ProjectName");
		project.setAuthor(user);
		Issue issue = new Issue();
		issue.setName("IssueName");
		issue.setProject(project);
		project.getIssues().add(issue);
		project.getIssues().add(issue);
		Task task = new Task();
		task.setDescription("TaskDescription");
		task.setIssue(issue);
		issue.setTask(new ArrayList<Task>());
		issue.getTask().add(task);
		issue.getTask().add(task);
		
		
				
		Response<Project> response = new Response<Project>(RespondResult.OK, project);
		
		System.out.println(response.toString());
	}
}
