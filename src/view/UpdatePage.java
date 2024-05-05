package view;

import java.util.List;
import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdatePage {
		private Stage stage;
	    private Scene scene;
	    private BorderPane borderPane;
	    private GridPane gridPane;
	    private Label labelId, labelNama, labelHarga, labelStock;
	    private TextField nama, harga, stock;
	    private Button updateBTN, backButton;
	    private ComboBox<String> idComboBox;
	    
	    public UpdatePage(Stage stage) {
	        this.stage = stage;
	        this.stage.setTitle("Update");
	        init();
	        setLayout();
	        scene = new Scene(borderPane, 400, 400);
	    }
	    
	    private void init() {
	        borderPane = new BorderPane();
	        gridPane = new GridPane();
	        labelId = new Label("ID");
	        labelNama = new Label("Nama");
	        labelHarga = new Label("Harga");
	        labelStock = new Label("Stock");
	        idComboBox = new ComboBox<>();
	        nama = new TextField();
	        harga = new TextField();
	        stock = new TextField();
	        updateBTN = new Button("Update Menu");
	        backButton = new Button("Return");
	        List<String> menus = MenuController.getAllMenuNames();
	        idComboBox.setItems(FXCollections.observableArrayList(menus));
	        idComboBox.setOnAction(event -> {
	        	nama.setText(MenuController.getNamaMenu(idComboBox.getValue()));
	        	harga.setText(MenuController.getHargaMenu(idComboBox.getValue()));
	        	stock.setText(MenuController.getStockMenu(idComboBox.getValue()));
	        });
	        updateBTN.setOnAction(event -> {
	            String selectedId = idComboBox.getValue();
	            String namaMenu = "";
	            try {
					namaMenu = nama.getText();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	            Integer hargaMenu = 0;
				try {
					hargaMenu = Integer.parseInt(harga.getText());
				} catch (NumberFormatException e) {
					showAlertError("Format Error", "Please fill the harga box");
				}
	            Integer stockMenu = 0;
				try {
					stockMenu = Integer.parseInt(stock.getText());
				} catch (NumberFormatException e) {
					showAlertError("Format Error", "Please fill the stock box");
				}
	            boolean updated = MenuController.updateMenu(selectedId, namaMenu, hargaMenu, stockMenu);
	            
	            if(updated) {
	                HomePage homePage = new HomePage(stage);
	                stage.setScene(homePage.getScene());
	                stage.show();
	            }
	        });
	        backButton.setOnAction(event -> {
	        	HomePage homePage = new HomePage(stage);
                stage.setScene(homePage.getScene());
                stage.show();
	        });
	    }
	    
	    private void setLayout() {
	        gridPane.add(labelId, 0, 0);
	        gridPane.add(idComboBox, 1, 0);
	        gridPane.add(labelNama, 0, 1);
	        gridPane.add(nama, 1, 1);
	        gridPane.add(labelHarga, 0, 2);
	        gridPane.add(harga, 1, 2);
	        gridPane.add(labelStock, 0, 3);
	        gridPane.add(stock, 1, 3);
	        gridPane.add(updateBTN, 0, 4);
	        gridPane.add(backButton, 1, 4);
	        borderPane.setCenter(gridPane);
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
