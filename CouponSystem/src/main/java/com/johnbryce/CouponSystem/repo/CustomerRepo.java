package com.johnbryce.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnbryce.CouponSystem.beans.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	public boolean existsCustomerByEmail(String email);

	public Customer findCustomerByEmailAndPassword(String email, String password);
	
	
	
	
}
