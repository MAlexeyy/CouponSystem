package com.johnbryce.CouponSystem;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.johnbryce.CouponSystem.Date.DateConfig;
import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Coupon;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.enums.CouponType;
import com.johnbryce.CouponSystem.service.AdminService;
import com.johnbryce.CouponSystem.service.CompanyService;
import com.johnbryce.CouponSystem.service.CustomerService;

public class Testing {

	public static void Test(String[] args) {

		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println("@@@@@ GO @@@@");

		// -------------------Services--------------------------------------------------------------------------------------------------------------------------
		AdminService adminService = ctx.getBean(AdminService.class);
		CompanyService companyService = ctx.getBean(CompanyService.class);
		CustomerService customerService = ctx.getBean(CustomerService.class);
		
		// Poppulating Company table with data.-----------------------------------------------------------------------------------------------------------------
		
		Company c1 = new Company("Intel", "intel@mail.com", "intel123");
		Company c2 = new Company("Google", "google@mail.com", "google123");
		Company c3 = new Company("Facebook", "facebook@mail.com", "facebook123");
		Company c4 = new Company("Qualcome", "qualcome@mail.com", "qualcome123");
		Company c5 = new Company("Samsung", "samsung@mail.com", "samsung123");
		
		try {
		adminService.addCompany(c1);
		adminService.addCompany(c2);
		adminService.addCompany(c3);
		adminService.addCompany(c4);
		adminService.addCompany(c5);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Adding coupons----------------------------------------------------------------------------------------------------------------------------------------
		
		Coupon coupon1 = new Coupon(4, CouponType.FOOD, "COUPON TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 10, 100, "www.image.com/picture.jpg");
		Coupon coupon2 = new Coupon(2, CouponType.RESTAURANT, "COUPON TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 20, 200, "www.image.com/picture.jpg");
		Coupon coupon3 = new Coupon(1, CouponType.FOOD, "COUPON TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 30, 300, "www.image.com/picture.jpg");
		Coupon coupon4 = new Coupon(5, CouponType.RESTAURANT, "COUPON TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 40, 400, "www.image.com/picture.jpg");
		Coupon coupon5 = new Coupon(2, CouponType.ELECTRICITY, "COUPON TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 50, 500, "www.image.com/picture.jpg");
		Coupon coupon6 = new Coupon(3, CouponType.VACATION, "COUPON TITLE", "COUPON DESCRIPTION", DateConfig.dateNow(), null, 60, 600, "www.image.com/picture.jpg");
		
		companyService.addCoupon(coupon1);
		companyService.addCoupon(coupon2);
		companyService.addCoupon(coupon3);
		companyService.addCoupon(coupon4);
		companyService.addCoupon(coupon5);
		companyService.addCoupon(coupon6);
		
		//Adding customers---------------------------------------------------------------------------------------------------------------------------------------
		
		Customer customer1 = new Customer("Shuki" , "Shukilast" , "shuki@mail.com" , "shuki123");
		Customer customer2 = new Customer("Coockie" , "Coockielast" , "coockie@mail.com" , "coockie123");
		Customer customer3 = new Customer("mookie" , "Mookielast" , "mookie@mail.com" , "mookie123");
		Customer customer4 = new Customer("Chookie" , "Chookielast" , "chookie@mail.com" , "chookie123");
		Customer customer5 = new Customer("Wookie" , "Wookielast" , "wookie@mail.com" , "wookie123");
		Customer customer6 = new Customer("Zoockie" , "Zoockielast" , "zoockie@mail.com" , "zoockie123");
		
		try {
			adminService.addCustomer(customer1);
			adminService.addCustomer(customer2);
			adminService.addCustomer(customer3);
			adminService.addCustomer(customer4);
			adminService.addCustomer(customer5);
			adminService.addCustomer(customer6);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println(c2);
		
		Company cc = new Company("Google", "google@mail.com!!!", "google123!!!");
		cc.setId(2);
		try {
			adminService.updateCompany(cc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(adminService.getOneCompany(2).toString());		
		
	}
	
	

}
