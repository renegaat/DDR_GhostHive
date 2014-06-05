package net.pearlbay.dao;

import java.util.List;

import net.pearlbay.model.customer.Customer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCustomer(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> listCustomer() {
		return sessionFactory.getCurrentSession().createQuery("from Customer").list();
	}

	@Override
	public void removeCustomer(Integer id) {
		Customer myCustomer =  (Customer)sessionFactory.getCurrentSession().load(Customer.class,id);

		if(myCustomer!=null){
			sessionFactory.getCurrentSession().delete(myCustomer);
		}
	}

	@Override
	public Customer getCustomer(Integer id) {
		return (Customer)sessionFactory.getCurrentSession().load(Customer.class,id);
	}
}
