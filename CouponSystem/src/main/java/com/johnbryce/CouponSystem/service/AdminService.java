package com.johnbryce.CouponSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		//System.out.println(customerRepo.findAll());
		//System.out.println(customerRepo.fi);

	}

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
//		try {
		return companyRepo.findById(companyId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	public Customer addCustomer(Customer customer) throws Exception {
		if (customerRepo.existsCustomerByEmail(customer.getEmail())) {
			throw new Exception("Customer with such Email already exists ");
		} else {
//			Customer tempCustomer = null;
//			tempCustomer = customerRepo.findById(customer.getId()).get();
//			customerRepo.save(tempCustomer);
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
		return (List<Customer>) customerRepo.findAll();
	}

	public Customer getOneCustomer(long customerId) throws Exception {
		try {
			Customer customer;
			customer = customerRepo.findById(customerId).get();
			return customer;
		} catch (Exception e) {
			throw new Exception("No customer with such ID exist ");
		}
	}

	public boolean doesCompanyExist(String email, String password) {
		return companyRepo.existsCompanyByEmailAndPassword(email, password);
	}

}
