package dev.vissa.nevermissue.server.parsers;

import dev.vissa.nevermissue.server.database.Database;
import dev.vissa.nevermissue.server.service.UserService;
import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.communication.RequestParserTyped;
import dev.vissa.nevermissue.shared.communication.Response;
import dev.vissa.nevermissue.shared.communication.Response.RespondResult;
import dev.vissa.nevermissue.shared.connection.Session;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.entities.UserLogin;

public class AuthorizationRequestParser extends RequestParserTyped {

	@Override
	protected void run(String data, Session session) {
		Database.getCurrentSession().beginTransaction();
		Request<UserLogin> request = Request.fromString(data, UserLogin.class);
		UserService userService = new UserService();
		User user = userService.authorize(request.getArgument());
		Response<User> response;
		if (user == null) {
			response = new Response<User>(RespondResult.ERROR, null, "Wrong password.");
			session.getConnection().send(response.toString());
			return;
		}
		else {
			response = new Response<User>(RespondResult.OK, user);
			session.setUser(user);
		}
		session.getConnection().send(response.toString());
		Database.getCurrentSession().getTransaction().commit();
		return;
	}

	@Override
	protected boolean isCorrectAction(RequestType action) {
		return action == RequestType.AUTHORIZE;
	}

}
