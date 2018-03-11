package tests.servlets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import resources.User;
import resources.Validation;
import servlets.Register;

class RegisterTest {

	@Test
	void testGET() {
		Register register = new Register();
		assertTrue(register.getUser("Test"), "Test-User konnte nicht gefunden werden.");
	}
	
	@Test
	void testPOST() {
		Register register = new Register();
		User user = new User("TESTicle");
		String password = "qweruiop@1";
		user.setPassword(Validation.hash(password));
		String returnValue = register.registerUser(user.getName(), password);
		assertEquals(returnValue, "success", "Registrierung war nicht erfolgreich!");
		user.delete();
	}
}
