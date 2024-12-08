package dev.vissa.nevermissue.client.controller;

import java.io.IOException;

import dev.vissa.nevermissue.client.JavaFXApp;
import dev.vissa.nevermissue.client.connection.RuntimeSession;
import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.Response;
import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.communication.Response.RespondResult;
import dev.vissa.nevermissue.shared.connection.Session;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.entities.UserLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController extends MenuBarController{
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField passwordFieldRepeat;
	
	@FXML
	private Label errorField;
	
	
	@FXML
	private void register() {
		boolean success = true; // TODO, DB connection
		String login = loginField.getText();
		System.out.println(login);
		String password = passwordField.getText();
		String passwordRepeat = passwordFieldRepeat.getText();
		
		if (! password.equals(passwordRepeat)) {
			errorField.setText("Пароли не совпадают.");
			return;
		}
		UserLogin userLogin = new UserLogin(login, password);
		Session session = RuntimeSession.getSession();
		Request<UserLogin> request = new Request<UserLogin>(RequestType.REGISTER, userLogin);
		session.getConnection().send(request.toString());
		Response<User> response = null;
		try {
			response = Response.fromString(session.getConnection().recieve(), User.class);
			if (response.getResult() != RespondResult.OK || response.getData() == null) {
				success = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			success = false;
		}
		
		if (success) {
			JavaFXApp.switchScene("Projects");
			System.out.println(response.getData());
		}
		else {
			errorField.setText("Неправильный логин или пароль.");

		}
	}
	
	@FXML
	private void toLogin() {
		JavaFXApp.switchScene("login");
	}
}
