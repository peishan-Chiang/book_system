package com.example.book_system.vo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookbuyerRequest {
	
	@JsonProperty("buy_list")
	private Map<String,Integer> buymap;//購書的map清單，isbn(品項):數量

	public Map<String, Integer> getBuymap() {
		return buymap;
	}

	public void setBuymap(Map<String, Integer> buymap) {
		this.buymap = buymap;
	}
	

}
