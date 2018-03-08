package resources;

public abstract class DatabaseObject {

	public abstract void query();

	public abstract void persist();

	public abstract void delete();

	public abstract boolean exists();

	public abstract String toString(); // Testing
}
