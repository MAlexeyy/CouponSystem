package com.johnbryce.CouponSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.repo.CompanyRepo;

@Service
public class AdminService {
	@Autowired
	CompanyRepo companyRepo;
}
