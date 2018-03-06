package com.converter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.converter.model.Currency;
import com.converter.model.Quotation;
import com.converter.service.CurrencyService;
import com.converter.service.QuotationService;

@RestController()
@RequestMapping("/api")
public class CurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private QuotationService quotationService;
	
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
	
	@RequestMapping(value="/quote", method=RequestMethod.POST)
	public Quotation quote(@RequestBody Quotation quotation) {
		return this.currencyService.quote(quotation);
	}
	
	@RequestMapping(value="/historic", method=RequestMethod.GET)
	public List<Quotation> historic(){
		return quotationService.historic();
	}
}
