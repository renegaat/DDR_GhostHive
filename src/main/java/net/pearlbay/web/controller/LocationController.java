package net.pearlbay.web.controller;

import java.util.Map;

import net.pearlbay.localisation.google.direction.GoogleDirectionService;
import net.pearlbay.localisation.google.places.GooglePlacesService;
import net.pearlbay.localisation.utility.LocationUtility;
import net.pearlbay.model.location.Location;
import net.pearlbay.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class LocationController {
	   
    @Autowired
    private LocationService locationService;
    
    @Autowired 
    private GoogleDirectionService googleDirectionService;
    
    @Autowired 
    private GooglePlacesService googlePlacesService;
   
       
    @RequestMapping("/location")
    public String listLocations(Map<String, Object> map) {
        map.put("location", new Location());
        map.put("locationList", locationService.listLocation());  
        return "location";
    }

 
 
    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addlocation(@ModelAttribute("location")
    Location location, BindingResult result) {

    	locationService.addRichLocation(location);
    	Location testLocation = LocationUtility.getRandomPoint(location);
    	
    	return "redirect:/admin/location";
    }
    
   
    @RequestMapping("/delete/{locationId}")
    public String deleteLocation(@PathVariable("locationId")
    Integer locationId) {
        locationService.removeLocation(locationId);
        return "redirect:/admin/location";
    }

}	