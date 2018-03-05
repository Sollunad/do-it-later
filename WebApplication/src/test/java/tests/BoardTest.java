package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import resources.Board;
import resources.User;

class BoardTest {
	@Test
	void getSingleBoardFromJSON() {
		Gson gson = new Gson();
		Board meinBoard = new Board(3, "Mein Board", 1337);
		String jsonString = gson.toJson(meinBoard);
		System.out.println(jsonString);
		Board json = gson.fromJson(jsonString, Board.class);
		assertEquals(json.getId(), meinBoard.getId(), "ID stimmt nicht.");
		assertEquals(json.getName(), meinBoard.getName(), "Name stimmt nicht.");
		assertEquals(json.getAdmin(), meinBoard.getAdmin(), "Admin stimmt nicht.");
	}

	@Test
	void getMultipleUserFromJSON() {
		Gson gson = new Gson();
		Board[] boards = { new Board(1, "Mein Board", 1337), new Board(2, "Mein zweites Board", 1337),
				new Board(3, "Mein drittes Board", 1337) };
		String jsonString = gson.toJson(boards);
		System.out.println(jsonString);
		Board[] fancyBoards = gson.fromJson(jsonString, Board[].class);
		for (int i = 0; i < fancyBoards.length; i++) {
			assertEquals(boards[i].getId(), fancyBoards[i].getId(), "ID stimmt nicht.");
			assertEquals(boards[i].getName(), fancyBoards[i].getName(), "Name stimmt nicht.");
			assertEquals(boards[i].getAdmin(), fancyBoards[i].getAdmin(), "Admin stimmt nicht.");
		}
	}

	@Test
	void getAllBoards() {
		List<Board> boards = Board.getAll();
		assertNotNull(boards, "Boards sind null!");
		assertFalse(boards.size() > 0, "Boards sind leer.");
		System.out.println("All Boards From DB:");
		for (Board board : boards) {
			System.out.println("\t" + board.toString());
		}
	}
}
