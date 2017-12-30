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
	
	public void addGhost(Ghost ghost) {
		sessionFactory.getCurrentSession().saveOrUpdate(ghost);
	}

	public void mergeGhost(Ghost ghost) {
		sessionFactory.getCurrentSession().merge(ghost);
	}

	@SuppressWarnings("unchecked")
	public List<Ghost> listGhosts() {
		return sessionFactory.getCurrentSession().createQuery("from Ghost").list();
	}

	public void removeGhost(Integer id) {
		Ghost myGhost =  (Ghost)sessionFactory.getCurrentSession().load(Ghost.class,id);

		if(myGhost!=null){
			sessionFactory.getCurrentSession().delete(myGhost);
		}
	}

	public Ghost getGhost(Integer id) {
		return (Ghost)sessionFactory.getCurrentSession().get(Ghost.class,id);
	}

}
