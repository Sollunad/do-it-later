package jdbc;

public class User {
	int id;
	String username;
	String passwort;
	
	public User() { }
	
	public User(String username, String passwort) {
		this.username = username;
		this.passwort = passwort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	

}
