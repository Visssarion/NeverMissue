package dev.vissa.nevermissue.client.controller;

import java.io.IOException;

import dev.vissa.nevermissue.client.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}