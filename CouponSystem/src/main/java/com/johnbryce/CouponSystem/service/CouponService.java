package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.repo.CouponRepo;

@Service
public class CouponService {
	@Autowired
	CouponRepo repo;

	public void addCoupon(Coupon c) {
		repo.save(c);
	}

	public void updateCoupon(Coupon c) {
		repo.save(c);
	}

	public void deleteCoupon(int couponId) {
		repo.deleteById(couponId);
	}

	public List<Coupon> getAllCoupons() {
		return repo.findAll();
	}

	public Optional<Coupon> getOneCoupon(int couponId) {
		return repo.findById(couponId);
	}

	// TODO
	public void addCouponPurchase(int coustomerId, int couponId) {
	}

	// TODO
	public void deleteCouponPuchase(int customerId, int couponId) {
	}
}
