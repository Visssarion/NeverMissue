module dev.vissa.nevermissue.client {
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.vissa.nevermissue.client to javafx.fxml;
    exports dev.vissa.nevermissue.client;
}
