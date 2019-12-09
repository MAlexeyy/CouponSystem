package com.johnbryce.CouponSystem.service;

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

	public Company addCompany(Company company) throws Exception {
		if (companyRepo.existsCompanyByNameOrEmail(company.getName(), company.getEmail())) {
			throw new Exception("A company with the same Email or Password already exists");
		} else {
			companyRepo.save(company);
		}
		return company;
	}

	public Company updateCompany(Company company) throws Exception {
		if (companyRepo.existsById(company.getId())) {
			Company tmp = companyRepo.findById(company.getId()).get();
			tmp.setEmail(company.getEmail());
			tmp.setPassword(company.getPassword());
			companyRepo.save(tmp);
		} else {
			throw new Exception("No company with such ID exists. ");
		}
		return company;
	}

	// TODO also make sure all the coupons are deleted.
	public boolean deleteCompany(long companyId) throws Exception {
		try {
			companyRepo.deleteById(companyId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Company> getAllCompanies() {
		try {
			return (List<Company>) companyRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Optional<Company> getOneCompany(long companyId) {
		try {
			return companyRepo.findById(companyId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer addCustomer(Customer customer) throws Exception {
		if (customerRepo.existsCustomerByEmail(customer.getEmail())) {
			throw new Exception("Customer with such Email already exists ");
		} else {
			customerRepo.save(customer);
		}
		return customer;
	}

	// TODO check for fix.
	public Customer updateCustomer(Customer customer) throws Exception {
		Customer temp = null;
		if (customerRepo.existsById(customer.getId())) {
			temp = customerRepo.findById(customer.getId()).get();
			temp.setFirst_name(customer.getFirst_name());
			temp.setLast_name(customer.getLast_name());
			temp.setPassword(customer.getPassword());
			temp.setEmail(customer.getEmail());
			customerRepo.save(temp);
			return temp;
		} else {
			throw new Exception("No customer with such ID exists. ");
			// return temp; ask why wount work.
		}
	}

	public boolean deleteCustomer(long customerId) throws Exception {
		try {
			customerRepo.deleteById(customerId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Optional<Customer> getOneCustomer(long customerId)throws Exception {
		try {
			return customerRepo.findById(customerId);
		} catch (Exception e) {
			throw new Exception("No customer with such ID exist ");
		}
	}

	public boolean doesCompanyExist(String email, String password) {
		return companyRepo.existsCompanyByEmailAndPassword(email, password);
	}

}
