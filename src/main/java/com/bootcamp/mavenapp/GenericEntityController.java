package com.bootcamp.mavenapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.mavenapp.model.Customer;

@RestController
@RequestMapping("/test")
public class GenericEntityController {
//
//	//customer/angka
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
//	
//	//customer?id=angka
//	@GetMapping("/customer")
//	public Customer customerparam(@RequestParam ("id") String customerNumber) {
//		Customer cs = new Customer();
//		cs.setCustomerNumber(Integer.parseInt(customerNumber));
//		cs.setFirstName("Arif");
//		cs.setLastName("Setyo");
//		cs.setPhoneNumber("087758221567");
//		cs.setPhoneType("Andoid");
//		return cs;
// 	} 
//	
//	//Post Customer
//	@PostMapping("/customer")
//	public String customerpost(@RequestBody Customer customer) {
//		return "First name "+customer.getFirstName()+"\n"+
//				"Last name  "+customer.getLastName()
//				;
//	}
//	
//	//Customer Put Update
////	@PutMapping("/customer")
////	public Customer customerupdate(@RequestParam ("data") String customer) {
////		Customer cs = new Customer();
////		cs.setFirstName(customer);
////		cs.setPhoneNumber("087758221567");
////		return cs;
////	}
//	
//	//Customer Delete
//	@DeleteMapping("/customer/{id}")
//	public String customerdelete(@PathVariable ("id") String customerNumber) {
//		Customer cs = new Customer();
//		//proses delete?
//		cs.setCustomerNumber(Integer.parseInt(customerNumber));
//		
//		return "id = "+customerNumber+ " sudah dihapus";
//	}
//	
//	//Customer GetList
//	@GetMapping("/customers")
//	public List<Customer> getList(){
//		Customer cs = new Customer();
//		cs.setFirstName("Arif");
//		cs.setLastName("Setyo");
//		cs.setPhoneNumber("087758221567");
//		ArrayList<Customer> list = new ArrayList<Customer>();
//		list.add(cs);
//		return list;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
////================ BATAS SUCI ==========================================	
//	//localhost:8080/test/hello/value
//	@GetMapping("/hello")
//	public String hello(@RequestParam("data") String id) {
//		return "Request Param "+id;
//	}
//	
//	//localhost:8080/test/hello?data=value
//	@GetMapping("/hello/{data}")
//	public String helloPathVariable(@PathVariable("data") String id) {
//		return "PathVariabel "+ id;
//	}
//	
//	//localhost:8080/test/helloes
//	@PostMapping("/helloes")
//	public String hello(@RequestBody Customer customer) {
//		return "hello " +customer.getFirstName();
//	}
//	
//	//ujicoba
//	@PostMapping("/coba")
//	public String coba(@RequestBody Customer customer) {
//		return "First name "+customer.getFirstName()+"\n"+
//				"Last name  "+customer.getLastName()
//				;
//	}
//
//	
//	
//	@GetMapping("/customere")
//	public Customer update(@RequestBody Customer customer) {
//		customer.setCustomerNumber(119);	
//		return customer;
//	}
	
}
