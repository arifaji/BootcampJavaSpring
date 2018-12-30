package com.bootcamp.mavenapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Customer;
import com.bootcamp.mavenapp.model.dto.CommonResponse;
import com.bootcamp.mavenapp.model.dto.CustomerDto;

@RestController
@RequestMapping("")
@SuppressWarnings("rawtypes")
public class ControllerCustomer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerCustomer.class);
	
	@Autowired
	private ModelMapper modelMapper;

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
	@GetMapping("/customer/{customerNumber}")
	public CommonResponse getById(@PathVariable("customerNumber") String customerNumber) throws UniversalException {
		LOGGER.info("customerNumber : {}", customerNumber);
		try {
				Customer customer = customerDao.getById(Integer.parseInt(customerNumber));
				
				return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
			} catch (UniversalException e) {
				LOGGER.error(e.getMessage());
				return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
//======================================================================================	
	//Start  /post Save-Create --!> 
	/* Noted : input di postman JSON harus lengkap sesuai dg database, 
	 * mulai dari : firstName, lastName, birthData, username, password, phoneNumber, phoneType
	 */	
	@PostMapping("/customer")
	public CommonResponse insert(@RequestBody CustomerDto customerDto) throws UniversalException {
		try {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customer.setCustomerNumber(0);
			customer =  customerDao.save(customer);

			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
			
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	//End /post Save-Create
	
	//List CustomerS
	@GetMapping("/customers")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer) throws UniversalException{
		try {
			LOGGER.info("customer get list, params : {}", customer);
		
			return new CommonResponse<List<Customer>>(customerDao.getList());
		} catch (UniversalException e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	//End Get List Customers
	
	//Update Customer
	@PutMapping("/customer")
	public CommonResponse update(@RequestBody CustomerDto customer) throws UniversalException {
		try {
			Customer checkCustomer = customerDao.getById(customer.getCustomerNumber());
			if(checkCustomer == null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			
			if(customer.getBirthDate()!=null) {
				checkCustomer.setBirthDate(customer.getBirthDate());
			}
			if(customer.getFirstName()!=null) {
				checkCustomer.setFirstName(customer.getFirstName());
			}
			if(customer.getLastName()!=null) {
				checkCustomer.setLastName(customer.getLastName());
			}
			if(customer.getPhoneNumber()!=null) {
				checkCustomer.setPhoneNumber(customer.getPhoneNumber());
			}
			if(customer.getPhoneType()!=null) {
				checkCustomer.setPhoneType(customer.getPhoneType());
			}
			
			checkCustomer =  customerDao.save(checkCustomer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(checkCustomer, CustomerDto.class));
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			List<Customer> customers = customerDao.getList();

			return new CommonResponse<List<CustomerDto>>(customers.stream().map(cust -> modelMapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
		}
	}

	//EndUpdateCustomer
	
	//Delete
	@DeleteMapping("/customer/{customer}")
	public CommonResponse delete(@PathVariable("customer") Integer customerNumber) throws UniversalException {
		try {
			Customer checkCustomer = customerDao.getById(customerNumber);
			if(checkCustomer == null) {
				return new CommonResponse("06", "customer tidak ditemukan");
			}
			customerDao.delete(checkCustomer);
			return new CommonResponse();
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	//End Delete

}
