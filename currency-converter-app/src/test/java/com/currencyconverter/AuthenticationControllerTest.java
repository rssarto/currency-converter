package com.currencyconverter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.converter.app.CurrencyConverterAppApplication;
import com.converter.controller.AuthenticationController;
import com.converter.controller.UserController;
import com.converter.model.LoginUser;
import com.converter.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {CurrencyConverterAppApplication.class})
@PropertySource("classpath:application.properties")
@WebMvcTest(controllers={AuthenticationController.class, UserController.class})
public class AuthenticationControllerTest extends AbstractControllerTest {
	
	@Test
	public void login() throws Exception {
		UserControllerTest userControllerTest =  new UserControllerTest();
		userControllerTest.populateDependencies(this);
		User user = userControllerTest.getNewUser();
		userControllerTest.addUser(user);
		LoginUser loginUser = new LoginUser(user.getUserName(), user.getPassword());
		this.executePostRequest("/token/generate-token", loginUser)
			.andExpect(status().isOk());
	}

}
