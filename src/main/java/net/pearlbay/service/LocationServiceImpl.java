package net.pearlbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pearlbay.dao.LocationDAO;
import net.pearlbay.model.location.Location;


@Service
public class LocationServiceImpl implements LocationService {

	@Autowired LocationDAO locationDAO;

	@Override
	public void addLocation(Location location) {
		locationDAO.addLocation(location);
	}
	@Override
	public List<Location> listLocation() {
		return locationDAO.listLocation();
	}
	@Override
	public void removeLocation(Integer id) {
		locationDAO.removeLocation(id);	
	}
	
	@Override
	public Location getLocation(Integer id) {
		return locationDAO.getLocation(id);
	}
	
	@Override
	public void addRichLocation(Location location) {
		locationDAO.addRichLocation(location);
	}
	@Override
	public List<Location> getCloseLocations(Location location) {
		return locationDAO.getCloseLocations(location);
	}
}
