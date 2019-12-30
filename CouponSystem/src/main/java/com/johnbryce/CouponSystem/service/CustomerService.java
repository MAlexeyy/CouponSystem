package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Locale.Category;

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
	public Customer purchaseCoupon(Coupon coupon) throws Exception {
		try {
			if (!couponRepo.existsById(coupon.getId())) {
				throw new Exception("No coupon with such ID to purchase");
			} else if (coupon.getEnd_date().before(DateConfig.dateNow())) {
				throw new Exception("Coupon has expired.");
			} else if (coupon.getAmount() < 1) {
				throw new Exception("This coupon has ran out.");
			} else if (customerRepo.findById(customerId).get().getCoupons().contains(coupon)) {
				throw new Exception("Customer has aleady purchased this coupon");
			}

			Coupon tmpCoupon = coupon;
			Customer tmpCustomer = customerRepo.findById(customerId).get();
			tmpCoupon.setAmount(tmpCoupon.getAmount() - 1);
			tmpCustomer.getCoupons().add(tmpCoupon);
			customerRepo.save(tmpCustomer);
			couponRepo.save(tmpCoupon);
			return tmpCustomer;

		} catch (Exception e) {
			throw new Exception("Failed to purchase coupon: " + e.getMessage());
		}
	}

	// All customer coupons.
	public List<Coupon> getCustomerCoupons() throws Exception {
		try {
			if (customerRepo.findById(customerId).get().getCoupons().isEmpty()) {
				throw new Exception("Customr has no coupons.");
			}
			return customerRepo.findById(customerId).get().getCoupons();
		} catch (Exception e) {
			throw new Exception("Failed to get customers coupons: " + e.getMessage());
		}
	}

	// Customer coupons from specific category.
	public List<Coupon> getCustomerCoupons(CouponType category) throws Exception {
		try {
			if (customerRepo.findById(customerId).get().getCoupons().isEmpty()) {
				throw new Exception("Customr has no coupons.");
			}
			List<Coupon> tmpCoupons = null;
			for (Coupon c : customerRepo.findById(customerId).get().getCoupons()) {
				if (c.getCategory().equals(category)) {
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
		try {
			if (customerRepo.findById(customerId).get().getCoupons().isEmpty()) {
				throw new Exception("Customr has no coupons.");
			}
			List<Coupon> tmpCoupons = null;
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
	public String getCustomerDetails() {
		return customerRepo.findById(customerId).get().toString();
	}

}
