package com.bootcamp.mavenapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.mavenapp.dao.AccountDao;
import com.bootcamp.mavenapp.dao.CustomerDao;
import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Customer;
import com.bootcamp.mavenapp.model.dto.AccountDto;
import com.bootcamp.mavenapp.model.dto.CommonResponse;

@RestController
@RequestMapping("")
@SuppressWarnings("rawtypes")
public class ControllerAccount {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAccount.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AccountDao accountDao;
	
	//Start /account/nomer
	@GetMapping(value="/account/{accountNumber}")	
	public CommonResponse getById(@PathVariable("accountNumber")String accountNumber) throws UniversalException {
		LOGGER.info("accountNumber : {}",accountNumber);
		
		try {
			Account account = accountDao.getById(Integer.parseInt(accountNumber));
			return new CommonResponse<Account>(account);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("60", e.getMessage());
		}
	}
	//end /account/nomer
	
	//======================================================================================	
	//Start  /post Save-Create --!> 
	/* Noted : input di postman JSON harus lengkap sesuai dg database, 
	 * mulai dari : firstName, lastName, birthData, username, password, phoneNumber, phoneType
	 */		
	@PostMapping("/account")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws UniversalException {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account = accountDao.save(account);
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
			
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	//End /post Save-Create
	
	//Start Update Customer
	@PutMapping("/account")
	public CommonResponse update(@RequestBody AccountDto account) throws UniversalException{
		
		try {
			Account checkAccount = accountDao.getById(account.getAccountNumber());
			if(checkAccount==null) {
				return new CommonResponse("14", "Account tidak ditemukan");
			}
			if(account.getOpenDate()!=null) {
				checkAccount.setOpenDate(account.getOpenDate());
			}
			if(account.getBalance()!=null) {
				checkAccount.setBalance(account.getBalance());
			}
			if(account.getCustomer()!=null) {
				checkAccount.setCustomer(account.getCustomer());
			}
			
			checkAccount = accountDao.save(checkAccount);
			
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, AccountDto.class));
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
			List<Account> accounts = accountDao.getList();
			return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
			
		}
			
	}
	//EndUpdateCustomer
	
	//Start Delete
	@DeleteMapping("/account/{account}")
	public CommonResponse delete(@PathVariable ("account") Integer accountNumber) throws UniversalException {
		try {
			Account checkAccount = accountDao.getById(accountNumber);
			if(checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	
	}
	//End Delete
	
	//Start /hello
		@GetMapping("/account")
		public String account(@RequestParam(value="id", defaultValue="") String id) {
			try {
				Account account = accountDao.getById(Integer.valueOf(id));
				if (account == null) {
					return "data tidak ditemukan";
				} else {
					return "hello, your account "+account.getAccountNumber();
				}
			} catch (NumberFormatException e) {
				return "salah format input";
			} catch (Exception e) {
				return String.format("terjadi kesalahan system : %s", e.getMessage());
			}
		}
		//End /hello
		
		//Get List Account
		@GetMapping("/accountlist")
		public CommonResponse getAllList(@RequestParam(name="account", defaultValue="") String account) throws UniversalException{
			try {
				LOGGER.info("account get list, params : {}", account);
			
				return new CommonResponse<List<Account>>(accountDao.getList());
			} catch (UniversalException e) {
				throw e;
			} catch(NumberFormatException e) {
				return new CommonResponse("01", e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				return new CommonResponse("06", e.getMessage());
			}
		}

		//End Get List Account
		
		
	//==== tambahan dari bapakya
		@GetMapping("/accounts")
		public CommonResponse getListAccount(@RequestParam(name="customer", defaultValue="") String customer) throws UniversalException {
			try {
				LOGGER.info("account get list params : {}", customer);
//				List<Account> accounts = accountDao.getList();
				
				if(!StringUtils.isEmpty(customer)) {
		            Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
		            if(checkCustomer==null) {
		                throw new UniversalException("customer tidak ditemukan");
		            }
		            List<Account> accounts = accountDao.getListByCustomer(checkCustomer);
		            return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
		        }else {
		        	List<Account> accounts = accountDao.getList();
		        	return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
		        }	
				
			} catch (UniversalException e) {
				throw e;
			} catch(NumberFormatException e) {
				return new CommonResponse("01", e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				return new CommonResponse("06", e.getMessage());
			}
		}


}
