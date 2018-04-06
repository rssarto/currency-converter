package com.converter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.converter.dao.UserDao;
import com.converter.exception.SignUpException;
import com.converter.model.User;

@Service(value="userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;	

	@Override
	public User save(User user) throws Exception {
		boolean isEmailAlreadyUsed = false;
		boolean isUserNameAlreadyUsed = false;
		
		User alreadyRegisteredUser = userDao.findByEmail(user.getEmail());
		if( alreadyRegisteredUser != null ) {
			isEmailAlreadyUsed = true;
		}
		
		alreadyRegisteredUser = userDao.findByUserName(user.getUserName()); 
		if( alreadyRegisteredUser != null ) {
			isUserNameAlreadyUsed = true;
		}
		
		if( isEmailAlreadyUsed || isUserNameAlreadyUsed ) {
			String exceptionMessage = isEmailAlreadyUsed && isUserNameAlreadyUsed ? "We already have an user registered with this user name and email address." : isEmailAlreadyUsed ? "We already have an user registered with this email address." : "We already have an user user registered with this user name.";
			throw new SignUpException(exceptionMessage);
		}
		
		
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		user.setEmail(user.getEmail().toLowerCase());
		return userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(users::add);
		return users;
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User findOne(String userNamePassword) throws UsernameNotFoundException {
		User user = userDao.findByEmail(userNamePassword);
		if( user == null ) {
			user = userDao.findByUserName(userNamePassword);
			if( user == null ) {
				throw new UsernameNotFoundException("Invalid user name or password.");	
			}
		}		
		
		return user;
	}

	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}
	
		@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String userNameEmail) throws UsernameNotFoundException {
		User user = this.findOne(userNameEmail);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}	
}

