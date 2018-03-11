package resources;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import database.MySQLConnector;
import database.jdbcConnector;

public class Board extends DatabaseObject {
	private int id;
	private String name;
	private String admin;

	public Board() {}
	
	public Board(int id) {
		this.id = id;
	}
	
	public Board(String name) {
		this.name = name;
	}
	
	public Board(String name, String admin) {
		this.admin = admin;
		this.name  = name;
	}

	public Board(int id, String name, String admin) {
		this.id = id;
		this.name = name;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAdmin() {
		return admin;
	}
	
	public void add(User user) {
		String sql = "INSERT INTO user_in_boards (user_name, board_id) VALUES (?, ?);";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, user.getName());
			s.setInt(2, this.id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(User user) {
		String sql = "DELETE FROM user_in_boards WHERE user_name=? AND board_id=? LIMIT 1;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, user.getName());
			s.setInt(2, this.id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Board> getAll() {
		String sql = "SELECT * FROM board;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			ResultSet rs = s.executeQuery();
			List<Board> result = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String admin = rs.getString("admin");
				Board board = new Board(id, name, admin);
				result.add(board);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Board> getAllBoardsFromUser(User user) {
		String sql = "SELECT board.id AS A, board.name AS B, board.admin AS C "
				+ "FROM user_in_boards JOIN board ON user_in_boards.board_id=board.id "
				+ "WHERE user_in_boards.user_name=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, user.getName());
			ResultSet rs = s.executeQuery();
			List<Board> result = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("A");
				String name = rs.getString("B");
				String admin = rs.getString("C");
				Board board = new Board(id, name, admin);
				result.add(board);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void query() {
		// TODO Auto-generated method stub

	}

	@Override
	public void persist() {
		String sql = "INSERT INTO board (name, admin) VALUES (?, ?);";
		try(PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)){
			s.setString(1, this.name);
			s.setString(2, this.admin);
			s.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return String.format("Board: [id=%d, name=%s, admin=%s]", this.id, this.name, this.admin);
	}

}
