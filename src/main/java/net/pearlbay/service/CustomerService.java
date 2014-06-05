package net.pearlbay.service;

import java.util.List;

import net.pearlbay.model.customer.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);
    public List<Customer> listCustomer();
    public void removeCustomer(Integer id);	
    public Customer getCustomer(Integer id);	
}
