package com.converter.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.converter.model.Currency;
import com.converter.service.CurrencyService;

@RestController()
@RequestMapping("/api")
public class CurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	/*
	@RequestMapping(value= "/**", method=RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET,listAllCurrencies POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");
	}
	*/
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET, produces="application/json")
	public List<Currency> list(){
		return currencyService.list();
	}
}
