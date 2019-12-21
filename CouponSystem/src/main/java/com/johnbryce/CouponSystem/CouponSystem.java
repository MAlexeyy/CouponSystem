package com.johnbryce.CouponSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.johnbryce.CouponSystem.beans.Company;
import com.johnbryce.CouponSystem.beans.Customer;
import com.johnbryce.CouponSystem.enums.ClientType;
import com.johnbryce.CouponSystem.repo.CompanyRepo;
import com.johnbryce.CouponSystem.repo.CustomerRepo;
import com.johnbryce.CouponSystem.service.AdminService;
import com.johnbryce.CouponSystem.service.CompanyService;
import com.johnbryce.CouponSystem.service.CustomerService;
import com.johnbryce.CouponSystem.service.Facade;

@Service
public class CouponSystem {
 
    @Autowired
    private ApplicationContext ctx;
     
    @Autowired
    private AdminService adminService;
     
    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private CompanyRepo companyRepo;
     
//    @Autowired
//    private CouponCleanerDailyTask task;
//    
//    @Autowired
//    private SessionTimeoutHandler sessionTask;
     
//    @PostConstruct
//    public void init() {
//        task.start();
//        sessionTask.start();
//    }
//     
//    @PreDestroy
//    public void destroy() {
//        task.stop();
//        sessionTask.stop();
//    }
     
    public Facade login(String email, String password, ClientType type) throws Exception { //throws CouponSystemException
		switch (type) {
		case ADMIN:
			if (email.equals("admin") && password.equals("1234")) {
				System.out.println("Welcome Admin! You're logged in system");
				return adminService;
			}
		case COMPANY:
			Company company = companyRepo.findCompanyByEmailAndPassword(email, password);
			if (company != null) {
				CompanyService companyService = ctx.getBean(CompanyService.class);
				companyService.setCompanyId(company.getId());
				System.out.println("Welcome " +company.getName()+ " company! You're logged in system");
				return companyService;
			}
		case CUSTOMER:
			Customer customer = customerRepo.findCustomerByEmailAndPassword(email, password);
			if (customer != null) {
				CustomerService customerService = ctx.getBean(CustomerService.class);
				customerService.setCustomerId(customer.getId());
				System.out.println("Welcome customer " +customer.getFirst_name()+ "! You're logged in system");
				return customerService;
			}
		}
		throw new Exception("Client not found. Check your data");//throw new CouponSystemException
	}
}
