package net.pearlbay.dao;

import java.util.List;

import net.pearlbay.model.hive.Hive;


public interface HiveDAO {
	public void addHive(Hive hive);
    public List<Hive> listHive();
    public void removeHive(Integer id);	
    public Hive getHive(Integer id);	
}
