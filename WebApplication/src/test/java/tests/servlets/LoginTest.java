package tests.servlets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import servlets.Login;

class LoginTest {	
	@Test
	void testPOST() {
		System.out.println("rest/authenticate -> POST:");
		Login login = new Login();
		login.postLogin("Test", "test@12345");
		assertTrue(login.postLogin("Test", "test@12345"), "Login hätte nicht funktioniert!");
	}
	
	@Test
	void testPUT() {
		System.out.println("rest/authenticate -> POST:");
		Login login = new Login();
		login.putLogin("Test", "test@12345");
		assertTrue(login.postLogin("Test", "test@12345"), "Login hätte nicht funktioniert!");
	}

}
