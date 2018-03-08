package resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.MySQLConnector;

public class Task extends DatabaseObject {
	private int id;
	private String title;
	private String description;
	private String status;
	private User bearbeiter;
	private int group;
	private String timestamp;
	
	public Task(String title, String description, String status, User bearbeiter, int group, String timestamp) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.setBearbeiter(bearbeiter);
		this.group = group;
		this.timestamp = timestamp;
	}
	
	public Task(int id, String title, String description, String status, User bearbeiter, int group, String timestamp) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.setBearbeiter(bearbeiter);
		this.group = group;
		this.timestamp = timestamp;
	}
	
	public static Task getById(int id) {
		String sql = "SELECT * FROM TASK WHERE id=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, id);
			ResultSet rs = s.executeQuery();
			List<Task> result = new ArrayList<>();
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String status = rs.getString("status");
				String bearbeiterName = rs.getString("bearbeiter");
				int board = rs.getInt("board");
				User bearbeiter = User.getByName(bearbeiterName);
				String timestamp = rs.getString("timestamp");
				Task task = new Task(id, title, content, status, bearbeiter, board, timestamp);
				result.add(task);
			}
			if (result.size() < 2)
				return result.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Task> getByGroup(int board) {
		String sql = "SELECT * FROM TASK WHERE board=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, board);
			ResultSet rs = s.executeQuery();
			List<Task> result = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String status = rs.getString("status");
				String bearbeiterName = rs.getString("bearbeiter");
				User bearbeiter = User.getByName(bearbeiterName);
				String timestamp = rs.getString("timestamp");
				Task task = new Task(id, title, content, status, bearbeiter, board, timestamp);
				result.add(task);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void query() {
		// TODO
	}

	@Override
	public void delete() {
		String sql = "DELETE FROM TASK WHERE id=? LIMIT 1";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, this.id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		String sql = "SELECT * FROM TASK WHERE id=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, this.id);
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
		String sql = "INSERT INTO TASK (title, content, status, bearbeiter, board, timestamp) VALUES (?, ?, ?, ?, ?, ?);";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.title);
			s.setString(2, this.description);
			s.setString(3, this.status);
			s.setString(4, this.bearbeiter.getName());
			s.setInt(5, this.group);
			s.setString(6, this.timestamp);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void register() {
		String sql = "INSERT INTO user (title, content, status, bearbeiter, board, timestamp) VALUES (?, ?, ?, ?, ?, ?)"
				+ " ON DUPLICATE KEY UPDATE title=?, content=?, status=?, bearbeiter=?, board=?, timestamp=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.title);
			s.setString(2, this.description);
			s.setString(3, this.status);
			s.setString(4, this.bearbeiter.getName());
			s.setInt(5, this.group);
			s.setString(6, this.timestamp);
			s.setString(7, this.title);
			s.setString(8, this.description);
			s.setString(9, this.status);
			s.setString(10, this.bearbeiter.getName());
			s.setInt(11, this.group);
			s.setString(12, this.timestamp);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public User getBearbeiter() {
		return bearbeiter;
	}

	public void setBearbeiter(User bearbeiter) {
		this.bearbeiter = bearbeiter;
	}

	@Override
	public String toString() {
		return "doWeEvenNeedThis";
	}

}
