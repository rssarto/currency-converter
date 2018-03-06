package com.converter.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.converter.model.Currency;
import com.converter.model.Quotation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@SuppressWarnings("unchecked")
public class CurrencyServiceImpl implements CurrencyService {
	
	private static final String API_ACCESS_KEY = "c16539abf71c9f74fc7b314ea2a08bd0";
	private static final String BASE_API_URL = "http://apilayer.net/api";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Currency> list() {
		List<Currency> currencyList = null;
		String raw = this.callRestApi(BASE_API_URL + "/list?access_key=" + API_ACCESS_KEY);
		if( raw != null ) {
			try {
				JsonNode rootNode = this.getRootNode(raw);
				if( this.isSuccess(rootNode) ) {
					JsonNode nodeCurrencies = this.getNode(rootNode, "currencies");
					Map<String, String> currencyMap = objectMapper.convertValue(nodeCurrencies, Map.class);
					if( currencyMap != null && !currencyMap.isEmpty() ) {
						currencyList = new ArrayList<>();
						for( Entry<String, String> entry : currencyMap.entrySet() ) {
							currencyList.add(new Currency(entry.getKey(), entry.getValue()));
						}
					}
				}
			}catch(IOException ex) {}
		}
		return currencyList;
	}

	@Override
	public Quotation quote(Quotation quotation) {
		quotation.setTime(new Date());
		String raw = this.callRestApi(BASE_API_URL + "/convert?access_key=" + API_ACCESS_KEY + "&from=" + quotation.getSource() + "&to=" + quotation.getDestination() + "&amount=" + quotation.getAmount());
		if( raw != null ) {
			try {
				JsonNode rootNode = this.getRootNode(raw);
				if( this.isSuccess(rootNode) ) {
					JsonNode resultNode = this.getNode(rootNode, "result");
					quotation.setResult(Double.parseDouble(resultNode.asText()));
				}else {
					quotation.setResult(5.00);
				}
			}catch(IOException ex) {}
			quotation.setUser(userService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
			return quotationService.save(quotation);
		}
		return null;
	}
	
	private String callRestApi(String url) {
		return restTemplate.getForObject(url, String.class);
	}
	
	private JsonNode getRootNode(String raw) throws IOException {
		return objectMapper.readTree(new ByteArrayInputStream(raw.getBytes()));
	}
	
	private boolean isSuccess(JsonNode jsonNode) {
		JsonNode successNode = jsonNode.get("success");
		return successNode.asText().equals(Boolean.TRUE.toString());
	}
	
	private JsonNode getNode(JsonNode source, String nodeName) {
		return source.get(nodeName);
	}
	
	
}