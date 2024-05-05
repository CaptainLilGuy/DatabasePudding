package view;

import controller.UserController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;
import request.RegisterRequest;


public class RegisterPage {

	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label emailLabel, usernameLabel, passwordLabel;
	protected TextField emailTF, usernameTF;
	protected PasswordField passwordField;
	protected Button registerButton;
	protected Label loginLabel;
	protected Button loginButton;
	
	public RegisterPage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("RegisterPage");
		initialize();
		setLayout();
		setAlignment();
		
		this.scene = new Scene(borderPane,400,400);
		
	}
	
	public void initialize() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		usernameLabel = new Label("Username");
		emailLabel = new Label("Email");
		passwordLabel = new Label("Password");
		
		emailTF = new TextField();
		usernameTF = new TextField();
		passwordField = new PasswordField();
		
		registerButton = new Button("Register");
		
		EventHandler<MouseEvent> registerEvent = event -> {
			RegisterRequest req = new RegisterRequest();
			req.setUsername(usernameTF.getText());
			req.setEmail(emailTF.getText());
			req.setPassword(passwordField.getText());
			
			if((req.getEmail() != null && req.getPassword() != null && req.getUsername() != null)) {
				User user = new User(req.getUsername(), req.getEmail(), req.getPassword());
				Boolean success = UserController.insertUser(user);
				if(success) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Success Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Registered");
					alert.showAndWait();
					LoginPage loginPage = new LoginPage(stage);
					stage.setScene(loginPage.getScene());
				} else {
					showAlertError("Registration Failed!", "User already exists");
				}
			} else {
				showAlertError("Registration Failed!", "The fields are required");
			}
			
		};
		registerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, registerEvent);
		loginLabel = new Label("Already have an account yet?");
		loginButton = new Button("Login here");
		EventHandler<MouseEvent> redirectToLogin = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				LoginPage loginPage = new LoginPage(stage);
				stage.setScene(loginPage.getScene());
			}
		};
		loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, redirectToLogin);
		
	}
	
	public void setLayout() {
		gridPane.add(usernameLabel,0,0);
		gridPane.add(usernameTF, 0, 1);
		gridPane.add(emailLabel, 0, 2);
		gridPane.add(emailTF, 0, 3);
		gridPane.add(passwordLabel, 0, 4);
		gridPane.add(passwordField, 0, 5);
		gridPane.add(registerButton, 0, 6);
		gridPane.add(loginLabel, 0, 7);
		gridPane.add(loginButton, 1, 7);
		borderPane.setCenter(gridPane);
	}
	
	public void setAlignment() {
		loginButton.setStyle(
                "-fx-background-color: none; " +  
                "-fx-border-color: none;" + 
                "-fx-cursor: hand;" +
                "-fx-text-fill: #007bff;" +
                "-fx-underline: true;"
        );
		BorderPane.setMargin(gridPane, new Insets(10));
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	private void showAlertError(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}

}
