package com.converter.service;

import java.util.List;

import com.converter.model.Currency;
import com.converter.model.Quotation;

public interface CurrencyService {
	
	List<Currency> list();
	Quotation quote(Quotation quotation);

}
