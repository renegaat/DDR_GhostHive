package net.pearlbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pearlbay.dao.HiveDAO;
import net.pearlbay.model.hive.Hive;


@Service
public class HiveServiceImpl implements HiveService {
	
	@Autowired HiveDAO hiveDAO;
	
	
	@Override
	public void addHive(Hive hive) {
		hiveDAO.addHive(hive);
	}

	@Override
	public List<Hive> listHive() {
		return hiveDAO.listHive();
	}

	@Override
	public void removeHive(Integer id) {
		hiveDAO.removeHive(id);
	}

	@Override
	public Hive getHive(Integer id) {
		return hiveDAO.getHive(id);
	}
}
