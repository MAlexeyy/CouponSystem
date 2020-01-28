package com.johnbryce.CouponSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.Config.DateConfig;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.enums.CouponType;
import com.johnbryce.CouponSystem.repo.CouponRepo;
import com.johnbryce.CouponSystem.repo.CustomerRepo;

@Service
public class CustomerService implements Facade {

	@Autowired
	CouponRepo couponRepo;

	@Autowired
	CustomerRepo customerRepo;

	long customerId;

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	// Purchasing a single coupon.
	public Customer purchaseCoupon(long couponId) throws Exception {
		Coupon coupon = couponRepo.findById(couponId).get();
		try {
			if (!couponRepo.existsById(coupon.getId())) {
				throw new Exception("No coupon with such ID to purchase");
//	TODO: ADD end date to coupons to check this functionality.			
//			} else if (coupon.getEnd_date().before(DateConfig.dateNow())) {
//				throw new Exception("Coupon has expired.");
			} else if (coupon.getAmount() < 1) {
				throw new Exception("This coupon has ran out.");
			} else if (customerRepo.findById(customerId).get().getCoupons().contains(coupon)) {
				throw new Exception("Customer has aleady purchased this coupon");
			}

			couponRepo.save(coupon);
			Customer customer = customerRepo.findById(customerId).get();
			customer.getCoupons().add(coupon);
			customerRepo.save(customer);
			coupon.setAmount(coupon.getAmount() - 1);
			return customer;

		} catch (Exception m) {
			throw new Exception("Failed to purchase coupon: " + m.getMessage());
		}
	}

	// All customer coupons.
	public List<Coupon> getCustomerCoupons() throws Exception {
		try {
			if (customerRepo.findById(customerId).get().getCoupons().isEmpty() || customerRepo.findById(customerId).get().getCoupons() == null) {
				throw new Exception("Customr has no coupons.");
			}
			return customerRepo.findById(customerId).get().getCoupons();
		} catch (Exception e) {
			throw new Exception("Failed to get customers coupons: " + e.getMessage());
		}
	}

	// Customer coupons from specific category.
	public List<Coupon> getCustomerCoupons(String type) throws Exception {
		CouponType couponType = CouponType.valueOf(type);
		List<Coupon> tmpCoupons = new ArrayList<Coupon>();
		List<Coupon> coupons = customerRepo.findById(customerId).get().getCoupons();
		try {
//			if (customerRepo.findById(customerId).get().getCoupons().isEmpty()) {
//				throw new Exception("Customr has no coupons.");
//			}		
			for (Coupon c : coupons) {
				if (c.getCategory().equals(couponType)) {
					tmpCoupons.add(c);
				}
			}
			return tmpCoupons;
		} catch (Exception e) {
			throw new Exception("Failed to get customer coupons: " + e.getMessage());
		}
	}

	// Customer coupons with a maximum price.
	public List<Coupon> getCustomerCoupons(double maxPrice) throws Exception {
		List<Coupon> tmpCoupons = new ArrayList<Coupon>();
		try {
			if (customerRepo.findById(customerId).get().getCoupons().isEmpty()) {
				throw new Exception("Customr has no coupons.");
			}						
			for (Coupon c : customerRepo.findById(customerId).get().getCoupons()) {
				if (c.getPrice() <= maxPrice) {
					tmpCoupons.add(c);
				}
			}
			return tmpCoupons;
		} catch (Exception e) {
			throw new Exception("Failed to get customer coupons: " + e.getMessage());
		}
	}

	// Customer information.
	public Customer getCustomerDetails() {
		return customerRepo.findById(customerId).get();
	}

}
