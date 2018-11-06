package com.vandung.service;

import java.util.List;
import com.vandung.model.DetailProduct;
import com.vandung.model.Product;

public interface ProductService {

	Product findByID(Long id_product);
	List<Product> findAll();
	void save(Product product);
	void update(Product product, Long id_product);
	void deleteById(Long id_product);
	List<DetailProduct> getListDetailProduct();
}
