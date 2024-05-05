package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import database.DatabaseSingleton;
//import javafx.collections.ObservableList;
import model.Menu;
//import model.User;

public class MenuController {
	
	protected static DatabaseConnection db = DatabaseSingleton.getInstance();
	public static Boolean insertMenu(Menu menu) {
		String query = "INSERT INTO Menu(KodeMenu, NamaMenu, HargaMenu, StockMenu) "
				+ "VALUES (?,?,?,?)";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, menu.getKodeMenu());
			stmt.setString(2, menu.getNamaMenu());
			stmt.setString(3, Integer.toString(menu.getHargaMenu()));
			stmt.setString(4, Integer.toString(menu.getStockMenu()));
			int rowAffected = stmt.executeUpdate();
			System.out.println("Row affected from query: " + rowAffected);
			return rowAffected > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<Menu> getAllMenus(){
		String query = "SELECT * FROM Menu";
		List<Menu> menus = new ArrayList<Menu>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String kode = rs.getString("KodeMenu");
				String nama = rs.getString("NamaMenu");
				String harga = rs.getString("HargaMenu");
				String stock = rs.getString("StockMenu");
				Menu menu = new Menu(kode, nama, Integer.parseInt(harga), Integer.parseInt(stock));
				menus.add(menu);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return menus;
	}
	
	public static List<String> getAllMenuNames(){
		String query = "SELECT KodeMenu FROM menu";
		List<String> kodeMenuList = new ArrayList<String>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String kodeMenu = rs.getString("KodeMenu");
				kodeMenuList.add(kodeMenu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kodeMenuList;
	}
	
	public static String getNamaMenu(String kodeMenu) {
		String query = "SELECT NamaMenu FROM menu WHERE KodeMenu = ?";
		String namaMenu = "";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, kodeMenu);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				namaMenu = rs.getString("NamaMenu");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return namaMenu;
	}
	
	public static String getHargaMenu(String kodeMenu) {
		String query = "SELECT HargaMenu FROM menu WHERE KodeMenu = ?";
		String hargaMenu = "";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, kodeMenu);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				hargaMenu = rs.getString("HargaMenu");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hargaMenu;
	}
	
	public static String getStockMenu(String kodeMenu) {
		String query = "SELECT StockMenu FROM menu WHERE KodeMenu = ?";
		String stockMenu = "";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, kodeMenu);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				stockMenu = rs.getString("stockMenu");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockMenu;
	}
	
	public static Boolean updateMenuPrice (String namaMenu, int hargaMenu) {
		if(menuExist(namaMenu)) {
			String query = "UPDATE menu SET HargaMenu = ? WHERE NamaMenu = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setInt(1, hargaMenu);
				stmt.setString(2, namaMenu);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static Boolean updateMenu(String kodeMenu, String namaMenu, int hargaMenu, int stockMenu) {
		if(menuExist(kodeMenu)) {
			String query = "UPDATE menu SET NamaMenu = ?, HargaMenu = ?, StockMenu = ? WHERE KodeMenu = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setString(1, namaMenu);
				stmt.setInt(2, hargaMenu);
				stmt.setInt(3, stockMenu);
				stmt.setString(4, kodeMenu);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static boolean checkKodeMenu(String kodeMenu){
		String query = "SELECT KodeMenu FROM Menu";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String kode = rs.getString("KodeMenu");
				System.out.println(kode);
				if(kode.equals(kodeMenu)) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static Boolean deleteMenu (String kodeMenu) {
		if(menuExist(kodeMenu)) {
			String query = "DELETE FROM menu WHERE KodeMenu = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setString(1, kodeMenu);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static Boolean menuExist(String kodeMenu) {
		String query = "SELECT COUNT(*) FROM menu WHERE KodeMenu = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, kodeMenu);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
