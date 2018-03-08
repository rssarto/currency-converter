package com.zooplus.currencyconverterapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.converter.app.CurrencyConverterAppApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {CurrencyConverterAppApplication.class})
@ContextConfiguration(classes= {CurrencyConverterAppApplication.class})
public class CurrencyConverterAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
