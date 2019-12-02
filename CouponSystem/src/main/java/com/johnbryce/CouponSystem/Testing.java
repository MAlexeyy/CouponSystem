package com.johnbryce.CouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.service.AdminService;

public class Testing {

	public static void Test(String[] args) {

		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println("@@@@@ GO @@@@@");

		AdminService dao = ctx.getBean(AdminService.class);

		Company c1 = new Company();
		Company c2 = new Company();

		c1.setEmail("c1mail@mail");
		c2.setEmail("c2mail@mail");

		c1.setName("c1name");
		c2.setName("c2name");

		c1.setPassword("c1password");
		c2.setPassword("c2password");

		dao.addCompany(c1);
		dao.addCompany(c2);

		System.out.println(dao.doesCompanyExist("c1mail@mail", "c1password"));
	}

}
