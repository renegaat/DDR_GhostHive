package net.pearlbay.service.ghosthiveconnector;

import java.util.ArrayList;
import java.util.List;

import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.ghost.SimpleGhost;
import net.pearlbay.model.hive.Hive;
import net.pearlbay.model.hive.SimpleHive;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.SimpleLocation;
import net.pearlbay.service.GhostService;
import net.pearlbay.service.HiveService;
import net.pearlbay.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GhostHiveServiceImpl implements GhostHiveService{

	@Autowired
	private GhostService ghostService ;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private HiveService hiveService;
	
	
	
	@RequestMapping(method= RequestMethod.GET,value="/getAllSimpleGhosts",produces="application/json")
	public @ResponseBody List<SimpleGhost> getAllSimpleGhosts() {
		java.util.List<SimpleGhost> returnList =  new ArrayList<SimpleGhost>();
		java.util.List<Ghost> ghostList = ghostService.listGhost();
		
		if(ghostList!=null){
			for(Ghost indexGhost : ghostList){
				returnList.add(new SimpleGhost(indexGhost));
			}
		}
		return returnList;
	}		

	@RequestMapping(method= RequestMethod.GET,value="/getSimpleGhostById/{id}",produces="application/json")
	public @ResponseBody SimpleGhost  getGhostById(@PathVariable int id) {		
		SimpleGhost resultGhost = new  SimpleGhost(ghostService.retrieveGhost(id));
		return  resultGhost;
	}	

	@RequestMapping(method= RequestMethod.GET,value="/getComplexGhostById/{id}",produces="application/json")
	public @ResponseBody Ghost  getComplexGhostById(@PathVariable int id) {		
		return  null;
	}	
	
	@RequestMapping(method= RequestMethod.GET,value="/getLocations",produces="application/json")
	public @ResponseBody List<SimpleLocation>  getLocations() {			
		java.util.List<SimpleLocation> returnList =  new ArrayList<SimpleLocation>();
		
		java.util.List<Location> locationList = locationService.listLocation();
		if(locationList!=null){		
			for(Location indexLocation : locationList){
				returnList.add(new SimpleLocation(indexLocation));
			}
		}
		
		return returnList;
	}	

	
	@RequestMapping(method= RequestMethod.GET, value="/getAllSimpleHives", produces="application/json")
	public @ResponseBody List<SimpleHive> getAllSimpleHives() {
		java.util.List<SimpleHive> returnList =  new ArrayList<SimpleHive>();
		java.util.List<Hive> hiveList = hiveService.listHive();
	
		if(hiveList!=null){
			for(Hive indexHive : hiveList){
				returnList.add(new SimpleHive(indexHive));
			}
		}	
		return returnList;
	}	
}
