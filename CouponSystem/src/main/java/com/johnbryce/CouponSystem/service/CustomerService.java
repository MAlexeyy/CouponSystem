package com.johnbryce.CouponSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.repo.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepo repo;
	
	
}
