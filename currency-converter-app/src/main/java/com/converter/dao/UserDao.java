package com.converter.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.converter.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	
	User findByUserName(String userName);

}
