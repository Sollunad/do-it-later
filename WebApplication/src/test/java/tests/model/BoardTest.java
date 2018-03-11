package tests.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import resources.Board;
import resources.User;

class BoardTest {
	@Test
	void getSingleBoardFromJSON() {
		System.out.println("GetSingleBoardFromJSON:");
		Gson gson = new Gson();
		Board meinBoard = new Board(3, "Mein Board", "Boris");
		String jsonString = gson.toJson(meinBoard);
		System.out.println("\t"+jsonString);
		Board json = gson.fromJson(jsonString, Board.class);
		assertEquals(json.getId(), meinBoard.getId(), "ID stimmt nicht.");
		assertEquals(json.getName(), meinBoard.getName(), "Name stimmt nicht.");
		assertEquals(json.getAdmin(), meinBoard.getAdmin(), "Admin stimmt nicht.");
	}

	@Test
	void getMultipleUserFromJSON() {
		System.out.println("GetMultipleUserFromJSON:");
		Gson gson = new Gson();
		Board[] boards = { new Board(1, "Mein Board", "Boris"), new Board(2, "Mein zweites Board", "Boris"),
				new Board(3, "Mein drittes Board", "Boris") };
		String jsonString = gson.toJson(boards);
		System.out.println("\t"+jsonString);
		Board[] fancyBoards = gson.fromJson(jsonString, Board[].class);
		for (int i = 0; i < fancyBoards.length; i++) {
			assertEquals(boards[i].getId(), fancyBoards[i].getId(), "ID stimmt nicht.");
			assertEquals(boards[i].getName(), fancyBoards[i].getName(), "Name stimmt nicht.");
			assertEquals(boards[i].getAdmin(), fancyBoards[i].getAdmin(), "Admin stimmt nicht.");
		}
	}

	@Test
	void getAllBoards() {
		System.out.println("GetAllBoards:");
		List<Board> boards = Board.getAll();
		assertNotNull(boards, "Boards sind null!");
		//assertFalse(boards.size() > 0, "Boards sind leer.");
		for (Board board : boards) {
			System.out.println("\t" + board.toString());
		}
	}
}
