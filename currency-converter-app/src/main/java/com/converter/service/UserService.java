package com.converter.service;

import java.util.List;

import com.converter.model.User;

public interface UserService {
	
    User save(User user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);	

}
