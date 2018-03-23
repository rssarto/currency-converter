package com.converter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.converter.model.User;
import com.converter.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UserController {
	
    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @ApiOperation(value="Find user by Id", notes="${UserController.getOne.notes}")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@ApiParam(value="${UserController.getOne.id}", required=true, allowEmptyValue=false)@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }
    
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@Valid @RequestBody User user)throws Exception{
    	return userService.save(user);
    }    

}
