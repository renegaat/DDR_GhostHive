package net.pearlbay.service;

import java.util.List;

import net.pearlbay.model.location.Location;

public interface LocationService {
	public void addRichLocation(Location Location);
    public void addLocation(Location Location);
    public List<Location> listLocation();
    public void removeLocation(Integer id);
    public Location getLocation(Integer id);
    public List<Location> getCloseLocations(Location location);
    
}
