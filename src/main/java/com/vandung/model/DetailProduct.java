package com.vandung.model;

import java.util.Set;

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
@Table(name = "detailproduct")
public class DetailProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detailproduct", nullable = false)
	private int id_detailproduct;
	
	@Column(name = "color_product")
	private String color_product;
	
	@Column(name = "size_product")
	private String size_product;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="product_detail", joinColumns = {@JoinColumn(name="id_detailproduct", referencedColumnName = "id_detailproduct")}, 
	inverseJoinColumns = {@JoinColumn(name="id_product",referencedColumnName = "id_product")})
	private Set<Product> setProduct;

	public int getId_detailproduct() {
		return id_detailproduct;
	}

	public void setId_detailproduct(int id_detailproduct) {
		this.id_detailproduct = id_detailproduct;
	}

	public String getColor_product() {
		return color_product;
	}

	public void setColor_product(String color_product) {
		this.color_product = color_product;
	}

	public String getSize_product() {
		return size_product;
	}

	public void setSize_product(String size_product) {
		this.size_product = size_product;
	}

	public Set<Product> getSetProduct() {
		return setProduct;
	}

	public void setSetProduct(Set<Product> setProduct) {
		this.setProduct = setProduct;
	}
}
