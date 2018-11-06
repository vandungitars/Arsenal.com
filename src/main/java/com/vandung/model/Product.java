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
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product", nullable = false)
	private Long id_product;
	
	@Column(name = "name_product")
	private String name_product;
	
	@Column(name = "season")
	private String season;
	
	@Column(name = "price_product")
	private int price_product;
	
	@Column(name = "image_product")
	private String image_product;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="product_detail", joinColumns = {@JoinColumn(name="id_product", referencedColumnName = "id_product")}, 
	inverseJoinColumns = {@JoinColumn(name="id_detailproduct",referencedColumnName = "id_detailproduct")})
	private List<DetailProduct> listDetailProduct;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="customer_product", joinColumns = {@JoinColumn(name="id_product", referencedColumnName = "id_product")}, 
	inverseJoinColumns = {@JoinColumn(name="id_customer",referencedColumnName = "id_customer")})
	private List<Customer> listCustomer;

	public Long getId_product() {
		return id_product;
	}

	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}

	public String getName_product() {
		return name_product;
	}

	public void setName_product(String name_product) {
		this.name_product = name_product;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public int getPrice_product() {
		return price_product;
	}

	public void setPrice_product(int price_product) {
		this.price_product = price_product;
	}

	public String getImage_product() {
		return image_product;
	}

	public void setImage_product(String image_product) {
		this.image_product = image_product;
	}

	public List<DetailProduct> getListDetailProduct() {
		return listDetailProduct;
	}

	public void setListDetailProduct(List<DetailProduct> listDetailProduct) {
		this.listDetailProduct = listDetailProduct;
	}

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
}
