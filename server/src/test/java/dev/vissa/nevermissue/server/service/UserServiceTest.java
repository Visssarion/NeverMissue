package dev.vissa.nevermissue.server.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.vissa.nevermissue.server.dao.DAO;
import dev.vissa.nevermissue.server.database.Database;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.entities.UserLogin;

@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {
	
	private Session session;
	private Transaction transaction;
	private UserService service;
	
	@BeforeAll
	@Timeout(unit = TimeUnit.SECONDS, value = 10)
	public void connectionTest() {
		assertNull(Database.getSessionFactory());
		Database.startup();
		assertNotNull(Database.getSessionFactory());
		service = new UserService();
	}
	
	@BeforeEach
	public void openSession() {
		session = Database.getCurrentSession();
		transaction = session.beginTransaction();
		System.out.println(session);
		System.out.println(transaction);
		
		
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
	
	
	@Test
	public void findUserTest() {
		service.registerUser(new UserLogin("testuser888", "testpassword"));
		User user = service.findByLogin("testuser888");
		System.out.println(user.getLogin());
		System.out.println(service.authorize(new UserLogin("testuser888", "testpassword")));
	}
}
