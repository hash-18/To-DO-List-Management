package com.mac.myfirstwebapp.Welcome;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userName")
public class WelcomeController {

	@RequestMapping("/")
	public String showWelcomePage(Model model)
	{
		model.addAttribute("userName", getLoggedinUsername());
		return "welcome";
	}
	
	private String getLoggedinUsername()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
		
	}
}
