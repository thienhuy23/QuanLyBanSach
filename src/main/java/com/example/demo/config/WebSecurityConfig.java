package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Autowired
			UserDetailsServiceImpl s;

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return s.loadUserByUsername(username);
			}
		};
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.requestMatchers("/cart", "/admin")
				.authenticated()
				.anyRequest()
				.permitAll()
				.and()
				.authenticationProvider(authenticationProvider())
				// .httpBasic();
				.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				// .loginProcessingUrl("/perform_login")
				// .successForwardUrl("/")
				.defaultSuccessUrl("/home", true)
				.permitAll()
				.and()
				.logout()
				.permitAll();
		;
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncodeer() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(passwordEncodeer());
		return dao;
	}

}
