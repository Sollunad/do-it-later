package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import database.jdbcConnector;

public class Task extends DatabaseObject {
	private int id;
	private String title;
	private String description;
	private TaskStatus status;
	private int group;
	
	public String toString() {
		return id + " " + title + " " + description + " " + status + " " + group;
	}
	
	public Task(String title, String description, TaskStatus status, int group) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.group = group;
	}
	
	public Task(int id, String title, String description, TaskStatus status, int group) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.group = group;
	}

	public static Task getById(int id) {
		String queryString = String
				.format("SELECT `TaskID` id, `Titel` title, `Beschreibung` description, `Status` status, `fk_GroupID` `group` FROM `TASK` WHERE `TaskID`=%d;", id);
		try {
			String resultString = jdbcConnector.query(queryString);
			System.out.println(resultString);
			if (resultString == null)
				return null;
			Gson gson = new Gson();
			return gson.fromJson(resultString, Task[].class)[0];
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Task[] getByGroup(int id) {
		String queryString = String
				.format("SELECT `TaskID` id, `Titel` title, `Beschreibung` description, `Status` status, `fk_GroupID` `group` FROM `TASK` WHERE `fk_GroupID`=%d;", id);
		try {
			String resultString = jdbcConnector.query(queryString);
			System.out.println(resultString);
			if (resultString == null)
				return null;
			Gson gson = new Gson();
			return gson.fromJson(resultString, Task[].class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void query() {
		//TODO
	}

	@Override
	public void delete() {
		String deleteString = String.format("DELETE FROM `Task` WHERE `TaskID`=%d ;", id);
		try {
			jdbcConnector.query(deleteString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		String queryString = String
				.format("SELECT * FROM `Task` WHERE `TaskID`=%d;", id);
		try {
			String resultString = jdbcConnector.query(queryString);
			return  (resultString != null);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void persist() {
		String updateString = String.format("INSERT INTO `TASK`(`Titel`, `Beschreibung`, `Status`, `fk_GroupID`) VALUES (\"%s\",\"%s\",\"%s\",%d);", 
				title, description, status, group);
		try {
			jdbcConnector.query(updateString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void register() {
		String updateString = String.format("UPDATE `TASK` SET `Titel` = \"%s\", `Beschreibung` = \"%s\", `Status` = \"%s\", `fk_GroupID` = %d WHERE `TaskID`=%d;", 
				title, description, status, group, id);
		try {
			jdbcConnector.query(updateString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

}
