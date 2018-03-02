package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import database.jdbcConnector;

public class Card extends DatabaseObject {

	private int id;
	private String title;
	private String content;
	private String status;
	private String timestamp;
	private int assignment;
	private Board board;

	public Card(String title, String content, String status) {
		super();
		this.title = title;
		this.content = content;
		this.status = status;
	}

	public Card(int id, String title, String content, String status, String timestamp, int assignment, Board board) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.timestamp = timestamp;
		this.assignment = assignment;
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

	public int getAssignment() {
		return assignment;
	}

	public Board getBoard() {
		return board;
	}

	public static List<Card> getAllCardsFromBoard(Board board) {
		String queryString = String.format("SELECT * FROM Card WHERE Card.Board=%s;", board.getId());
		try {
			String resultString = jdbcConnector.query(queryString);
			Gson gson = new Gson();
			Card[] cardArray = gson.fromJson(resultString, Card[].class);
			return new ArrayList<>(Arrays.asList(cardArray));
		} catch (IOException e) {
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
		// TODO Auto-generated method stub
		
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

}
