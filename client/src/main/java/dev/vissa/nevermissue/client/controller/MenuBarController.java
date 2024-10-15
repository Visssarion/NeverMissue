package dev.vissa.nevermissue.client.controller;

import dev.vissa.nevermissue.client.App;
import javafx.fxml.FXML;

public class MenuBarController {

	@FXML
	private void ChangeStyleToDark() {
		App.changeStyle(true);
	}
	
	@FXML
	private void ChangeStyleToDefault() {
		App.changeStyle(false);
	}
}
