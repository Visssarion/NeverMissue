package dev.vissa.nevermissue.client.controller;

import dev.vissa.nevermissue.client.JavaFXApp;
import javafx.fxml.FXML;

public class MenuBarController {

	@FXML
	private void ChangeStyleToDark() {
		JavaFXApp.changeStyle(true);
	}
	
	@FXML
	private void ChangeStyleToDefault() {
		JavaFXApp.changeStyle(false);
	}
}
