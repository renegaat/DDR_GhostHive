package net.pearlbay.dao;

import java.util.List;

import net.pearlbay.model.customer.Customer;

public interface CustomerDAO {

	public void addCustomer(Customer custoer);
    public List<Customer> listCustomer();
    public void removeCustomer(Integer id);	
    public Customer getCustomer(Integer id);	
}
