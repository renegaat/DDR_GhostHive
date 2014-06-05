package net.pearlbay.dao;

import java.util.List;

import net.pearlbay.model.hive.Hive;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class HiveDAOImpl implements HiveDAO {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addHive(Hive hive) {
		sessionFactory.getCurrentSession().save(hive);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hive> listHive() {
		return sessionFactory.getCurrentSession().createQuery("from Hive").list();
	}

	@Override
	public void removeHive(Integer id) {
		Hive myHive =  (Hive)sessionFactory.getCurrentSession().load(Hive.class,id);

		if(myHive!=null){
			sessionFactory.getCurrentSession().delete(myHive);
		}	
	}
	
	@Override
	public Hive getHive(Integer id) {
		return (Hive) sessionFactory.getCurrentSession().load(Hive.class,id);
	}
}
