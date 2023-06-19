package com.mac.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
/* @SessionAttributes("userName") */
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String goToLogin()
	{
		return "login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	
	public String goToWelcome(@RequestParam("username") String userName, @RequestParam("password") String password, Model model)
	{
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		if (loginService.validate(userName, password))
		return "welcome";
		
		model.addAttribute("error","Invalid Credentials");
		return "login";
	}
	
	

}
