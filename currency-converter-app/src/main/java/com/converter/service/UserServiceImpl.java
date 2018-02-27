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
import com.converter.model.User;

@Service(value="userService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;	

	@Override
	public User save(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
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
	public User findOne(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public User findById(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if( user == null ) {
			throw new UsernameNotFoundException("Invalid user name or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}	
}
