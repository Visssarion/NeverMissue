package dev.vissa.nevermissue.client.controller;

import dev.vissa.nevermissue.client.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends MenuBarController{
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Label errorField;
	
	
	@FXML
	private void login() {
		boolean success = true; // TODO, DB connection
		String login = loginField.getText();
		String password = passwordField.getText();
		if (success) {
			App.switchScene("primary");
		}
		else {
			errorField.setText("Неправильный логин или пароль.");

		}
	}
}
