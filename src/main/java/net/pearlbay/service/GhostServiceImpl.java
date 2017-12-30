package net.pearlbay.service;

import java.util.List;

import net.pearlbay.dao.GhostDAO;
import net.pearlbay.model.ghost.Ghost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GhostServiceImpl implements GhostService {

	@Autowired GhostDAO ghostDao;

	public void addGhost(Ghost ghost) {
		ghostDao.addGhost(ghost);
	}

	public List<Ghost> listGhost() {
		return ghostDao.listGhosts();
	}

	public void removeGhost(Integer id) {
		ghostDao.removeGhost(id);
	}

	public Ghost retrieveGhost(Integer id) {
			return ghostDao.getGhost(id);
	}

	public void mergeGhost(Ghost ghost) {
		ghostDao.mergeGhost(ghost);
	}
}
