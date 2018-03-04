package com.converter.service;

import java.util.List;

import com.converter.model.User;

public interface UserService {
	
    User save(User user) throws Exception;
    List<User> findAll();
    void delete(long id);
    User findOne(String userNameEmail);

    User findById(Long id);
    User findByEmail(String email);

}
