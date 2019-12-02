package com.johnbryce.CouponSystem.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CouponRepo;
import com.johnbryce.CouponSystem.repo.CustomerRepo;

@Service
public class AdminService {

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	CouponRepo couponRepo;

	public boolean addCompany(Company company) {
		try {
			companyRepo.save(company);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	public boolean updateCompany(Company company) {
//		Optional<Company> tmp = null;
//		if(companyRepo.existsById(company.getId())) {
//			tmp.get();  //ASK KOBI
//			tmp = companyRepo.findById(company.getId());
//			
//		}
//	}

	public boolean deleteCompany(Long companyId) {
		try {
			companyRepo.deleteById(companyId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Company> getAllCompanies() {
		return (List<Company>) companyRepo.findAll();
	}

	public Optional<Company> getOneCompany(Long companyId) {
		return companyRepo.findById(companyId);
	}

	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}

//	public void updateCustomer(Customer customer) {
//		
//	}

	public void deleteCustomer(Long customerId) {
		customerRepo.findById(customerId);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Optional<Customer> getOneCustomer(Long customerId) {
		return customerRepo.findById(customerId);
	}

	public boolean doesCompanyExist(String email, String password) {
		return companyRepo.existsCompanyByEmailAndPassword(email, password);
	}

}
