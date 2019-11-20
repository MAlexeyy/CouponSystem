package com.johnbryce.CouponSystem.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	public boolean existsByEmailAndPassword(String email, String password);

	public void addCustomer(Customer customer);

	public boolean updateCustomer(Customer customer);

	public void deleteCustomerById(int id);

	public ArrayList<Customer> getAllCustomers();

	public Customer getOneCustomer(int id);

	public int getCustomerId(String email);

	public ArrayList<Coupon> getOneCustomerCoupons(int id);
}
