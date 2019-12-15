package com.johnbryce.CouponSystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.service.AdminService;
import com.johnbryce.CouponSystem.service.CompanyService;
import com.johnbryce.CouponSystem.service.CustomerService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	CustomerService customerService;

	// http://localhost:8080/admin/getCompany
	@GetMapping("/getCompany")
	public List<Company> getAllCompanies() {
		return adminService.getAllCompanies();
	}

	// http://localhost:8080/admin/getCoupons
	@GetMapping("/getCoupons")
	public List<Coupon> getCompanyCoupons() {
		return companyService.getAllCoupons();
	}

	// http://localhost:8080/admin/getCustomers
	@GetMapping("/getCustomers")
	public List<Customer> getAllCustomers() {
		return adminService.getAllCustomers();
	}
	
	@GetMapping("/getCustomer/{id}")
	public Customer getOneCustomer(@PathVariable long id) throws Exception {
		return adminService.getOneCustomer(id);
	}
	
//	@PostMapping("/addCoupon/{companyId}")
//	public Company addCoupon(@RequestBody Coupon coupon, @PathVariable long companyId) throws Exception {
//		return companyService.addCoupon(coupon, companyId);
//	}
	
	@PostMapping("/addCompany")
	public Company addCompany(@RequestBody Company company) throws Exception {
		return adminService.addCompany(company);
	}

}
