package com.mac.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	 @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	    	
	    	
			
	    	UserDetails user1 = createNewUser("Mayank", "Pandey");
	    	UserDetails user2 = createNewUser("Mac", "Pan");
	        return new InMemoryUserDetailsManager(user1,user2);
	    }

	private UserDetails createNewUser(String userName, String password) {
		Function<String, String> encoder = input -> passwordEncoder().encode(input);
		UserDetails user = User.builder().passwordEncoder(
				encoder
				)
		    .username(userName)
		    .password(password)
		    .roles("USER","ADMIN")
		    .build();
		return user;
	}
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    } 

//All URLs are protected.
//Login form is shown for unauthorised requests
//To access H@ - Disable CSRF
//H2 websites makes use of Frames which spring security by default doesnot allows


@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
	http.authorizeHttpRequests(
			auth -> auth.anyRequest().authenticated());
	http.formLogin(withDefaults());
	
	http.csrf().disable();
	http.headers().frameOptions().disable();
	
	return http.build();
}
}