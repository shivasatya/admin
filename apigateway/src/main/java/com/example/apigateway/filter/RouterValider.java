package com.example.apigateway.filter;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;

@Component
public class RouterValider {


	public static final List<String> openUrl = List.of(
			"/auth/admin/login",
			"/user/get/UserDetails/email",
			"/user/sigup", 
			"/auth/refresh/token",
			"/admin/create/admin",
			"/admin/get/adminDetails");


	public Predicate<ServerHttpRequest> isSecured = 
			request -> openUrl
			         .stream()
			         .noneMatch(uri ->request.getURI().getPath().contains(uri));
}
