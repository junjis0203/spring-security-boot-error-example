package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMINISTRATOR")
				.anyRequest().authenticated()
			.and()
				.formLogin()
			.and()
				.logout();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserBuilder builder = User.withDefaultPasswordEncoder();
		UserDetails admin = builder.username("admin").password("admin").roles("ADMINISTRATOR").build();
		UserDetails user = builder.username("user").password("pass").roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

}
