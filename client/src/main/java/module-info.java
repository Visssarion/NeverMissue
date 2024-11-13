module dev.vissa.nevermissue.client {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.vissa.nevermissue.client to javafx.fxml;
    exports dev.vissa.nevermissue.client;
    opens dev.vissa.nevermissue.client.controller to javafx.fxml;
}
