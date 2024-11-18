package dev.vissa.nevermissue.server.database;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Timeout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.vissa.nevermissue.server.authorization.PasswordHasher;
import dev.vissa.nevermissue.shared.entities.Issue;
import dev.vissa.nevermissue.shared.entities.Project;
import dev.vissa.nevermissue.shared.entities.Role;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.gson.BidirectionalExclusionStrategy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@TestInstance(Lifecycle.PER_CLASS)
// @Disabled // @Ignore
public class DatabaseTest {
	
	Session session;
	Transaction transaction;
	
	@BeforeAll // @BeforeClass
	@Timeout(unit = TimeUnit.SECONDS, value = 10) // @Test(timeout = 10000)
	public void connectionTest() {
		assertNull(Database.getSessionFactory());
		Database.startup();
		assertNotNull(Database.getSessionFactory());
	}
	
	@BeforeEach // @Before
	public void openSession() {
		session = Database.getCurrentSession();
		transaction = session.beginTransaction();
	}
	
	@AfterEach
	public void closeSession() {
		assertNotNull(transaction);
		assertNotNull(session);
		assertTrue(session.isOpen());
		transaction.rollback();
		transaction = null;
		session.close();
		session = null;
	}
	
	@AfterAll
	public void disconnectTest() {
		Database.shutdown();
	}
	
	private User createSampleUser() {
		User user = new User();
		user.setLogin("testuser");
		user.setSalt(PasswordHasher.generateSalt());
		user.setPasswordHash(PasswordHasher.getHash("testpass", user.getSalt()));
		return user;
	}
	
	@Test
	public void userManipulationTest() {
		User user = createSampleUser();
		session.persist(user);
		
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);
		Root<User> root = cr.from(User.class);
		cr.select(root).where(cb.like(root.get("login"), "testuser"));
		Query<User> query = session.createQuery(cr);
		List<User> results = query.getResultList();
		
		assertEquals(results.size(), 1);
		
		user = results.get(0);
		
		byte[] hash = PasswordHasher.getHash("testpass", user.getSalt());
		
		assertArrayEquals(hash, user.getPasswordHash(), "Passwords do not match");
		
		user.setLogin("switcheroo");
		session.persist(user);
		
		cr =  cb.createQuery(User.class);
		root = cr.from(User.class);
		cr.select(root).where(cb.like(root.get("login"), "switcheroo"));
		query = session.createQuery(cr);
		results = query.getResultList();
		
		assertEquals(results.size(), 1);

		session.remove(results.get(0));
		
		cr =  cb.createQuery(User.class);
		root = cr.from(User.class);
		cr.select(root).where(cb.like(root.get("login"), "switcheroo"));
		query = session.createQuery(cr);
		results = query.getResultList();

		assertEquals(results.size(), 0);	
	}
	
	
	
	
	@Nested
	class UserTest{
		User user;
		
		@BeforeEach
		public void userCreation() {
			user = createSampleUser();
		}
		
		@Test
		// assertThrows(type, executable) => @Test(expected = type)
		public void nullInsertTest() {		
			user.setLogin(null);
			assertThrows(ConstraintViolationException.class,
					() -> {session.persist(user);});

		}
		
		@Test
		public void uniqueInsertTest() {		
			session.persist(user);
			User user2 = createSampleUser();
			assertThrows(ConstraintViolationException.class,
					() -> {session.persist(user2);});
		}
		
		@Nested
		class RolesTest{
			@Test
			public void userRolesOneToManyTest() {
				List<Role> roles = new ArrayList<Role>();
				Role role = new Role();
				role.setRole("Admin");
				role.setUser(user);
				roles.add(role);
				role = new Role();
				role.setRole("Manager");
				role.setUser(user);
				roles.add(role);
				user.setRoles(roles);
				session.persist(user);
			}
			
			@Test
			public void userRolesCascadeDeletionTest() {
				List<Role> roles = new ArrayList<Role>();
				Role role = new Role();
				role.setRole("Admin");
				role.setUser(user);
				roles.add(role);
				user.setRoles(roles);
				session.persist(user);
				assertNotNull(session.get(Role.class, role.getId()));
				session.remove(user);
				assertNull(session.get(Role.class, role.getId()));
			}
		}
	
		@Nested
		class ProjectTest{
			
			Project project;
			
			private Project createSampleProject(User user) {
				Project project = new Project();
				if(user.getCreatedProjects() == null) {
					user.setCreatedProjects(new ArrayList<Project>());
				}
				user.getCreatedProjects().add(project);
				project.setAuthor(user);
				project.setName("Sample Project");
				project.setDescription("Sample Project Description");
				return project;
			}
			
			@BeforeEach
			public void projectCreation() {
				project = createSampleProject(user);
			}
			
			@Test
			public void projectAddTest() {
				session.persist(user);
				Project project = createSampleProject(user);
				session.persist(project);
			}
			
			@Test
			public void projectIssueTest() {
				User user = createSampleUser();
				session.persist(user);
				Project project = createSampleProject(user);
				project.setIssues(new ArrayList<Issue>());
				session.persist(project);
			}
			
			@Nested
			class IssueTest{
				Issue issue;
				
				private Issue createSampleIssue(Project project) {
					Issue issue = new Issue();
					if(project.getIssues() == null) {
						project.setIssues(new ArrayList<Issue>());
					}
					project.getIssues().add(issue);
					issue.setProject(project);
					issue.setName("Sample issue");
					issue.setDescription("Sample description");
					return issue;
				}
				
				@Test
				public void projectJsonTest() {
					session.persist(user);
					Project project = createSampleProject(user);
					session.persist(project);
					session.persist(createSampleIssue(project));
					Gson gson = new GsonBuilder().setPrettyPrinting()
							.setExclusionStrategies(new BidirectionalExclusionStrategy(true)).create();
					String json = gson.toJson(user);
					System.out.println(json);
				}
				
				@BeforeEach
				public void issueCreation() {
					issue = createSampleIssue(project);
				}
				
				@Test
				public void issueTest() {
					session.persist(user);
					session.persist(project);
					session.persist(issue);
				}
				
			}
		}
	}
	
	
	
	
}
