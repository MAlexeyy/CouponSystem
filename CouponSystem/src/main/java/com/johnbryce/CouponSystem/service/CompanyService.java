package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.Config.DateConfig;
import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.enums.CouponType;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CouponRepo;

import ch.qos.logback.core.subst.Token.Type;

@Service
public class CompanyService implements Facade {

	@Autowired
	CouponRepo couponRepo;

	@Autowired
	CompanyRepo companyRepo;

	long companyId;

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	

	// Add new coupon.
	public Company addCoupon(Coupon coupon) throws Exception {
		if (!companyRepo.existsById(this.companyId)) {
			throw new Exception("There is no company with such ID");
		} else {
			Company tempCompany = companyRepo.findById(this.companyId).get();
			for (Coupon c : tempCompany.getCoupons()) {
				if (c.getTitle().equals(coupon.getTitle())) {
					throw new Exception("Coupon with such title already exists in this company");
				}
			}
			coupon.setStart_date(DateConfig.dateNow());
			coupon.setCompany(tempCompany);
			tempCompany.getCoupons().add(coupon);
//			companyRepo.save(tempCompany);
			couponRepo.save(coupon);
			return tempCompany;
		}
	}

	// Update existing coupon.
	public String updateCoupon(Coupon coupon) throws Exception {
		Company tmpCompany = companyRepo.findById(companyId).get();		
		try {
			if (couponRepo.existsCouponByIdAndCompany_Id(coupon.getId(),companyId)) {
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
				return "Coupon was updated.";
			} else {
				throw new Exception("No coupon with such id to update");
			}
		} catch (Exception e) {		
			throw new Exception("Failed to update coupon");
		}
	}

	// Delete coupon.
	public String deleteCoupon(long couponId) throws Exception {
		Company tmpCompany = companyRepo.findById(companyId).get();	
		Coupon tmpCoupon = couponRepo.findById(couponId).get();
		try {
			if(tmpCompany.getCoupons().contains(tmpCoupon)) {
				couponRepo.delete(tmpCoupon);
				return "Great success, coupon deleted.";
			} else {
				throw new Exception("Your company has no cuch coupon.");
			}
		} catch (Exception e) {
			throw new Exception("Failed to delete coupon");
		}
	}

	// Get all company coupons.
	public List<Coupon> getCompanyCoupons() throws Exception {
		if (companyRepo.existsById(this.companyId)) {
			Company tmpCompany = companyRepo.findById(this.companyId).get();
			return tmpCompany.getCoupons();
		} else {
			throw new Exception("No company with such ID");
		}
	}

	// Get company coupons from specific category.
	public List<Coupon> getCompanyCouponsByCategory(String type) throws Exception {
		List<Coupon> tmpCoupons = null;
		CouponType couponType = CouponType.valueOf(type);
		if (companyRepo.existsById(this.companyId)) {
			Company tmpCompany = companyRepo.findById(this.companyId).get();
			for (Coupon c : tmpCompany.getCoupons()) {
				if (c.getCategory() == couponType) {
					tmpCoupons.add(c);
				}
			}
		} else {
			throw new Exception("No company with such ID");
		}
		if (tmpCoupons.isEmpty()) {
			throw new Exception("This company has no coupons with such category");
		} else {
			return tmpCoupons;
		}
	}

	// Get company coupons with a max price.
	public List<Coupon> getCompanyCouponsByMaxPrice(double price) throws Exception {
		List<Coupon> tmpCoupons = null;
		if (companyRepo.existsById(this.companyId)) {
			Company tmpCompany = companyRepo.findById(this.companyId).get();
			for (Coupon c : tmpCompany.getCoupons()) {
				if (c.getPrice() <= price) {
					tmpCoupons.add(c);
				}
			}
		} else {
			throw new Exception("No company with such ID");
		}
		if (tmpCoupons.isEmpty()) {
			throw new Exception("This company has no coupons under searched price.");
		} else {
			return tmpCoupons;
		}
	}

	// Get company information.
	public Optional<Company> getCompanyDetails() {
		return companyRepo.findById(this.companyId);
	}

	public List<Coupon> getAllCompanyCoupons() {
		Company tmpCompany = companyRepo.findById(companyId).get();
		return tmpCompany.getCoupons();
	}

}
