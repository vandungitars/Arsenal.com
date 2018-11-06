package com.vandung.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.model.DetailProduct;
import com.vandung.model.Product;
import com.vandung.repository.ProductRepository;

@Service("ProductService")
@Transactional
public class ProductServiceImp implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EntityManager entityManager; 

	@Override
	public Product findByID(Long id_product) {
		return productRepository.getOne(id_product);
	}

	@Override
	public List<Product> findAll() {
		List<Product> listProduct = productRepository.findAll();
		listProduct.sort((p1,p2)->{
			return p1.getPrice_product()-p2.getPrice_product();
		});
		return listProduct;
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void update(Product product, Long id_product) {
		Product productDB = productRepository.getOne(id_product);
		productDB.setName_product(product.getName_product());
		productDB.setPrice_product(product.getPrice_product());
		productDB.setSeason(product.getSeason());
		productDB.setListDetailProduct(product.getListDetailProduct());
		productRepository.save(productDB);
	}

	@Override
	public void deleteById(Long id_product) {
		productRepository.deleteById(id_product);
	}

	@Override
	public List<DetailProduct> getListDetailProduct() {
		TypedQuery<DetailProduct> query = entityManager.createQuery("FROM DetailProduct", DetailProduct.class);		
		return query.getResultList();
	}	
}
