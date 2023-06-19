package com.mac.myfirstwebapp.Hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	//say-hello -> Hello, what did you learn today.?
	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello()
	{
		return "Hello, what did you learn today.?";
	}
	
	//JSP file is in =- /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	//As we see for diff jsps -> the prefix path and the suffix(.jsp) will be constant. SO let's store this in application.properties
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp()
	{
		return "sayHello";
	}

}
