package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import database.jdbcConnector;

public class User extends DatabaseObject {
	private int id;
	private String name;
	private String password;
	private List<Board> boards;

	public User(String name) {
		this.name = name;
		this.password = "";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (this.password != "") {
			this.password = password;
		}
	}

	public List<Board> getBoards() {
		return boards;
	}

	public static User getUser(String name) {
		String queryString = String
				.format("SELECT id AS id, name AS name FROM User WHERE name=%s;", name);
		try {
			String resultString = jdbcConnector.query(queryString);
			if (resultString == null)
				return null;
			Gson gson = new Gson();
			return gson.fromJson(resultString, User.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User getAdmin(Board board) {
		String queryString = String.format("SELECT User.id AS id, User.name AS name FROM User "
				+ "JOIN Board ON User.ID=Board.admin " + "WHERE Board.ID=%s;", board.getId());
		try {
			String resultString = jdbcConnector.query(queryString);
			Gson gson = new Gson();
			return gson.fromJson(resultString, User.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User getAssignedUser(Card card) {
		String queryString = String.format("SELECT User.id AS id, User.name AS name FROM User "
				+ "JOIN Card ON User.ID=Card.assignment " + "WHERE Card.ID=%s;", card.getId());
		try {
			String resultString = jdbcConnector.query(queryString);
			Gson gson = new Gson();
			return gson.fromJson(resultString, User.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<User> getAllUsersFromBoard(Board board) {
		String queryString = String.format(
				"SELECT User.id AS id, User.name AS name FROM rel_User_Board "
						+ "JOIN User ON rel_User_Board.user_id=User.ID " + "WHERE rel_User_Board.board_id=%s;",
				board.getId());
		try {
			String resultString = jdbcConnector.query(queryString);
			Gson gson = new Gson();
			User[] userArray = gson.fromJson(resultString, User[].class);
			return new ArrayList<>(Arrays.asList(userArray));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void query() {
		//TODO
	}

	@Override
	public void delete() {
		String deleteString = String.format("DELETE FROM User WHERE name=%s AND password=%s;", this.name,
				this.password);
		try {
			jdbcConnector.query(deleteString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		String queryString = String
				.format("SELECT id AS id, name AS name FROM User WHERE name=%s;", name);
		try {
			String resultString = jdbcConnector.query(queryString);
			return  (resultString != null);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void persist() {
		String updateString = String.format("INSERT INTO table (name, password) VALUES(%s, %s);", this.name,
				this.password);
		try {
			jdbcConnector.query(updateString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void register() {
		String updateString = String.format(
				"INSERT INTO table (name, password) VALUES(%s, %s) ON DUPLICATE KEY UPDATE name=%s, password=%s;",
				this.name, this.password, this);
		try {
			jdbcConnector.query(updateString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
