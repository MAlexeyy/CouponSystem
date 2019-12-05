package com.johnbryce.CouponSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CouponRepo;

/**
 * This is....
 * @author alexeymx
 *
 */
@Service
public class CompanyService {

	@Autowired
	CouponRepo couponRepo;

	@Autowired
	CompanyRepo companyRepo;

	public void addCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}

//	public void updateCoupon(Coupon coupon) {
//		
//	}

	/**
	 * 
	 * @param coupons
	 */
	public void deleteCoupon(Coupon coupon) {
		couponRepo.delete(coupon);
	}

//	public List<Coupon> getCompanyCoupons(long companyId){
//		return 
//	}

//	public List<Coupon> getCompanyCoupons(Category category){
//		return
//	}

//	public List<Coupon> getCompanyCoupons(double maxPrice){
//		return
//	}

	public Optional<Company> getCompanyDetails(long companyId) {
		return companyRepo.findById(companyId);
	}

}
