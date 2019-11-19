package com.johnbryce.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem.beans.Customers;

public interface CustomersRepo extends JpaRepository<Customers, Integer> {

}
