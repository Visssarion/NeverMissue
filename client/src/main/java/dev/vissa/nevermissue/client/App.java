package dev.vissa.nevermissue.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import dev.vissa.nevermissue.client.scenes.css.CSSResources;
import dev.vissa.nevermissue.client.scenes.fxml.FXMLResources;
import dev.vissa.nevermissue.client.scenes.images.ImageResources;

/**
 * JavaFX App
 */
public class App extends Application {

	private final static String TITLE = "NeverMissue";
	
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        String css = CSSResources.class.getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.getIcons().add(new Image(ImageResources.class.getResourceAsStream("logo.png")));
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {	
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLResources.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static String DARK_CSS = CSSResources.class.getResource("darkmode.css").toExternalForm();
    
    public static void changeStyle(boolean dark) {
    	scene.getStylesheets().remove(DARK_CSS);
    	if (dark) {
    		scene.getStylesheets().add(DARK_CSS);
    	}
    	
    }

}