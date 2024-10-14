module dev.vissa.client {
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.vissa.client to javafx.fxml;
    exports dev.vissa.client;
}
