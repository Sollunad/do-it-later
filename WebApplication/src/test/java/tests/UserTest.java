package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import resources.User;

class UserTest {
	@Test
	void getSingleUserFromJSON() {
		Gson gson = new Gson();
		User boris = new User(12, "Boris", "238591723");
		String jsonString = gson.toJson(boris);
		System.out.println(jsonString);
		User json = gson.fromJson(jsonString, User.class);
		assertEquals(json.getId(), boris.getId(), "ID stimmt nicht.");
		assertEquals(json.getName(), boris.getName(), "Username stimmt nicht.");
		assertEquals(json.getPassword(), boris.getPassword(), "Passwort stimmt nicht.");
	}

	@Test
	void getMultipleUserFromJSON() {
		Gson gson = new Gson();
		User[] users = { new User(12, "Boris", "238591723"), new User(43, "Vladimir", "238591724"),
				new User(45, "Nikita", "238591725") };
		String jsonString = gson.toJson(users);
		System.out.println(jsonString);
		User[] fancyUsers = gson.fromJson(jsonString, User[].class);
		for (int i = 0; i<fancyUsers.length; i++) {
			assertEquals(users[i].getId(), fancyUsers[i].getId(), "ID stimmt nicht.");
			assertEquals(users[i].getName(), fancyUsers[i].getName(), "Username stimmt nicht.");
			assertEquals(users[i].getPassword(), fancyUsers[i].getPassword(), "Passwort stimmt nicht.");
		}
	}
}
