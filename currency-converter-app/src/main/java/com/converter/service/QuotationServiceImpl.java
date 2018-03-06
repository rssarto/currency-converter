package com.converter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.converter.dao.QuotationDao;
import com.converter.model.Quotation;

@Service
public class QuotationServiceImpl implements QuotationService {
	
	@Autowired
	private QuotationDao quotationDao;
	
	@Autowired
	private UserService userService;

	@Override
	public Quotation save(Quotation quotation) {
		return quotationDao.save(quotation);
	}

	@Override
	public Quotation findById(Long id) {
		return quotationDao.findOne(id);
	}

	@Override
	public List<Quotation> historic() {
		return this.quotationDao.getLastQuotations(userService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()).getId(), new PageRequest(0, 10));
	}
}
