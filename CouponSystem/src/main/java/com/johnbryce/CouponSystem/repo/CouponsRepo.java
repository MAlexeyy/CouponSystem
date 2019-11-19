package com.johnbryce.CouponSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnbryce.CouponSystem.beans.Coupons;

public interface CouponsRepo extends JpaRepository<Coupons, Integer> {

}
