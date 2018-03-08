package resources;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;

import database.MySQLConnector;
import database.jdbcConnector;

public class Card extends DatabaseObject {

	private int id;
	private String title;
	private String content;
	private String status;
	private String timestamp;
	private String bearbeiter;
	private int board;

	public Card(String title, String content, String status) {
		super();
		this.title = title;
		this.content = content;
		this.status = status;
	}

	public Card(int id, String title, String content, String status, String timestamp, int board, String bearbeiter) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.timestamp = timestamp;
		this.bearbeiter = bearbeiter;
		this.board = board;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getStatus() {
		return status;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public void updateTimestamp() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		System.out.println(timestamp);
		this.timestamp = timeStamp;
	}

	public String getBearbeiter() {
		return bearbeiter;
	}

	public int getBoard() {
		return board;
	}

	public static List<Card> getAllCardsFromBoard(Board board) {
		String sql = "SELECT * FROM card WHERE board=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, board.getId());
			ResultSet rs = s.executeQuery();
			List<Card> cards = new ArrayList<>();
			while (rs.next()) {
				Card card = new Card(
				rs.getInt("id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getString("status"),
				rs.getString("timestamp"),
				rs.getInt("board"),
				rs.getString("bearbeiter"));
				cards.add(card);
			}
			return cards;
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
		String sql = "INSERT INTO card (title, content, status, board, bearbeiter) VALUES (?, ?, ?, ?, ?);";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.title);
			s.setString(2, this.content);
			s.setString(3, this.status);
			s.setInt(4, this.board);
			s.setString(5, this.bearbeiter);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void persistUpdate() {
		String sql = "UPDATE card SET title=?, content=?, status=?, bearbeiter=? WHERE id=?;";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setString(1, this.title);
			s.setString(2, this.content);
			s.setString(3, this.status);
			s.setString(4, this.bearbeiter);
			s.setInt(5, this.id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		String sql = "DELETE FROM card WHERE id=? LIMIT 1";
		try (PreparedStatement s = MySQLConnector.getConnection().prepareStatement(sql)) {
			s.setInt(1, this.id);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		String sql = "SELECT * FROM card WHERE id=?;";
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
	public String toString() {
		return String.format("Card: [id=%d, title=%s, content=%s, status=%s, assignment=%s]", this.id, this.title,
				this.content, this.status, this.bearbeiter);
	}

}
