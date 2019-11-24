package com.johnbryce.CouponSystem.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.repo.CompanyRepo;

@Service
public class CompanyService {
	@Autowired
	CompanyRepo repo;
	
	//TODO 
	public boolean isCompanyExists(String email,String password) {
		return false;
	}

	public void addCompany(Company p) {
		repo.save(p);
	}
	
	//TODO
	public void updateCompany(Company p) {
		repo.save(p);
	}
	
	
	public void deleteCompany(Long companyId) {
		repo.deleteById(companyId);
	}
	
	public List<Company> getAllCompanies(){
		return (List<Company>) repo.findAll();
	}
	
	public Optional<Company> getOneCompany(Long companyId) {
		return repo.findById(companyId);
	}

}
