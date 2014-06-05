package net.pearlbay.service;

import java.util.List;

import net.pearlbay.dao.GhostDAO;
import net.pearlbay.model.ghost.Ghost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GhostServiceImpl implements GhostService {

	@Autowired GhostDAO ghostDao;
	
	@Override
	public void addGhost(Ghost ghost) {
		ghostDao.addGhost(ghost);
	}

	@Override
	public List<Ghost> listGhost() {
		return ghostDao.listGhost();
	}

	@Override
	public void removeGhost(Integer id) {
		ghostDao.removeGhost(id);
	}

	@Override
	public Ghost getGhost(Integer id) {
			return ghostDao.getGhost(id);
	}

	@Override
	public void mergeGhost(Ghost ghost) {
		ghostDao.mergeGhost(ghost);
	}
}
