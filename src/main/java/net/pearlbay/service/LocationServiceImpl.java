package net.pearlbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pearlbay.dao.LocationDAO;
import net.pearlbay.model.location.Location;


@Service
public class LocationServiceImpl implements LocationService {

	@Autowired LocationDAO locationDAO;

	public void addLocation(Location location) {
		locationDAO.addLocation(location);
	}

	public List<Location> listLocation() {
		return locationDAO.listLocation();
	}

	public void removeLocation(Integer id) {
		locationDAO.removeLocation(id);	
	}
	
	public Location getLocation(Integer id) {
		return locationDAO.getLocation(id);
	}
	
	public void addRichLocation(Location location) {
		locationDAO.addRichLocation(location);
	}

	public List<Location> getCloseLocations(Location location) {
		return locationDAO.getCloseLocations(location);
	}
}
