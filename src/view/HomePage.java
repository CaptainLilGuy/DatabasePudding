package view;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Menu;

public class HomePage {
    protected Stage stage;
    protected Scene scene;
    protected BorderPane borderPane;
    protected GridPane gridPane;
    protected Label welcomeLabel;
    protected TableView<Menu> menuTable;
    protected Button insertButton;
    protected Button updateButton;
    protected Button deleteButton;
    protected ObservableList<Menu> listMenus;

	public HomePage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Homepage");
		initialize();
		setLayout();
		this.scene = new Scene(borderPane, 600, 600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	
	@SuppressWarnings("unchecked")
	private void initialize() {
		List<Menu> menus = MenuController.getAllMenus();
		listMenus = FXCollections.observableArrayList(menus);
		borderPane = new BorderPane();
	    gridPane = new GridPane();
	    welcomeLabel = new Label("Hello");
	    TableColumn<Menu, String> kodeMenuColumn = new TableColumn<>("kodeMenu");
	    kodeMenuColumn.setCellValueFactory(cell -> cell.getValue().kodeMenuProperty());
	    TableColumn<Menu, String> namaMenuColumn = new TableColumn<>("namaMenu");
	    namaMenuColumn.setCellValueFactory(cell -> cell.getValue().namaMenuProperty());
	    TableColumn<Menu, String> hargaMenuColumn = new TableColumn<>("hargaMenu");
	    hargaMenuColumn.setCellValueFactory(cell -> cell.getValue().hargaMenuProperty());
	    TableColumn<Menu, String> stockMenuColumn = new TableColumn<>("stockMenu");
	    stockMenuColumn.setCellValueFactory(cell -> cell.getValue().stockMenuProperty());
	    
	    menuTable = new TableView<>();
	    menuTable.setItems(listMenus);
	    menuTable.getColumns().addAll(kodeMenuColumn, namaMenuColumn, hargaMenuColumn, stockMenuColumn);
	    insertButton = new Button("Insert New Menu");
	    updateButton = new Button("Update Menu");
	    deleteButton = new Button("Delete Menu");
	    
	    insertButton.setOnAction(event -> {
	    	InsertPage insertPage = new InsertPage(stage);
            stage.setScene(insertPage.getScene());
            stage.show();
	    });
	    
	    updateButton.setOnAction(event -> {
	    	UpdatePage updatePage = new UpdatePage(stage);
	    	stage.setScene(updatePage.getScene());
	    	stage.show();
	    });
	    
	    deleteButton.setOnAction(event -> {
	    	DeletePage deletePage = new DeletePage(stage);
	    	stage.setScene(deletePage.getScene());
	    	stage.show();
	    });
	}
	
	
	
	private void setLayout() {
		//gridPane.add(welcomeLabel, 0, 0);
		gridPane.add(menuTable, 0, 1);
		gridPane.add(insertButton, 0, 2);
		gridPane.add(updateButton, 1, 2);
		gridPane.add(deleteButton, 2, 2);
		borderPane.setCenter(gridPane);
	}

}
