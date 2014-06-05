package net.pearlbay.service;

import java.util.List;

import net.pearlbay.model.hive.Hive;

public interface HiveService {
	public void addHive(Hive hive);
    public List<Hive> listHive();
    public void removeHive(Integer id);	
    public Hive getHive(Integer id);	

}
