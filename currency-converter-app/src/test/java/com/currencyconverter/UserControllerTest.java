package com.currencyconverter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.converter.app.CurrencyConverterAppApplication;
import com.converter.controller.UserController;
import com.converter.dao.UserDao;
import com.converter.exception.SignUpException;
import com.converter.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes= {CurrencyConverterAppApplication.class})
@ContextConfiguration(classes= {CurrencyConverterAppApplication.class})
@PropertySource("classpath:application.properties")
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void addNewUser() throws Exception {
		User user = getNewUser();
		this.addUser(user).andExpect(status().isOk());
	}
	
	@Test(expected=Exception.class)
	public void addDuplicateUserName()throws Exception{
		Page<User> pageRegisteredUser = userDao.findAll(new PageRequest(0, 1));
		User registeredUser = pageRegisteredUser.getContent().get(0);
		Assert.assertNotNull(registeredUser);
		User newUser = getNewUser();
		newUser.setUserName(registeredUser.getUserName());
		this.addUser(newUser);
	}
	
	private ResultActions addUser(User user) throws Exception {
		return this.mvc.perform(post("/signup")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(user)));
	}
	
	private void getUser(int customerId, HttpStatus expectedStatus)throws Exception{
		this.mvc.perform(get("/user/" + customerId)).andExpect(expectedStatus == HttpStatus.OK ? status().isOk() : status().isNotFound());
	}
	
	private static User getNewUser() {
		int randomInt = ThreadLocalRandom.current().nextInt();
		return new User("teste" + randomInt, "123456", "teste" + randomInt + "@teste.com.br", "Rua Marcondes do Amaral, " + randomInt, "03661-000", "SAO PAULO", "BRAZIL");
	}
	
	private static String toJson(Object object){
		String json = gson.toJson(object);
		System.out.println(json);
		return json;
	}	

}
