package resources;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import database.MySQLConnector;
import database.jdbcConnector;

public class User extends DatabaseObject {
	private String name;
	private String password;
	private List<Board> boards;

	public User(String name) {
		this.name = name;
		this.password = "";
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public static List<User> getAll() {
		String sql = "SELECT name AS A, password AS B FROM user;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			ResultSet rs = s.executeQuery();
			List<User> result = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("A");
				String password = rs.getString("B");
				User user = new User(name);
				user.setPassword(password);
				result.add(user);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static User getByName(String username) {
		String sql = "SELECT * FROM user WHERE name=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, username);
			ResultSet rs = s.executeQuery();
			List<User> result = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				User user = new User(name);
				user.setPassword(password);
				result.add(user);
			}
			if (result.size() < 2)
				return result.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<User> getAllUsers(Board board) {
		String sql = "SELECT user.name AS A, user.password AS B FROM user_in_boards "
				+ "JOIN user ON user_in_boards.user_name=user.name WHERE user_in_boards.board_id=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, board.getId());
			ResultSet rs = s.executeQuery();
			List<User> result = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("A");
				String password = rs.getString("B");
				User user = new User(name);
				user.setPassword(password);
				result.add(user);
			}
			return result;
		} catch (SQLException e) {
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
		String sql = "DELETE FROM user WHERE name=? LIMIT 1";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.name);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		String sql = "SELECT name, password FROM user WHERE name=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.name);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean login() {
		String sql = "SELECT name, password FROM user WHERE name=? AND password=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.name);
			s.setString(2, this.password);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void persist() {
		String sql = "INSERT INTO user (name, password) VALUES (?, ?);";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.name);
			s.setString(2, this.password);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void register() {
		String sql = "INSERT INTO user (name, password) VALUES (?, ?) ON DUPLICATE KEY UPDATE Username=?, Password=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.name);
			s.setString(2, this.password);
			s.setString(3, this.name);
			s.setString(4, this.password);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format("User: [name=%s, password=%s]", this.name, this.password);
	}

}
