package com.mac.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todos=new ArrayList<>();
	private static int count=1;
	//To initialize static field, you need static block
	static
	{
		todos.add(new Todo(count++, "Spring Boot","Mac", "Used by Java Dev", LocalDate.now().plusYears(1),false));
		todos.add(new Todo(count++, "AWS","Mayank", "Used by Cloud  Dev", LocalDate.now().plusYears(2),false));
		todos.add(new Todo(count++, "AI","Mayank", "Used for Data Scientists", LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUserName(String username)
	{
		Predicate<Todo> predicate=todos->todos.getUserName().equalsIgnoreCase(username);
		
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String userName, String name, String description, LocalDate TargetDate, Boolean done)
	{
		Todo todo=new Todo(count++,name,userName, description, TargetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id)
	{
		Predicate<Todo> predicate=todos->todos.getId()==id;
		todos.removeIf(predicate);
	}
	
	public Todo getById(int id)
	{
		Predicate<Todo> predicate=todos->todos.getId()==id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	
	public void updateTodo(Todo todo)
	{
		deleteById(todo.getId());
		todos.add(todo);
	}

}
