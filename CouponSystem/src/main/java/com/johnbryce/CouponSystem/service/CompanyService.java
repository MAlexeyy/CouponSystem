package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CouponRepo;

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

	public void deleteCoupon(Coupon coupon) {
		couponRepo.delete(coupon);
	}

//	public List<Coupon> getCompanyCoupons(Long companyId){
//		return 
//	}

//	public List<Coupon> getCompanyCoupons(Category category){
//		return
//	}

//	public List<Coupon> getCompanyCoupons(double maxPrice){
//		return
//	}

	public Optional<Company> getCompanyDetails(Long companyId) {
		return companyRepo.findById(companyId);
	}

}
