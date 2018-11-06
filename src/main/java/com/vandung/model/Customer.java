package com.vandung.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_customer", nullable = false)
	private Long id_customer;
	
	@Column(name = "name_customer")
	private String name_customer;
	
	@Column(name = "phone_customer")
	private int phone_customer;
	
	@Column(name = "address_customer")
	private String address_customer;

	@Column(name = "email_customer")
	private String email_customer;
	
	@Column(name = "count")
	private int count;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="customer_product", joinColumns = {@JoinColumn(name="id_customer", referencedColumnName = "id_customer")}, 
	inverseJoinColumns = {@JoinColumn(name="id_product",referencedColumnName = "id_product")})
	private List<Product> listProduct;

	public Long getId_customer() {
		return id_customer;
	}

	public void setId_customer(Long id_customer) {
		this.id_customer = id_customer;
	}

	public String getName_customer() {
		return name_customer;
	}

	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}

	public int getPhone_customer() {
		return phone_customer;
	}

	public void setPhone_customer(int phone_customer) {
		this.phone_customer = phone_customer;
	}

	public String getAddress_customer() {
		return address_customer;
	}

	public void setAddress_customer(String address_customer) {
		this.address_customer = address_customer;
	}

	public List<Product> getlistProduct() {
		return listProduct;
	}

	public void setlistProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public String getEmail_customer() {
		return email_customer;
	}

	public void setEmail_customer(String email_customer) {
		this.email_customer = email_customer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

