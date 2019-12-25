package com.johnbryce.CouponSystem.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private Map<String, Session> tokensMap;

	private Session isActive(String token) {
		return tokensMap.get(token);
	}
	
	@PostMapping("/purchaseCoupon/{couponId}")
	public Customer purchaseCoupon(@RequestBody Customer customer, @PathVariable long couponId) throws Exception {
		return customerService.purchaseCoupon(customer, couponId);
	}
}
