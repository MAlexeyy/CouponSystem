package com.johnbryce.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem.beans.Companies;

public interface CompaniesRepo extends JpaRepository<Companies, Integer>{

}
