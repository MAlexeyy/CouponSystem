package com.johnbryce.CouponSystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController {

	@RequestMapping("/people")
	@CrossOrigin
	public String getPeople(String str) {
		return str;
	}
}
