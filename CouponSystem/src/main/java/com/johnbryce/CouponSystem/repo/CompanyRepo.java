package com.johnbryce.CouponSystem.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Company;

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

	public Company findCompanyByEmailAndPassword(String name, String password);

}
