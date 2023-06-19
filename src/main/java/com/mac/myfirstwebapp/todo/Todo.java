package com.mac.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String userName;
	@Size(min = 5, message = "Enter Atleast 5 characters")
	private String description;
	private LocalDate targetDate;
	private boolean done;

	

	

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todo(int id, String name, String userName,
			@Size(min = 5, message = "Enter Atleast 5 characters") String description, LocalDate targetDate,
			boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	/*
	 * public Todo(int id, String name, String description, LocalDate targetDate,
	 * Boolean done) { super(); this.id = id; this.name = name; this.description =
	 * description; this.targetDate = targetDate; this.done = done; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", userName=" + userName + ", description=" + description
				+ ", targetDate=" + targetDate + ", done=" + done + "]";
	}

	/*
	 * @Override public String toString() { return "Todo [id=" + id + ", Name=" +
	 * name + ", description=" + description + ", targetDate=" + targetDate +
	 * ", done=" + done + "]"; }
	 */

}
