package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.enums.CouponType;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CouponRepo;

@Service
public class CompanyService {

	@Autowired
	CouponRepo couponRepo;

	@Autowired
	CompanyRepo companyRepo;

	public void addCoupon(Coupon coupon, long companyid) throws Exception {
		if (!companyRepo.existsById(companyid)) {
			throw new Exception("There is no company with such ID");
		} else {
			Company tempCompany = companyRepo.findById(companyid).get();
			for (Coupon c : tempCompany.getCoupons()) {
				if (c.getTitle().equals(coupon.getTitle())) {
					throw new Exception("Coupon with such title already exists in this company");
				}
			}
			coupon.setCompany(tempCompany);
//			tempCompany.getCoupons().add(coupon);
//			companyRepo.save(tempCompany);
			couponRepo.save(coupon);
		}
	}

	public Coupon updateCoupon(Coupon coupon) throws Exception {
		if (couponRepo.existsById(coupon.getId())) {

			Coupon tempCoupon = couponRepo.findById(coupon.getId()).get();
			tempCoupon.setAmount(coupon.getAmount());
			tempCoupon.setCategory(coupon.getCategory());
			tempCoupon.setDescription(coupon.getDescription());
			tempCoupon.setTitle(coupon.getTitle());
			tempCoupon.setStart_date(coupon.getStart_date());
			tempCoupon.setEnd_date(coupon.getEnd_date());
			tempCoupon.setPrice(coupon.getPrice());
			tempCoupon.setImage(coupon.getImage());
			couponRepo.save(tempCoupon);
			return tempCoupon;

		} else {

			throw new Exception("No coupon with such id to update");

		}
	}

	public Coupon deleteCoupon(Coupon coupon) throws Exception {
		try {
			couponRepo.delete(coupon);
			return coupon;
		} catch (Exception e) {
			throw new Exception("Failed to delete coupon");
		}
	}

	public List<Coupon> getCompanyCouponsById(long companyId) throws Exception{
		 if(companyRepo.existsById(companyId)) {
			 return couponRepo.findByCompany_Id(companyId);
		 } else {
			 throw new Exception("No company with such ID");
		 }
	}

	public List<Coupon> getCompanyCouponsByCategory(long companyId, CouponType couponType)throws Exception{
		if(companyRepo.existsById(companyId)) {
			 return couponRepo.findByCategory(couponType);
		 } else {
			 throw new Exception("No company with such ID");
		 }
	}

	public List<Coupon> getCompanyCouponsByMaxPrice(long companyId,double price) throws Exception{
		if(companyRepo.existsById(companyId)) {
			 return couponRepo.findByPriceGreaterThan(price);
		 } else {
			 throw new Exception("No company with such ID");
		 }
	}

	public Optional<Company> getCompanyDetails(long companyId) {
		return companyRepo.findById(companyId);
	}
	
	public List<Coupon> getAllCoupons(){
		return couponRepo.findAll();
	}

}
