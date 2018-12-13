package com.bootcamp.mavenapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.mavenapp.dao.CustomerDao;
import com.bootcamp.mavenapp.model.Customer;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private CustomerDao customerDao;
	
	//Start Get customer?id=nomer
	@GetMapping("/customer")
	public String hello(@RequestParam(value="id", defaultValue="") String id) {
		try {
			Customer customer = customerDao.getById(Integer.valueOf(id));
			if (customer == null) {
				return "data tidak ditemukan";
			} else {
				return "hello "+customer.getFirstName();
			}
		} catch (NumberFormatException e) {
			return "salah format input";
		} catch (Exception e) {
			return String.format("terjadi kesalahan system : %s", e.getMessage());
		}
	}
	//End get Customer
//======================================================================================
	//customer/angka
//	@GetMapping("/customer/{id}")
//	public Customer customerpath(@PathVariable ("id") String customerNumber) {
//		Customer cs = new Customer();
//		cs.setCustomerNumber(Integer.parseInt(customerNumber));
//		cs.setFirstName("Arif");
//		cs.setLastName("Setyo A");
//		cs.setPhoneNumber("087758221567");
//		cs.setPhoneType("Andoid");
//		return cs;
//	}
	
//======================================================================================	
	//Start  /post Save-Create --!> 
	/* Noted : input di postman JSON harus lengkap sesuai dg database, 
	 * mulai dari : firstName, lastName, birthData, username, password, phoneNumber, phoneType
	 */	
	@PostMapping("/customer")
	public Customer postCustomer(@RequestBody Customer customer) throws Exception {
		Customer data = customerDao.save(customer);
		return data;
	}
	//End /post Save-Create
	
	//List CustomerS
	@GetMapping("/customers")
	public List<Customer> getList() throws Exception{
		List<Customer> list = customerDao.getList();
		return list;
	}
	//End Get List Customers
	
	//Update Customer
	@PutMapping("/customer")
	public Customer customerupdate(@RequestBody Customer customer) throws Exception {
		Customer update = customerDao.save(customer);
		return update;
	}
	//EndUpdateCustomer
	
	//Delete
	@DeleteMapping("/customer/{id}")
	public void customerdelete(@PathVariable ("id") Customer data) throws Exception {
		customerDao.delete(data);
	}
	//End Delete

}
