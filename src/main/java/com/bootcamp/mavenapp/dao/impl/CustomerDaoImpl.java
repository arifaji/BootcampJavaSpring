package com.bootcamp.mavenapp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.mavenapp.dao.CustomerDao;
import com.bootcamp.mavenapp.dao.repository.CustomerRepository;
import com.bootcamp.mavenapp.model.Customer;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao{

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public Customer getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(customer);
		
	}

	@Override
	public List<Customer> getList() throws Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		return q.getResultList();
	}

}
