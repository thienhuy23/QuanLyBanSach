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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
				.requestMatchers( "/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/cart","/bill").hasAuthority("USER")
				.anyRequest().authenticated()
				.and()
				.authenticationProvider(authenticationProvider())
				// .httpBasic();
				.formLogin() //cho phép người dùng xác thực bằng login 
				.successHandler(savedRequestAwareAuthenticationSuccessHandler())
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				// .loginProcessingUrl("/perform_login")
				// .successForwardUrl("/")
				.defaultSuccessUrl("/")
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
   @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }
}
