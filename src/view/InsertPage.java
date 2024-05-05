package view;

import java.util.Random;
//import java.util.List;
//import java.util.ArrayList;

import controller.MenuController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Menu;

public class InsertPage {

	Random rand = new Random();
	
	Stage stage;
	Scene scene;
	BorderPane borderPane;
	GridPane gridPane;
	Label namaMenuLabel, hargaMenuLabel, stockMenuLabel;
	TextField namaMenuTF, hargaMenuTF, stockMenuTF;
	String kodeMenu;
	Button insertButton, backButton;
	
	public InsertPage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Insert");
		init();
		setLayout();
		setEventHandler();
		scene = new Scene(borderPane, 400, 400);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		namaMenuLabel = new Label("Nama Menu");
		hargaMenuLabel = new Label("Harga Menu");
		stockMenuLabel = new Label("Stock Menu");
		namaMenuTF = new TextField();
		hargaMenuTF = new TextField();
		hargaMenuTF.setText("0");
		stockMenuTF = new TextField();
		stockMenuTF.setText("0");
		insertButton = new Button("Insert Menu");
		backButton = new Button("Return");
		backButton.setOnAction(event -> {
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
	}
	
	private void setLayout() {
		gridPane.add(namaMenuLabel, 0, 0);
		gridPane.add(namaMenuTF, 1, 0);
		gridPane.add(hargaMenuLabel, 0, 1);
		gridPane.add(hargaMenuTF, 1, 1);
		gridPane.add(stockMenuLabel, 0, 2);
		gridPane.add(stockMenuTF, 1, 2);
		gridPane.add(insertButton, 0, 3);
		gridPane.add(backButton, 1, 3);
		borderPane.setCenter(gridPane);
	}
	
	private void setEventHandler() {
		insertButton.setOnAction(event -> {
			
			String namaMenu = namaMenuTF.getText();
			Integer hargaMenu = 0;
			Integer stockMenu = 0;
			
			try {
				hargaMenu = Integer.parseInt(hargaMenuTF.getText());
			} catch (Exception e) {
				showAlertError("Invalid type", "Harga Menu must be an integer");
				hargaMenuTF.setText("0");
				return;
				
			}
			try {
				stockMenu = Integer.parseInt(stockMenuTF.getText());
			} catch (Exception e) { 
				showAlertError("Invalid type", "Stock Menu must be an integer");
				stockMenuTF.setText("0");
				return;
			}
			
			if(namaMenu=="" || hargaMenu==0 || stockMenu==0) {
				showAlertError("Empty Fields", "Please fill all fields");
				return;
			}
			
			kodeMenu = genKode();
			
			Menu menu = new Menu(kodeMenu, namaMenu, hargaMenu, stockMenu);
			Boolean inserted = MenuController.insertMenu(menu);
			if(inserted) {
				HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show();
			}
		});
	}
	
	private String genKode() {
		boolean isUnique = false;
		String kodeMenu = "PD-";
		while(!isUnique) {
			String digits = Integer.toString(rand.nextInt(10))+Integer.toString(rand.nextInt(10))+Integer.toString(rand.nextInt(10));
			kodeMenu+=digits;
			if(MenuController.checkKodeMenu(kodeMenu)==true) {
				isUnique=true;
			}
		}
		System.out.println(kodeMenu);
		return kodeMenu;
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
