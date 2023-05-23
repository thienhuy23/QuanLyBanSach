 package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsersRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users account = repo.findByEmail(username);
        if(account == null){
            throw new UsernameNotFoundException(username);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return new org.springframework.security.core.userdetails.User(
        account.getEmail(),
        account.getPassword(),
        true,
        true,
        true,
        true,
        Arrays.asList(new SimpleGrantedAuthority("USER")));
	}

}
