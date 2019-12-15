package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.repo.CouponRepo;
import com.johnbryce.CouponSystem.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	CouponRepo couponRepo;

	@Autowired
	CustomerRepo customerRepo;
	
	//Login
//	@Autowired
//	Customer customer
	//OR
//	long customerId;
	
	
	// TODO ask kobi, have to add customer id to this method?
	public Customer purchaseCoupon(Customer customer, long id) throws Exception {
		if(!customerRepo.existsById(customer.getId()) || !couponRepo.existsById(id)) {
			throw new Exception("Customer or coupon does not exists.");
		} else {
			Customer newCustomer = customer;
			List<Coupon> coupons = newCustomer.getCoupons();
			coupons.add(couponRepo.findById(id).get());
			newCustomer.setCoupons(coupons);
			customerRepo.save(newCustomer);
			
			Coupon newCoupon = couponRepo.findById(id).get();
			List<Customer> customers = newCoupon.getCustomers();
			customers.add(customerRepo.findById(customer.getId()).get());
			newCoupon.setCustomers(customers);
			couponRepo.save(newCoupon);
			
			return customerRepo.findById(customer.getId()).get();
		}
	}

//	public List<Coupon> getCustomerCoupons(long customerId) {
//		Customer tmp = customerRepo.findById(customerId).get();
//		return (List<Coupon>) tmp.getCoupons();
//	}

	public List<Coupon> getCustomerCoupons(Category category) {
		return null;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		return null;
	}

	// TODO ask kobi, if this is fine.
	public String getCustomerDetails(long customerId) {
		return customerRepo.findById(customerId).get().toString();
	}

}
