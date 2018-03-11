package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import resources.Validation;

class ValidationTest {

	@Test
	void registerPasswordHardness() {
		List<String> correct = new ArrayList<>();
		List<String> incorrect = new ArrayList<>();
		correct.add("aAbB66@qQq");
		incorrect.add("aAbB66aqQq");
		incorrect.add("aAbBeeaqQq");
		incorrect.add("aAbBee");
		for (String pw : correct)
			assertTrue(Validation.checkPasswordForHardness(pw), "Richtiges Passwort wurde als falsch bezeichnet!");
		for (String pw : incorrect)
			assertFalse(Validation.checkPasswordForHardness(pw), "Falsches Passwort wurde als richtig bezeichnet!");
	}

}
