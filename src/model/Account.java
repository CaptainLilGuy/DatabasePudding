package model;

import java.util.ArrayList;

public class Account {
	ArrayList<User> users = new ArrayList<User>();
	User currUser;
	
	public Account(ArrayList<User> user) {
		this.users = user;
	}
	
	public User getCurrUser() {
		return currUser;
	}
	
	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}
	
	public ArrayList<User> getUser(){
		return users;
	}
	
	public void setUser(ArrayList<User> user) {
		this.users = user;
	}
	
	public void registerAccount(User user) {
		users.add(user);
	}
	
	public Boolean loginAccount(String email, String password) {
		System.out.println("Testing");
		for(User u: users) {
			System.out.println("Masuk ke loop");
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				System.out.println("Here");
				currUser = u;
				return true;
			}
		}
		return false;
	}

}
