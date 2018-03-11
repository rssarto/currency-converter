package com.currencyconverter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.converter.app.CurrencyConverterAppApplication;
import com.converter.controller.UserController;
import com.converter.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {CurrencyConverterAppApplication.class})
@PropertySource("classpath:application.properties")
@WebMvcTest(UserController.class)
public class UserControllerTest extends AbstractControllerTest {
	
	@Test
	public void addNewUser() throws Exception {
		User user = getNewUser();
		this.addUser(user).andExpect(status().isOk());
	}
	
	@Test(expected=Exception.class)
	public void addDuplicateUserName()throws Exception{
		User registeredUser = this.getRegisteredUser();
		Assert.assertNotNull(registeredUser);
		User newUser = getNewUser();
		newUser.setUserName(registeredUser.getUserName());
		this.addUser(newUser);
	}
	
	@Test(expected=Exception.class)
	public void addDuplicateEmail()throws Exception{
		User registeredUser = this.getRegisteredUser();
		Assert.assertNotNull(registeredUser);
		User newUser = getNewUser();
		newUser.setUserName(registeredUser.getEmail());
		this.addUser(newUser);
	}
	
	public ResultActions addUser(User user) throws Exception {
		return executePostRequest("/signup", user);
	}
	
	private ResultActions getUser(int customerId, HttpStatus expectedStatus)throws Exception{
		return executeGetRequest("/user/" + customerId);
	}
	
	private User getRegisteredUser() throws Exception {
		Page<User> pageRegisteredUser = userDao.findAll(new PageRequest(0, 1));
		return pageRegisteredUser.getContent().get(0);
	}
	
	public User getNewUser() {
		int randomInt = ThreadLocalRandom.current().nextInt();
		return new User("teste" + randomInt, "123456", "teste" + randomInt + "@teste.com.br", "Rua Marcondes do Amaral, " + randomInt, "03661-000", "SAO PAULO", "BRAZIL");
	}

}
