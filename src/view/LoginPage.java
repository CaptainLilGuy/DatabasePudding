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

public class LoginPage {
    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;
    private GridPane gridPane;
    private Label emailLabel, passwordLabel;
    private TextField emailTF;
    private PasswordField passwordField;
    private Button loginButton;
    private Label registerLabel;
    private Button registerButton;
	
    public LoginPage(Stage stage) {
    	this.stage = stage;
    	this.stage.setTitle("Login");
    	initialize();
        setLayout();
        setAlignment();
        this.scene = new Scene(borderPane, 400, 400);
    }

	public void initialize() {
		borderPane = new BorderPane();
        gridPane = new GridPane();
        emailLabel = new Label("Email");
        passwordLabel = new Label("Password");
        emailTF = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");

        EventHandler<MouseEvent> loginEvent = event -> {
        	String email = emailTF.getText();
        	String password = passwordField.getText();
        	
        	if(!email.isEmpty() && !password.isEmpty()) {
        		boolean isLogin = UserController.Login(email, password);
        		if(isLogin) {
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
        			alert.setTitle("Successful message");
        			alert.setHeaderText(null);
        			alert.setContentText("Succesfully logged in");
        			alert.showAndWait();
        			HomePage homePage = new HomePage(stage);
        			stage.setScene(homePage.getScene());
        			stage.show();
        		} else {
        			showAlertError("Login Failed!", "There are no matching credentials");
        		}
        	} else {
        		showAlertError("Login Failed!", "Email and password are required");
        	}
        };
        loginButton.addEventHandler(MouseEvent.MOUSE_CLICKED, loginEvent);
        
        
        registerLabel = new Label("Don't have an account yet?");
        registerButton = new Button("Register here");
        
        EventHandler<MouseEvent> redirectToRegister = event -> {
            RegisterPage registerPage = new RegisterPage(stage);
            stage.setScene(registerPage.getScene());
        };
        registerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectToRegister);
		
	}

	public void setLayout() {
		gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailTF, 0, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 0, 3);
        gridPane.add(loginButton, 0, 4);
        gridPane.add(registerLabel, 0, 5);
        gridPane.add(registerButton, 1, 5);
        borderPane.setCenter(gridPane);
		
	}

	public void setAlignment() {
		registerButton.setStyle(
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
