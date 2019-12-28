package com.johnbryce.CouponSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.johnbryce.CouponSystem.Config.DateConfig;
import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.enums.CouponType;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CouponRepo;
import com.johnbryce.CouponSystem.repo.CustomerRepo;

@Service
public class AdminService implements Facade {

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	CouponRepo couponRepo;

	@PostConstruct
	public void populate() {

		List<Company> listc = new ArrayList<>();
		List<Coupon> coupons = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();

		listc.add(new Company("Intel", "intel@mail.com", "intel123"));
		listc.add(new Company("Google", "google@mail.com", "google123"));
		listc.add(new Company("Facebook", "facebook@mail.com", "facebook123"));
		listc.add(new Company("Qualcome", "qualcome@mail.com", "qualcome123"));
		listc.add(new Company("Samsung", "samsung@mail.com", "samsung123"));
		companyRepo.saveAll(listc);

		coupons.add(new Coupon(CouponType.FOOD, "COUPON1 TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 10,
				100, "www.image.com/picture.jpg"));
		coupons.add(new Coupon(CouponType.RESTAURANT, "COUPON2 TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null,
				20, 200, "www.image.com/picture.jpg"));
		coupons.add(new Coupon(CouponType.FOOD, "COUPON3 TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 30,
				300, "www.image.com/picture.jpg"));
		coupons.add(new Coupon(CouponType.RESTAURANT, "COUPON4 TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null,
				40, 400, "www.image.com/picture.jpg"));
		coupons.add(new Coupon(CouponType.ELECTRICITY, "COUPON5 TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(),
				null, 50, 500, "www.image.com/picture.jpg"));
		coupons.add(new Coupon(CouponType.VACATION, "COUPON6 TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null,
				60, 600, "www.image.com/picture.jpg"));
		couponRepo.saveAll(coupons);

		customers.add(new Customer("Shuki", "Shukilast", "shuki@mail.com", "shuki123"));
		customers.add(new Customer("Coockie", "Coockielast", "coockie@mail.com", "coockie123"));
		customers.add(new Customer("mookie", "Mookielast", "mookie@mail.com", "mookie123"));
		customers.add(new Customer("Chookie", "Chookielast", "chookie@mail.com", "chookie123"));
		customers.add(new Customer("Wookie", "Wookielast", "wookie@mail.com", "wookie123"));
		customers.add(new Customer("Zoockie", "Zoockielast", "zoockie@mail.com", "zoockie123"));
		customerRepo.saveAll(customers);

	}

	// Creating,adding new company.
	public Company addCompany(Company company) throws Exception {
		try {
			if (companyRepo.existsCompanyByNameOrEmail(company.getName(), company.getEmail())) {
				throw new Exception("A company with such name or Email already exists.");
			} else {
				companyRepo.save(company);
			}
		} catch (Exception e) {
			throw new Exception("Failed to add a the company: " + e.getMessage());
		}
		return company;
	}

	// Updating company information.
	public Company updateCompany(Company company) throws Exception {
		try {
			if (companyRepo.existsById(company.getId())) {
				Company tmp = companyRepo.findById(company.getId()).get();
				tmp.setEmail(company.getEmail());
				tmp.setPassword(company.getPassword());
				companyRepo.save(tmp);
			} else {
				throw new Exception("No company with such ID exists. ");
			}
		} catch (Exception e) {
			throw new Exception("Failed to update company information: " + e.getMessage());
		}
		return company;
	}

	// Delete company and all it's coupons.
	public boolean deleteCompany(long companyId) throws Exception {
		try {
			if (companyRepo.existsById(companyId)) {
				Company tmp = companyRepo.findById(companyId).get();
				List<Coupon> coupons = tmp.getCoupons();
				for (Coupon c : coupons) {
					couponRepo.deleteById(c.getId());
				}
				companyRepo.deleteById(companyId);
			} else {
				throw new Exception("No company with such ID exists");
			}
		} catch (Exception e) {
			throw new Exception("Failed to delete company: " + e.getMessage());
		}
		return true;
	}

	// Get all companies.
	public List<Company> getAllCompanies() throws Exception {
		try {
			return (List<Company>) companyRepo.findAll();
		} catch (Exception e) {
			throw new Exception("Failed to get all companies: " + e.getMessage());
		}
	}

	// Get one company.
	public Optional<Company> getOneCompany(long companyId) throws Exception {
		try {
			if (companyRepo.existsById(companyId)) {
				return companyRepo.findById(companyId);
			} else {
				throw new Exception("No company with such id exists");
			}
		} catch (Exception e) {
			throw new Exception("Failed to get company information: " + e.getMessage());
		}
	}

	// Add one customer.
	public Customer addCustomer(Customer customer) throws Exception {
		try {
			if (customerRepo.existsCustomerByEmail(customer.getEmail())) {
				throw new Exception("Customer with such Email already exists ");
			} else {
				Customer tempCustomer = customerRepo.findById(customer.getId()).get();
				customerRepo.save(tempCustomer);
			}
		} catch (Exception e) {
			throw new Exception("Failed to add customer: " + e.getMessage());
		}
		return customer;
	}

	// Update customer information.
	public Customer updateCustomer(Customer customer) throws Exception {
		Customer temp = null;
		try {
			if (customerRepo.existsById(customer.getId())) {
				temp = customerRepo.findById(customer.getId()).get();
				customerRepo.save(temp);
			} else {
				throw new Exception("No customer with such ID exists. ");
			}
		} catch (Exception e) {
			throw new Exception("Failed to update customer information: " + e.getMessage());
		}
		return temp;
	}

	// Delete one customer.
	public boolean deleteCustomer(long customerId) throws Exception {
		try {
			if (customerRepo.existsById(customerId)) {
				customerRepo.deleteById(customerId);
			} else {
				throw new Exception("No customer with such ID");
			}
		} catch (Exception e) {
			throw new Exception("Failed to delete customer: " + e.getMessage());
		}
		return true;
	}

	// Get all customers.
	public List<Customer> getAllCustomers() throws Exception {
		try {
			return (List<Customer>) customerRepo.findAll();
		} catch (Exception e) {
			throw new Exception("Failed to get all customers: " + e.getMessage());
		}
	}

	// Get one customer.
	public Customer getOneCustomer(long customerId) throws Exception {
		try {
			if (customerRepo.existsById(customerId)) {
				return customerRepo.findById(customerId).get();
			} else {
				throw new Exception("No customer with such ID");
			}
		} catch (Exception e) {
			throw new Exception("Failed to get the customer: " + e.getMessage());
		}
	}

	public boolean doesCompanyExist(String email, String password) {
		return companyRepo.existsCompanyByEmailAndPassword(email, password);
	}

}
