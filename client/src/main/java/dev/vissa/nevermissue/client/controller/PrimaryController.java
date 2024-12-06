package dev.vissa.nevermissue.client.controller;

import java.io.IOException;

import dev.vissa.nevermissue.client.JavaFXApp;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        JavaFXApp.setRoot("secondary");
    }
}
