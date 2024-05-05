package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {
	String kodeMenu;
	String namaMenu;
	int hargaMenu;
	int stockMenu;
	public Menu(String kodeMenu, String namaMenu, int hargaMenu, int stockMenu) {
		super();
		this.kodeMenu = kodeMenu;
		this.namaMenu = namaMenu;
		this.hargaMenu = hargaMenu;
		this.stockMenu = stockMenu;
	}
	public String getKodeMenu() {
		return kodeMenu;
	}
	public void setKodeMenu(String kodeMenu) {
		this.kodeMenu = kodeMenu;
	}
	public String getNamaMenu() {
		return namaMenu;
	}
	public void setNamaMenu(String namaMenu) {
		this.namaMenu = namaMenu;
	}
	public int getHargaMenu() {
		return hargaMenu;
	}
	public void setHargaMenu(int hargaMenu) {
		this.hargaMenu = hargaMenu;
	}
	public int getStockMenu() {
		return stockMenu;
	}
	public void setStockMenu(int stockMenu) {
		this.stockMenu = stockMenu;
	}
	public StringProperty kodeMenuProperty() {
		return new SimpleStringProperty(kodeMenu);
	}
	public StringProperty namaMenuProperty() {
		return new SimpleStringProperty(namaMenu);
	}
	public StringProperty hargaMenuProperty() {
		return new SimpleStringProperty(Integer.toString(hargaMenu));
	}
	public StringProperty stockMenuProperty() {
		return new SimpleStringProperty(Integer.toString(stockMenu));
	}
}
