package com.johnbryce.CouponSystem.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.johnbryce.CouponSystem.enums.CouponType;

@Entity
@Table
public class Coupon {
	private long id;
	//private Company company;
	private CouponType category;
	private String title;
	private String description;
	private Date start_date;
	private Date end_date;
	private int amount;
	private double price;
	private String image;

	private List<Customer> customers;

	public Coupon() {
	}

	public Coupon(CouponType category, String title, String description, Date start_date, Date end_date, int amount,
			double price, String image) {
		super();
		this.category = category;
		this.title = title;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	@ManyToOne
//	@JoinColumn(name = "company_id")
	
//	public Company getCompany() {
//		return company;
//	}
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}

	@Column
	@Enumerated(EnumType.STRING)
	public CouponType getCategory() {
		return category;
	}

	public void setCategory(CouponType category) {
		this.category = category;
	}

	@Column
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	@Column
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	@Column
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "customers_vs_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"), foreignKey = @ForeignKey(name = "FK_COUPON_ID"), inverseForeignKey = @ForeignKey(name = "FK_CUSTOMER_ID"))
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

//	@Override
//	public String toString() {
//		return "Coupons [id=" + id + ", company=" + company + ", category=" + category + ", title=" + title
//				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date + ", amount="
//				+ amount + ", price=" + price + ", image=" + image + "]";
//	}

}
