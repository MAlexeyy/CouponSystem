package com.johnbryce.CouponSystem.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Long> {

	public boolean existsCompanyByEmailAndPassword(String email, String password);

	public boolean existsCompanyByNameOrEmail(String name, String email);
//	public void addCompany(Company company);
//
//	public void updateCompany(Company company);
//
//	public void deleteById(int id);
//
//	public ArrayList<Company> getAllCompanies();
//
//	public Company getOneCompany(int id);
//
//	public int getCompanyId(String email);
//
//	public ArrayList<Coupon> getOneCompanyCoupons(int id);

}
