package com.example.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.apigateway.util.ExceptionHandlerCustomer;
import com.example.apigateway.util.jwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouterValider valider;

	@Autowired
	private jwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);

	}

	@Override
	public GatewayFilter apply(Config config)  {
		return ((exchange, chain)	 -> {
			if (valider.isSecured.apply(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorization");
				}
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
					try {
						jwtUtil.validToken(authHeader);
					} catch (Exception e) {
						throw new ExceptionHandlerCustomer("UNAUTHENTICATION", "401");
					}
				}

			}

			return chain.filter(exchange);
		});
	}

	@Configuration
	public static class Config {
	}
}
