package com.johnbryce.CouponSystem.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Coupon;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {
	
	public void addCoupon(Coupon coupon);

	public void updateCoupon(Coupon coupon);

	public void deleteCouponById(int id);

	public ArrayList<Coupon> getAllCoupons();

	public Coupon getOneCoupon(int id);

	public void addCouponPurchase(int customerID, int couponID);

	public void deleteCouponPurchase(int customerID, int couponID);

	public void deleteCouponByCompanyId(int id);
}
