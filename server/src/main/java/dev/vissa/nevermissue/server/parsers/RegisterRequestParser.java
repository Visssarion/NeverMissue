package dev.vissa.nevermissue.server.parsers;

import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.communication.Response.RespondResult;

import org.hibernate.exception.ConstraintViolationException;

import dev.vissa.nevermissue.server.database.Database;
import dev.vissa.nevermissue.server.service.UserService;
import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.RequestParserTyped;
import dev.vissa.nevermissue.shared.communication.Response;
import dev.vissa.nevermissue.shared.connection.Session;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.entities.UserLogin;

public class RegisterRequestParser extends RequestParserTyped {

	@Override
	protected void run(String data, Session session) {
		Database.getCurrentSession().beginTransaction();
		Request<UserLogin> request = Request.fromString(data, UserLogin.class);
		UserService userService = new UserService();
		User user = null;
		Response<User> response;
		try {
			user = userService.registerUser(request.getArgument());
			response = new Response<User>(RespondResult.OK, user);
			Database.getCurrentSession().getTransaction().commit();
		}
		catch (ConstraintViolationException e) {
			response = new Response<User>(RespondResult.ERROR, null, "User could not be created");
			Database.getCurrentSession().getTransaction().rollback();
		}
		session.setUser(user);
		session.getConnection().send(response.toString());
		return;
	}

	@Override
	protected boolean isCorrectAction(RequestType action) {
		return action == RequestType.REGISTER;
	}

}
