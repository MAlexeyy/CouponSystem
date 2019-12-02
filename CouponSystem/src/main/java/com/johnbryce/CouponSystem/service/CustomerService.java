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

	// TODO ask kobi, have to add customer id to this method?
	public void purchaseCoupon(Customer customer, Coupon coupon) {

	}

	public List<Coupon> getCustomerCoupons(Long customerId) {
		Customer tmp = customerRepo.findById(customerId).get();
		return (List<Coupon>) tmp.getCoupons();
	}

	public List<Coupon> getCustomerCoupons(Category category) {
		return null;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		return null;
	}

	// TODO ask kobi, if this is fine.
	public String getCustomerDetails(Long customerId) {
		return customerRepo.findById(customerId).get().toString();
	}

}
