package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import resources.User;
import servlets.Login;

class UserTest {
	
	
	@Test
	void sampleUserPersistQueryAndDelete() {
		User jerry = new User("Jerry");
		String hash = DigestUtils.sha256Hex("knampf");
		jerry.setPassword(hash);
		jerry.persist();
		boolean exists = jerry.exists();
		System.out.println("Jerry exists: " + exists);
		assertTrue(exists, "Jerry konnte nicht gefunden werden.");
		if (exists)
			jerry.delete();
	}
	
	@Test
	void testUserExists() {
		User pipples = new User("Shrimply Pibbles");
		String hash = DigestUtils.sha256Hex("I<3Heroin!");
		pipples.setPassword(hash);
		boolean exists = pipples.exists();
		System.out.println("Pipples exists: " + exists);
		assertFalse(exists, "Shrimply Pibbles darf nicht existieren! Niemals!");
	}
	
	@Test
	void generateTestUser() {
		User vladimir = new User("Vladimir");
		String hash = DigestUtils.sha256Hex("qweruiop");
		vladimir.setPassword(hash);
		boolean exists = vladimir.exists();
		System.out.println("Vladimir exists: " + exists);
		assertFalse(exists, "Vladimir existiert bereits!");
		if (exists)
			vladimir.persist();
	}	
	
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
		for (int i = 0; i < fancyUsers.length; i++) {
			assertEquals(users[i].getId(), fancyUsers[i].getId(), "ID stimmt nicht.");
			assertEquals(users[i].getName(), fancyUsers[i].getName(), "Username stimmt nicht.");
			assertEquals(users[i].getPassword(), fancyUsers[i].getPassword(), "Passwort stimmt nicht.");
		}
	}

	@Test
	void getAllUsersFromDB() {
		List<User> users = User.getAll();
		assertNotNull(users, "Users sind null!");
		System.out.println("All Users From DB:");
		for (User user : users) {
			System.out.println("\t" + user.toString());
		}
	}
}
