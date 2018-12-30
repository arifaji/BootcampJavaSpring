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
import com.bootcamp.mavenapp.dao.TransactionDao;
import com.bootcamp.mavenapp.exceptions.UniversalException;
import com.bootcamp.mavenapp.model.Account;
import com.bootcamp.mavenapp.model.Transaction;
import com.bootcamp.mavenapp.model.dto.CommonResponse;
import com.bootcamp.mavenapp.model.dto.TransactionDto;

@RestController
@RequestMapping("")
@SuppressWarnings("rawtypes")
public class ControllerTransaction {
	
	private static Logger logger = LoggerFactory.getLogger(ControllerTransaction.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	//Start get transaction/id
	@GetMapping("transaction/{idTrx}")
	public CommonResponse getById(@PathVariable("idTrx") String id) throws UniversalException{
		logger.info("id : {}",id);
		try {
				Transaction transaction = transactionDao.getById(Integer.parseInt(id));
				
				return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
			} catch (UniversalException e) {
				logger.error(e.getMessage());
				return new CommonResponse("01", e.getMessage());
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
				return new CommonResponse("06", "parameter harus angka");
		
			} catch (Exception e) {
				logger.error(e.getMessage());
				return new CommonResponse("06", e.getMessage());
			}
	}
	
	//Start /hello
			@GetMapping("/transaction")
			public String transactionview(@RequestParam(value="id", defaultValue="") String id) {
				try {
					Transaction transaction = transactionDao.getById(Integer.valueOf(id));
					if (transaction == null) {
						return "data tidak ditemukan";
					} else {
						return "hello, your amount "+transaction.getAmount();
					}
				} catch (NumberFormatException e) {
					return "salah format input";
				} catch (Exception e) {
					return String.format("terjadi kesalahan system : %s", e.getMessage());
				}
			}
			//End /hello
//======================================================================================	
	//Start  /post Save-Create --!> 
	/* Noted : input di postman JSON harus lengkap sesuai dg database, 
	 * mulai dari : firstName, lastName, birthData, username, password, phoneNumber, phoneType
	 */	
	@PostMapping("/transaction")
	public CommonResponse insert(@RequestBody TransactionDto transactionDto) throws UniversalException{
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction = transactionDao.save(transaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
		
	}
	//End /post Save-Create
	
	//Update Transaction
	@PutMapping("/transaction")
	public CommonResponse update(@RequestBody TransactionDto transaction) throws UniversalException{
		try {
			Transaction checkTransaction = transactionDao.getById(transaction.getId());
			if(checkTransaction == null) {
				return new CommonResponse("14", "transaction tidak ditemukan");
			}
			if(transaction.getType()!=null) {
				checkTransaction.setType(transaction.getType());
			}
			if(transaction.getAmount()!=null) {
				checkTransaction.setAmount(transaction.getAmount());
			}
			if(transaction.getAmountsign()!=null) {
				checkTransaction.setAmountsign(transaction.getAmountsign());
			}
			if(transaction.getAccount()!=null) {
				checkTransaction.setAccount(transaction.getAccount());
			}
			
			checkTransaction = transactionDao.save(checkTransaction);
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			List<Transaction> transactions = transactionDao.getList();
			return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx -> modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
		}
	}
	//EndUpdateCustomer
	
	//Delete
	@DeleteMapping("/transaction/{idTrx}")
	public CommonResponse delete(@PathVariable("idTrx")Integer id) throws UniversalException{
		
		try {
			Transaction checkTransaction = transactionDao.getById(id);
			if(checkTransaction == null) {
				return new CommonResponse("06", "transaction tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (UniversalException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	//End Delete
	
//==== tambahan dari bapakya
		@GetMapping("/transactions")
		public CommonResponse getListTransaction(@RequestParam(name="account", defaultValue="") String account) throws UniversalException {
			try {
				logger.info("account get list params : {}", account);
				
				if(!StringUtils.isEmpty(account)) {
		            Account checkAccount = accountDao.getById(Integer.parseInt(account));
		            if(checkAccount==null) {
		                throw new UniversalException("account tidak ditemukan");
		            }
		            List<Transaction> transactions = transactionDao.getListByAccount(checkAccount);
		            return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx -> modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
		        }else {
		        	List<Transaction> transactions = transactionDao.getList();
		        	return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx -> modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
		        }	
				
			} catch (UniversalException e) {
				throw e;
			} catch(NumberFormatException e) {
				return new CommonResponse("01", e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
				return new CommonResponse("06", e.getMessage());
			}
		}


}
