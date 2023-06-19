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

@Controller
@SessionAttributes("userName")

public class TodoControllerJPA {

	//@Autowired
	//private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping("list-todos")
	public String listAllTodos(Model model) {
		
		String userName=getLoggedInUsername(model);
		List<Todo> todos=todoRepository.findByUserName(userName);
		model.addAttribute("userName", userName);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

	@RequestMapping(value = "add-todos", method = RequestMethod.GET)
	public String showAddTodosPage(Model model) {
		Todo todo=new Todo();
		String userName=getLoggedInUsername(model);
		todo.setUserName(userName);
		model.addAttribute("todo", todo);
		return "Todo";
	}


	@RequestMapping(value = "add-todos", method = RequestMethod.POST)
	public String AddTodos(Model model, @Valid Todo todo, BindingResult result) { // This "todo" should match the
																					// parameter in modelAttribute in
																					// form
		// Second side Binding - Whatever data is entered in the form is binded to the
		// variable todo

		if (result.hasErrors())
			return "Todo";
		
		String userName = getLoggedInUsername(model);
		todo.setUserName(userName);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		
		todoRepository.deleteById(id);
		return "redirect:list-todos";
		
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, Model model) {
		Todo todo=todoRepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "Todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String UpdateTodo(Model model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors())
			return "Todo";
		String userName = getLoggedInUsername(model);
		todo.setUserName(userName);
		todoRepository.save(todo);	
		return "redirect:list-todos";
		
	}

	private String getLoggedInUsername(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
