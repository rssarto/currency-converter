package com.converter.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.converter.model.Currency;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@SuppressWarnings("unchecked")
public class CurrencyServiceImpl implements CurrencyService {

	@Override
	public List<Currency> list() {
		List<Currency> currencyList = null;
		RestTemplate restTemplate = new RestTemplate();
		String raw = restTemplate.getForObject("http://apilayer.net/api/list?access_key=c16539abf71c9f74fc7b314ea2a08bd0", String.class);
		if( raw != null ) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				JsonNode rootNode = objectMapper.readTree(new ByteArrayInputStream(raw.getBytes()));
				JsonNode nodeCurrencies = rootNode.get("currencies");
				Map<String, String> currencyMap = objectMapper.convertValue(nodeCurrencies, Map.class);
				if( currencyMap != null && !currencyMap.isEmpty() ) {
					currencyList = new ArrayList<>();
					for( Entry<String, String> entry : currencyMap.entrySet() ) {
						currencyList.add(new Currency(entry.getKey(), entry.getValue()));
					}
				}
			}catch(IOException ex) {}
		}
		return currencyList;
	}
}