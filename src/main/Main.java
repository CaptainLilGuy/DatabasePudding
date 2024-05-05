package main;

import java.util.ArrayList;

import database.DatabaseConnection;
import database.DatabaseSingleton;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Account;
import model.User;
import view.RegisterPage;


public class Main extends Application{

	DatabaseConnection db = DatabaseSingleton.getInstance();
	Account acc = new Account(new ArrayList<User>());
	
	public static void main(String[] args) {
		new Main();
		launch(args);
	}
	
	public Main() {
		db.migrateTables();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		RegisterPage registerPage = new RegisterPage(arg0);
		arg0.setScene(registerPage.getScene());
		arg0.show();
	}

}
