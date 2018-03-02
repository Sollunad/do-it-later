package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import database.jdbcConnector;

public class Board extends DatabaseObject {
	private int id;
	private String name;
	private int admin;
	private List<User> users;
	private List<Card> cards;

	Board(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAdmin() {
		return admin;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Card> getCards() {
		return cards;
	}

	// TODO
	public void setUsers() {
		this.users = User.getAllUsersFromBoard(this);
	}

	// TODO
	public void setCards() {
		this.cards = Card.getAllCardsFromBoard(this);
	}

	public static List<Board> getAllBoardsFromUser(User user) {
		String queryString = String.format("SELECT Board.id AS ID, Board.name AS name, Board.admin AS admin "
				+ "FROM rel_User_Board JOIN Board ON rel_User_Board.board_id=Board.id "
				+ "WHERE rel_User_Board.user_id=%s", user.getId());
		try {
			String resultString = jdbcConnector.query(queryString);
			Gson gson = new Gson();
			Board[] boardArray = gson.fromJson(resultString, Board[].class);
			return new ArrayList<>(Arrays.asList(boardArray));
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
