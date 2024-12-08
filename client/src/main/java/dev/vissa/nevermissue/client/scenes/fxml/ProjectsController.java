package dev.vissa.nevermissue.client.scenes.fxml;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dev.vissa.nevermissue.client.JavaFXApp;
import dev.vissa.nevermissue.client.connection.RuntimeSession;
import dev.vissa.nevermissue.client.controller.MenuBarController;
import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.communication.Response;
import dev.vissa.nevermissue.shared.communication.Response.RespondResult;
import dev.vissa.nevermissue.shared.connection.Session;
import dev.vissa.nevermissue.shared.entities.User;
import dev.vissa.nevermissue.shared.entities.UserLogin;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ProjectsController extends MenuBarController implements Initializable{
	
	@FXML
	public VBox itemContainer;
	
	@FXML
	public VBox item;
	
	
	public void initialize() {
		System.out.println("This scene loaded");
		//Item.setVisible(false);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("This scene loaded");
		item.setVisible(true);
		
		
		
		item.setOnMouseClicked(new javafx.event.EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				System.out.println("Item clicked");
				Button button = new Button();
				button.setText("Test\nTest2");
				
				itemContainer.getChildren().add(button);
				button.setPrefWidth(9999999);
				button.setMinHeight(72);
				
				
			}
		});
	}
	
	@FXML
	private void test() {
		System.out.println(item);
	}
	
//	public class ItemClicked implements EventHandler{
//
//		public ItemClicked(Object target, String action, String eventPropertyName, String listenerMethodName) {
//			super(target, action, eventPropertyName, listenerMethodName);
//			// TODO Auto-generated constructor stub
//		}
//		
//	}
}
