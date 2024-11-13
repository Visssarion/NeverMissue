package dev.vissa.nevermissue.client.controller;

import dev.vissa.nevermissue.client.App;
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
		
		System.out.println(password);
		System.out.println(passwordRepeat);
		if (! password.equals(passwordRepeat)) {
			errorField.setText("Пароли не совпадают.");
			return;
		}
		
		if (success) {
			App.switchScene("primary");
		}
		else {
			errorField.setText("Неправильный логин или пароль.");

		}
	}
	
	@FXML
	private void toLogin() {
		App.switchScene("login");
	}
}
