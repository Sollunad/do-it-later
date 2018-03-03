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

	public User(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
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

	public static List<User> getAll() {
		String queryString = "SELECT UserID AS id, Username AS name, Password AS password FROM USER;";
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

	public static User getByName(String name) {
		String queryString = String.format("SELECT id AS id, name AS name FROM User WHERE name=%s;", name);
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

	public static User getAssigned(Card card) {
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

	public static List<User> getAllUsers(Board board) {
		String queryString = String.format(
				"SELECT USER.UserID AS id, USER.Username AS name FROM USER_IN_GROUP "
						+ "JOIN USER ON USER_IN_GROUP.fk_UserID=USER.UserID " + "WHERE USER_IN_GROUP.fk_GroupID=%d;",
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
		// TODO
	}

	@Override
	public void delete() {
		String deleteString = String.format("DELETE FROM USER WHERE Username=%s AND Password=%s;", this.name,
				this.password);
		try {
			jdbcConnector.query(deleteString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		String queryString = String.format("SELECT UserID AS id, Username AS name FROM USER WHERE Username=%s;", name);
		try {
			String resultString = jdbcConnector.query(queryString);
			return (resultString != null);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void persist() {
		String updateString = String.format("INSERT INTO table (Username, Password) VALUES('%s', '%s');", this.name,
				this.password);
		try {
			jdbcConnector.query(updateString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void register() {
		String updateString = String.format(
				"INSERT INTO USER (Username, Password) VALUES('%s', '%s') ON DUPLICATE KEY UPDATE Username=%s, Password=%s;",
				this.name, this.password);
		try {
			jdbcConnector.query(updateString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format("User: [id=%d, name=%s, password=%s]", this.id, this.name, this.password);
	}

}
