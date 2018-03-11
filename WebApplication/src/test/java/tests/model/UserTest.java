package tests.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import resources.User;
import servlets.Login;

class UserTest {

	@Test
	void sampleUserPersistQueryAndDelete() {
		System.out.println("SampleUserPersistQueryAndDelete:");
		User jerry = new User("Jerry");
		String hash = DigestUtils.sha256Hex("knampf");
		jerry.setPassword(hash);
		jerry.persist();
		boolean exists = jerry.exists();
		System.out.println("\tJerry exists: " + exists);
		assertTrue(exists, "Jerry konnte nicht gefunden werden.");
		if (exists)
			jerry.delete();
	}

	@Test
	void testUserExists() {
		System.out.println("TestUserExists:");
		User pipples = new User("Shrimply Pibbles");
		String hash = DigestUtils.sha256Hex("I<3Heroin!");
		pipples.setPassword(hash);
		boolean exists = pipples.exists();
		System.out.println("\tPipples exists: " + exists);
		assertFalse(exists, "Shrimply Pibbles darf nicht existieren! Niemals!");
	}

	@Test
	void generateTestUser() {
		System.out.println("GenerateTestUser:");
		User testUser = new User("Test");
		String hash = DigestUtils.sha256Hex("test@12345");
		testUser.setPassword(hash);
		System.out.println("\t" + testUser.toString());
		boolean exists = testUser.exists();
		System.out.println("\tTestUser exists: " + exists);
		if (!exists)
			testUser.persist();
	}

	@Test
	void getSingleUserFromJSON() {
		System.out.println("GetSingleUserFromJSON:");
		Gson gson = new Gson();
		User boris = new User("Boris", "238591723");
		String jsonString = gson.toJson(boris);
		System.out.println("\t" + jsonString);
		User json = gson.fromJson(jsonString, User.class);
		assertEquals(json.getName(), boris.getName(), "Username stimmt nicht.");
		assertEquals(json.getPassword(), boris.getPassword(), "Passwort stimmt nicht.");
	}

	@Test
	void getMultipleUserFromJSON() {
		System.out.println("GetMultipleUserFromJSON:");
		Gson gson = new Gson();
		User[] users = { new User("Boris", "238591723"), new User("Vladimir", "238591724"),
				new User("Nikita", "238591725") };
		String jsonString = gson.toJson(users);
		System.out.println("\t" + jsonString);
		User[] fancyUsers = gson.fromJson(jsonString, User[].class);
		for (int i = 0; i < fancyUsers.length; i++) {
			assertEquals(users[i].getName(), fancyUsers[i].getName(), "Username stimmt nicht.");
			assertEquals(users[i].getPassword(), fancyUsers[i].getPassword(), "Passwort stimmt nicht.");
		}
	}

	@Test
	void getAllUsersFromDB() {
		System.out.println("GetAllUsersFromDB:");
		List<User> users = User.getAll();
		assertNotNull(users, "Users sind null!");
		for (User user : users) {
			System.out.println("\t" + user.toString());
		}
	}
}
