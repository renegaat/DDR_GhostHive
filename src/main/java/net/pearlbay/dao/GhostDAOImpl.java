package net.pearlbay.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.pearlbay.model.ghost.Ghost;


@Transactional
@Repository
public class GhostDAOImpl implements GhostDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addGhost(Ghost ghost) {
		sessionFactory.getCurrentSession().saveOrUpdate(ghost);
	}

	@Override
	public void mergeGhost(Ghost ghost) {
		sessionFactory.getCurrentSession().merge(ghost);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ghost> listGhost() {
		return sessionFactory.getCurrentSession().createQuery("from Ghost").list();
	}

	@Override
	public void removeGhost(Integer id) {
		Ghost myGhost =  (Ghost)sessionFactory.getCurrentSession().load(Ghost.class,id);

		if(myGhost!=null){
			sessionFactory.getCurrentSession().delete(myGhost);
		}
	}

	@Override
	public Ghost getGhost(Integer id) {
		return (Ghost)sessionFactory.getCurrentSession().get(Ghost.class,id);
	}


	

}
