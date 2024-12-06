package dev.vissa.nevermissue.server.service;

import java.util.Arrays;

import dev.vissa.nevermissue.server.authorization.PasswordHasher;
import dev.vissa.nevermissue.server.dao.HibernateDAO;
import dev.vissa.nevermissue.server.dao.JpaDAO;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.entities.UserLogin;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserService {
	JpaDAO<User, Integer> dao = new HibernateDAO<User, Integer>(User.class);
	
	/**
	 * Returns User if UserLogin is correct, null if not.
	 */
	public User authorize(UserLogin userLogin) {
		User user = findByLogin(userLogin.getLogin());
		if (user == null) { return null; }
		if (PasswordHasher.isCorrectPassword(
				userLogin.getPassword(),
				user.getSalt(), user.getPasswordHash())) {
			return user;
		}
		return null;
	}
	
	public User findByLogin(String login) {
		CriteriaBuilder cb = dao.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get("login"), login));
		return dao.getCriteriaQuerySigleResult(cq);
	}
	
	public User createFromLoginInfo(UserLogin userLogin) {
		User user = new User();
		user.setLogin(userLogin.getLogin());
		byte[] salt = PasswordHasher.generateSalt();
		byte[] password = PasswordHasher.getHash(userLogin.getPassword(), salt);
		user.setSalt(salt);
		user.setPasswordHash(password);
		return user;
	}
	
	public User registerUser(UserLogin userLogin) {
		User user = createFromLoginInfo(userLogin);
		dao.save(user);
		return user;
	}
	
	public void deleteUser(User user) {
		dao.delete(user);
	}
	
	public User getUser(Integer id) {
		User user = dao.get(id);
		return user;
	}
	
	
}