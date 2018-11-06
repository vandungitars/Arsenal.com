package com.vandung.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.model.Admin;
import com.vandung.model.Comment;
import com.vandung.model.CrunchifyFileUpload;
import com.vandung.model.Customer;
import com.vandung.model.Practise;
import com.vandung.model.Product;
import com.vandung.service.AdminAccountService;
import com.vandung.service.CustomerService;
import com.vandung.service.NewsService;
import com.vandung.service.PractiseService;
import com.vandung.service.ProductService;

@RestController
public class ApiController{

	@Autowired
	private AdminAccountService adminService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private PractiseService practiseService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	public static String nameFile;
	
	@RequestMapping(value = "/api/loginAdmin", method = RequestMethod.GET)
	public String loginAdmin(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String username = jsonNode.get("username").asText();
			String password = jsonNode.get("password").asText();
			Iterable<Admin> iterableAdmin = adminService.findAll();
			iterableAdmin.forEach((admin) ->{
				if(admin.getUsername().equals(username)) {
					if(admin.getPassword().equals(password)) {
						KeyApi.statusLogin = "true";
					}
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return KeyApi.statusLogin;
	}
	
	@RequestMapping(value = "/api/registerAdmin", method = RequestMethod.POST)
	public String registerAdmin(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Admin admin = new Admin();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String username = jsonNode.get("username").asText();
			String password = jsonNode.get("password").asText();
			String repeatPassword = jsonNode.get("repeat-password").asText();
			if(password.equals(repeatPassword)) {
				admin.setUsername(username);
				admin.setPassword(password);
				adminService.save(admin);
				KeyApi.statusRegister = "true";
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return KeyApi.statusRegister;
	}
		
	@RequestMapping(value = "/api/uploadfile", method = RequestMethod.POST)
	public void UploadFile(MultipartHttpServletRequest request)
	{
		String path_save_file = "F:\\Workspace\\com.vandung.arsenal\\src\\main\\resources\\static\\img\\core-img\\";
		Iterator<String> listNames = request.getFileNames();
		MultipartFile mpf = request.getFile(listNames.next());
		File file_save = new File(path_save_file + mpf.getOriginalFilename());
		nameFile = mpf.getOriginalFilename();
		try {
			mpf.transferTo(file_save);
		} catch (IllegalStateException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/uploadMultifile", method = RequestMethod.POST)
    public void crunchifySave(@ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm){
        String saveDirectory = "F:\\Workspace\\com.vandung.arsenal\\src\\main\\resources\\static\\img\\core-img\\";
        List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
        List<String> listFileName = new ArrayList<String>(); 
        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
            for (MultipartFile multipartFile : crunchifyFiles) { 
            	try {
            		String fileName = multipartFile.getOriginalFilename();
            		if (!"".equalsIgnoreCase(fileName)) {
                        multipartFile.transferTo(new File(saveDirectory + fileName));
                        listFileName.add(fileName);
                    }
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
            }
        }
        StringBuilder fileNamesST = new StringBuilder();
        listFileName.forEach((p)->{
        	fileNamesST.append(p.toString()+",");
        });
        String nameFiles = new String(fileNamesST);
        Practise practise = new Practise();
        practise.setImage(nameFiles);
        practiseService.save(practise);
    }
	
	@RequestMapping(value = "/api/comment", method = RequestMethod.POST)
	public void saveComment(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Comment comment = new Comment();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String email = jsonNode.get("email_comment").asText();
			String content = jsonNode.get("content_comment").asText();
			if(email != "" && content != "") {
				comment.setEmail_comment(email);
				comment.setContent_comment(content);
				newsService.saveComment(comment);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/customer", method = RequestMethod.POST)
	public void saveCustomer(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Customer customer = new Customer();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			String name = jsonNode.get("name_customer").asText();
			int phone = jsonNode.get("phone_customer").asInt();
			String email = jsonNode.get("email_customer").asText();
			String address = jsonNode.get("address_customer").asText();
			int count = jsonNode.get("count").asInt();
			Long id_product = jsonNode.get("id_product").asLong();
			Product product = productService.findByID(id_product);
			List<Product> listProduct = new ArrayList<>();
			listProduct.add(product);
			if(name != "" && phone != 0 && email != "" && address != "" && count != 0) {
				customer.setName_customer(name);
				customer.setPhone_customer(phone);
				customer.setEmail_customer(email);
				customer.setCount(count);
				customer.setlistProduct(listProduct);
				customerService.saveCustomer(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
    public static String changeContent(String content) {
		return content.replace("\n", "</br>");
	}
}
