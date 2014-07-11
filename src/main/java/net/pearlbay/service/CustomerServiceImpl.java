package net.pearlbay.service;

import java.util.List;

import net.pearlbay.dao.CustomerDAO;
import net.pearlbay.model.customer.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired CustomerDAO customerDAO;

	@Override
	public void addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);	
	}

	@Override
	public List<Customer> listCustomer() {
		return customerDAO.listCustomer();
	}
	@Override
	
	public void removeCustomer(Integer id) {
		customerDAO.removeCustomer(id);
	}

	@Override 
	public Customer getCustomer(Integer id) {
		return customerDAO.getCustomer(id);
	}
}
  