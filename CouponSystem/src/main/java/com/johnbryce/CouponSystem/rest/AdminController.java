package com.johnbryce.CouponSystem.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private Map<String, Session> tokensMap;

	private Session isActive(String token) {
		return tokensMap.get(token);
	}

	// http://localhost:8080/admin/getAllCompanies
//	@GetMapping("/getAllCompanies/{token}")
//	public List<Company> getAllCompanies(@PathVariable String token) {
//		Session clientSession = isActive(token);
//		if(clientSession != null) {
//			return adminService.getAllCompanies();
//		} else {
//			return null;
//		}	
//	}
	
	@GetMapping("/getAllCompanies/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		Session clientSession = isActive(token);
		if (clientSession != null) {
			clientSession.setLastAccessed(System.currentTimeMillis());
			try {
				return new ResponseEntity<> (adminService.getAllCompanies(), HttpStatus.OK);
			} catch (Exception e) {
				e.getMessage();
				return new ResponseEntity<>("Failed to display companies ", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Unauthorized. Session Timeout", HttpStatus.UNAUTHORIZED);
		}
	}

	// http://localhost:8080/admin/getCoupons
	@GetMapping("/getCoupons")
	public List<Coupon> getCompanyCoupons() {
		return companyService.getAllCoupons();
	}

	// http://localhost:8080/admin/getCustomers
	@GetMapping("/getCustomers")
	public List<Customer> getAllCustomers() {
		try {
			return adminService.getAllCustomers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getCustomer/{id}")
	public Customer getOneCustomer(@PathVariable long id) throws Exception {
		return adminService.getOneCustomer(id);
	}
	
	@PostMapping("/addCoupon/{companyId}")
	public Company addCoupon(@RequestBody Coupon coupon, @PathVariable long companyId) throws Exception {
		return companyService.addCoupon(coupon, companyId);
	}
	
	@PostMapping("/addCompany")
	public Company addCompany(@RequestBody Company company) throws Exception {
		return adminService.addCompany(company);
	}
	
	@PostMapping("/addCustomer")
	public Customer addCustomer(@RequestBody Customer customer) throws Exception {
		return adminService.addCustomer(customer);
	}
	
	@PostMapping("/purchaseCoupon/{couponId}")
	public Customer purchaseCustomer(@RequestBody Customer customer, @PathVariable long couponId) throws Exception {
		return customerService.purchaseCoupon(customer, couponId);
	}

}
