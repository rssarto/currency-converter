package com.converter.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.converter.model.User;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Long> {
	
	User findByUserName(String userName);
	User findByEmail(String email);

}
