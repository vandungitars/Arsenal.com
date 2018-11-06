package com.vandung.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.model.DetailProduct;
import com.vandung.model.Product;
import com.vandung.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/arsenal/product", method = RequestMethod.GET)
	public String defaultPage(ModelMap modelMap) {
		List<Product> listProduct = productService.findAll();
		listProduct.forEach((n)->{
			n.setImage_product("/img/core-img/" + n.getImage_product());
		});
		modelMap.addAttribute("listProduct", listProduct);
		return "productPage";
	}
	
	@RequestMapping(value = "/arsenal/product/{idProduct}", method = RequestMethod.GET)
	public String detailProduct(@PathVariable String idProduct,ModelMap modelMap) {
		Long id_product = Long.parseLong(idProduct);
		Product productDB = productService.findByID(id_product);
		Product product = productDB;
		if(!product.getImage_product().contains("/img/core-img/")) {
			product.setImage_product("/img/core-img/" + product.getImage_product());
		}
		List<DetailProduct> listDetailProduct = product.getListDetailProduct();
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("listDetailProduct", listDetailProduct);
		return "detailProductPage";
	}
	
	@RequestMapping(value = "/admin/Product", method = RequestMethod.GET)
	public String viewAdminProduct(ModelMap modelMap) {
		List<DetailProduct> listDetailProduct = productService.getListDetailProduct();
		listDetailProduct.sort((p1,p2)->{
			return (p1.getColor_product().compareTo(p2.getColor_product()));
		});
		modelMap.addAttribute("listDetailProduct", listDetailProduct);
		return "adminAddProduct";
	}
	
	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.POST)
	public String addProduct(@RequestParam String dataJson, @RequestParam String optionText) {
		optionText = optionText.trim();
		optionText = optionText.replace("\n", " ");
		optionText = optionText.replace("\t", " ");
		while(optionText.contains("  ")) {
			optionText = optionText.replace("  ", " ");
		}
		String[] arrOptionText = optionText.split(" ");
		String color_product = arrOptionText[0];
		String size_product = arrOptionText[1];
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = new Product();
		List<DetailProduct> listDetailProductDB = productService.getListDetailProduct();
		List<DetailProduct> listDetailProduct = new ArrayList<>();
		for(int i=0; i<listDetailProductDB.size(); i++) {
			if(listDetailProductDB.get(i).getColor_product().equals(color_product) && listDetailProductDB.get(i).getSize_product().equals(size_product)) {
				listDetailProduct.add(listDetailProductDB.get(i));
			}
		};
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String name_product = jsonNode.get("name_product").asText();
			int price_product = jsonNode.get("price_product").asInt();
			String season = jsonNode.get("season").asText();
			String image_product = ApiController.nameFile;
			if((name_product != "") && (price_product != 0) && (season != "") && (image_product != "")) {
				product.setName_product(name_product);
				product.setPrice_product(price_product);
				product.setSeason(season);
				product.setImage_product(image_product);
				product.setListDetailProduct(listDetailProduct);
				productService.save(product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminAddProduct";
	}
	
	@RequestMapping(value = "/admin/getListProduct", method = RequestMethod.GET)
	public String getListProduct(ModelMap modelMap) {
		List<Product> listProduct = productService.findAll();
		listProduct.forEach((n)->{
			if(!n.getImage_product().contains("/img/core-img/")) {
				n.setImage_product("/img/core-img/" + n.getImage_product());
			}
		});
		List<DetailProduct> listDetailProduct = productService.getListDetailProduct();
		listDetailProduct.sort((p1,p2)->{
			return (p1.getColor_product().compareTo(p2.getColor_product()));
		});
		modelMap.addAttribute("listDetailProduct", listDetailProduct);
		modelMap.addAttribute("listProduct", listProduct);
		return "adminUpdateProduct";
	}
	
	@RequestMapping(value = "/admin/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@RequestParam String dataJson, @RequestParam String idProduct, @RequestParam String optionText) {
		Long id_product = Long.parseLong(idProduct);
		optionText = optionText.trim();
		optionText = optionText.replace("\n", " ");
		optionText = optionText.replace("\t", " ");
		while(optionText.contains("  ")) {
			optionText = optionText.replace("  ", " ");
		}
		String[] arrOptionText = optionText.split(" ");
		String color_product = arrOptionText[0];
		String size_product = arrOptionText[1];
		List<DetailProduct> listDetailProductDB = productService.getListDetailProduct();
		List<DetailProduct> listDetailProduct = new ArrayList<>();
		for(int i=0; i<listDetailProductDB.size(); i++) {
			if(listDetailProductDB.get(i).getColor_product().equals(color_product) && listDetailProductDB.get(i).getSize_product().equals(size_product)) {
				listDetailProduct.add(listDetailProductDB.get(i));
			}
		};
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = new Product();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String name_product = jsonNode.get("name_productChange").asText();
			int price_product = jsonNode.get("price_productChange").asInt();
			String season = jsonNode.get("season_productChange").asText();
			if((name_product != "") && (price_product != 0) && (season != "")) {
				product.setName_product(name_product);
				product.setPrice_product(price_product);
				product.setSeason(season);
				product.setListDetailProduct(listDetailProduct);
				productService.update(product, id_product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "adminUpdateDatabase";
	}
	
	@RequestMapping(value = "/admin/deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam String idProduct) {
		Long id_product = Long.parseLong(idProduct);
		productService.deleteById(id_product);
		return "adminUpdateProduct";
	}
}
