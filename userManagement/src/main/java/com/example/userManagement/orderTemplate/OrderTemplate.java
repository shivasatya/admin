package com.example.userManagement.orderTemplate;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.userManagement.model.OrderDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class OrderTemplate {

	private static final String ORDER_URL = "http://localhost:8080/order/save";

	public String postUserOrderDetails(OrderDto order) {

		String data = null;
		try {
			WebClient client = WebClient.create();
			data = client.post().uri(ORDER_URL).contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(order)).retrieve().bodyToMono(String.class).block();
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return data;
		// Execute the request
	}
}
