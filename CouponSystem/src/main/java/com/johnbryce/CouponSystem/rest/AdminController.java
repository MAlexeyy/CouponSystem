package com.johnbryce.CouponSystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.service.AdminService;
import com.johnbryce.CouponSystem.service.CompanyService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	CompanyService companyService;

	// http://localhost:8080/admin/getCompany
	@GetMapping("/getCompany")
	public List<Company> getAllCompanies() {
		return adminService.getAllCompanies();
	}

	// http://localhost:8080/admin/getCompany1
	@GetMapping("/getCompany1")
	public List<Coupon> getCompanyCoupons() {
		return companyService.getAllCoupons();
	}

	// http://localhost:8080/admin/getCompany1
	@GetMapping("/getCompany2")
	public List<Customer> getAllCustomers() {
		return adminService.getAllCustomers();
	}

}
