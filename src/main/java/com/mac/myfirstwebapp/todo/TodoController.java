package com.mac.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("userName")

public class TodoController {

	@Autowired
	private TodoService todoService;

	@RequestMapping("list-todos")
	public String listAllTodos(Model model) {
		String userName = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUserName(userName);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value = "add-todos", method = RequestMethod.GET)
	public String showAddTodosPage(Model model) {
		Todo todo = new Todo(); // Whatever values we enter here is visble in the front in the form
		// First SIde Binding - From COntroller to the form
		model.addAttribute("todo", todo);// This "todo" should match the parameter in modelAttribute in form
		return "Todo";
	}

	/*
	 * @RequestMapping(value="add-todos",method = RequestMethod.POST) public String
	 * AddTodos(@RequestParam("name") String name, @RequestParam("description")
	 * String description, Model model) { String
	 * userName=(String)model.getAttribute("userName");
	 * todoService.addTodo(userName,name, description, LocalDate.now().plusYears(2),
	 * false); return "redirect:list-todos"; }
	 */

	@RequestMapping(value = "add-todos", method = RequestMethod.POST)
	public String AddTodos(Model model, @Valid Todo todo, BindingResult result) { // This "todo" should match the
																					// parameter in modelAttribute in
																					// form
		// Second side Binding - Whatever data is entered in the form is binded to the
		// variable todo

		if (result.hasErrors())
			return "Todo";
		String userName = getLoggedInUsername(model);
		todoService.addTodo(userName, todo.getName(), todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, Model model) {
		Todo todo = todoService.getById(id);
		model.addAttribute("todo", todo);
		return "Todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String UpdateTodo(Model model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors())
			return "Todo";
		String userName = getLoggedInUsername(model);
		todo.setUserName(userName);
		todo.setDone(false);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
