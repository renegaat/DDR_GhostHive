package net.pearlbay.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.ghost.GhostBody;
import net.pearlbay.model.ghost.GhostPersonality;
import net.pearlbay.model.helper.BodyStates;
import net.pearlbay.model.helper.PersonalityStates;
import net.pearlbay.model.location.Location;
import net.pearlbay.service.CustomerService;
import net.pearlbay.service.GhostService;
import net.pearlbay.service.HiveService;
import net.pearlbay.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class GhostController {
 
    @Autowired
    private GhostService ghostService;
    @Autowired
    private HiveService hiveService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CustomerService customerService;
   
     
    @RequestMapping("/ghost")
    public String listGhosts(Map<String, Object> map) {
    	
    	List<Location> locationList  = locationService.listLocation();
   
        map.put("ghost", new Ghost());
        map.put("ghostList", ghostService.listGhost());  
        map.put("locationListTo", locationList);
        map.put("locationListFrom", locationList);
        map.put("locationListAt", locationList);
        map.put("hiveList", hiveService.listHive());
        map.put("customerList", customerService.listCustomer());      
                   
        return "ghost";   
    }
   
    @RequestMapping(value = "/addGhost", method = RequestMethod.POST)
    public String addGhost(@ModelAttribute("ghost") Ghost ghost, BindingResult result) {
    	
    	//create random personality
    	GhostPersonality myPersonality = new GhostPersonality();
    	myPersonality.setAggression(randomPersonalityState());
    	myPersonality.setIntelligence(randomPersonalityState());
    	myPersonality.setCuriosity(randomPersonalityState());
    	myPersonality.setGhost(ghost);
    	 	
    	//create random Body
    	GhostBody myBody = new GhostBody();
    	myBody.setEnergy(randomBodyState());
    	myBody.setLive(randomBodyState());
    	myBody.setGhost(ghost);
    	
    	ghost.setGhostBody(myBody);
    	ghost.setGhostPersonality(myPersonality);
        ghostService.addGhost(ghost);
        return "redirect:/admin/ghost";
    }
  
   
    @RequestMapping("/deleteGhost/{ghostId}")
    public String deleteGhost(@PathVariable("ghostId")
    Integer ghostId) {
    	
    	ghostService.removeGhost(ghostId);
        return "redirect:/admin/ghost";
    }

    // helper functions generell
    
    // helper function random personality state
    private PersonalityStates randomPersonalityState() {
        int pick = new Random().nextInt(PersonalityStates.values().length);
        return PersonalityStates.values()[pick];
    }

    // helper function random body state
    private BodyStates randomBodyState() {
        int pick = new Random().nextInt(BodyStates.values().length);
        return BodyStates.values()[pick];
    }
}	