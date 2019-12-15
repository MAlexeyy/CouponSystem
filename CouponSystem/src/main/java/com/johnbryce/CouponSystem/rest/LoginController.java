package com.johnbryce.CouponSystem.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.enums.ClientType;

@RestController
public class LoginController {
	
	@RequestMapping
	public String Login(@RequestParam String user, @RequestParam String password, @RequestParam String type) {
		if(user.equals("Admin") && password.equals("1234") && type.equals("Admin")) {
			return "This is admin";
		}else if(user.equals("Moshe") && password.equals("1234") && type.equals("Customer")) {
			return "This is Moshe customer";
		}else if(user.equals("Kobi") && password.equals("1234") && type.equals("Customer")){
			return "This is Kobi customer";
		}else if(user.equals("Pepsi") && password.equals("1234") && type.equals("Company")) {
			return "This is Pepsi company";
		}else if(user.equals("Cola") && password.equals("1234") && type.equals("Company")){
			return "This is Cola Company";
		}
		return null;
	}
}
