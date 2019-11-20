package com.johnbryce.CouponSystem.repo;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;


@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
	public boolean existsByEmailAndPassword(String email,String password);

	public void addCompany(Company company);

	public void updateCompany(Company company);

	public void deleteById(int id);

	public ArrayList<Company> getAllCompanies();

	public Company getOneCompany(int id);

	public int getCompanyId(String email);

	public ArrayList<Coupon> getOneCompanyCoupons(int id);
}
