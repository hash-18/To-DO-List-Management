package com.mac.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public Boolean validate(String userName, String password)
	{
		return (userName.equalsIgnoreCase("Mayank")&&password.equals("Pandey"));
		
	}
}