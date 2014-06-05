package net.pearlbay.localisation.google.direction;

import java.util.ArrayList;
import java.util.List;

import net.pearlbay.model.location.Location;

public class Route {
	
	List<Location>route = new ArrayList<Location>();

	public List<Location> getRoute(){ 
		return route ;
	}

	public void setRoute(List<Location> route) {
		this.route = route;
	}
}
