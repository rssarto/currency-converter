package com.converter.service;

import java.util.List;

import com.converter.model.Quotation;

public interface QuotationService {
	
	Quotation save(Quotation quotation);
	Quotation findById(Long id);
	List<Quotation> historic();

}
