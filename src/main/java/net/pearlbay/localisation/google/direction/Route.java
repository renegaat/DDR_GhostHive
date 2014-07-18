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

	public Location getRouteStart(){
		return this.route != null ? (Location)this.route.get(0) : null ;
	}
	
	public Location getRouteEnd(){
		return this.route != null ? (Location)this.route.get(this.route.size()) : null ;
	}
}
