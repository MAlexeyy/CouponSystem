package com.johnbryce.CouponSystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.service.ClientService;

@RestController
@RequestMapping("coupons")
public class CouponsController {
	@Autowired
	ClientService service;
}