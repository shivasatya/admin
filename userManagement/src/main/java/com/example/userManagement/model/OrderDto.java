package com.example.userManagement.model;

import lombok.Data;

@Data
public class OrderDto {

	private String userId;
	private String productID;
	private String amount;
	private String productQuantity;

}
