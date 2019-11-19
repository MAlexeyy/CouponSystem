package com.johnbryce.CouponSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.repo.CouponsRepo;

@Service
public class ClientService {
	@Autowired
	private CouponsRepo repo;

}
