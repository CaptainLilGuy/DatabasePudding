package view;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeletePage {
	private Stage stage;
    private Scene scene;
    private BorderPane borderPane;
    private GridPane gridPane;
    private Label labelKode, labelNama, labelHarga, labelStock, labelValueNama, labelValueHarga, labelValueStock;
    private Button deleteBTN, backButton;
    private ComboBox<String> kodeComboBox;
    
    public DeletePage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Delete");
        init();
        setLayout();
        scene = new Scene(borderPane, 400, 400);
    }
    
    private void init() {
        borderPane = new BorderPane();
        gridPane = new GridPane();
        labelKode = new Label("Kode Menu");
        labelNama = new Label("Nama Menu");
        labelHarga = new Label("Harga Menu");
        labelStock = new Label("Stock Menu");
        labelValueNama = new Label("");
        labelValueHarga = new Label("");
        labelValueStock = new Label("");
        kodeComboBox = new ComboBox<>();
        deleteBTN = new Button("Delete Menu");
        backButton = new Button("Return");
        List<String> menus = MenuController.getAllMenuNames();
        kodeComboBox.setItems(FXCollections.observableArrayList(menus));
        kodeComboBox.setOnAction(event -> {
        	labelValueNama.setText(MenuController.getNamaMenu(kodeComboBox.getValue()));
        	labelValueHarga.setText(MenuController.getHargaMenu(kodeComboBox.getValue()));
        	labelValueStock.setText(MenuController.getStockMenu(kodeComboBox.getValue()));
        });
        deleteBTN.setOnAction(event -> {
            String selectedTitle = kodeComboBox.getValue();
            boolean updated = MenuController.deleteMenu(selectedTitle);
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
        gridPane.add(labelKode, 0, 0);
        gridPane.add(kodeComboBox, 1, 0);
        gridPane.add(labelNama, 0, 1);
        gridPane.add(labelValueNama, 1, 1);
        gridPane.add(labelHarga, 0, 2);
        gridPane.add(labelValueHarga, 1, 2);
        gridPane.add(labelStock, 0, 3);
        gridPane.add(labelValueStock, 1, 3);
        gridPane.add(deleteBTN, 0, 4);
        gridPane.add(backButton, 1, 4);
        borderPane.setCenter(gridPane);
    }
    
    public Scene getScene() {
    	return this.scene;
    }
}
