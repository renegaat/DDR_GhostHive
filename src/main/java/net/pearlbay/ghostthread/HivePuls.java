package net.pearlbay.ghostthread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import net.pearlbay.behaviour.BehaviourProvider;
import net.pearlbay.localisation.google.GoogleServiceBase;
import net.pearlbay.localisation.google.direction.GoogleDirectionService;
import net.pearlbay.localisation.google.places.GooglePlacesService;
import net.pearlbay.localisation.utility.LocationUtility;
import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.location.Location;
import net.pearlbay.service.GhostService;
import net.pearlbay.service.LocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HivePuls {
	
	public Logger logger = LoggerFactory.getLogger(HivePuls.class);
	
	@Autowired
	private GhostService ghostService;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private BehaviourProvider behaviourProvider;
	
	
	@Autowired 
	private GoogleDirectionService googleDirectionService;
	
	
	@Autowired 
	private GooglePlacesService googleplacesService;
	

	//init the ghost hive , start Ghost Pulses for each ghost in the database 
	@PostConstruct
	public void initIt() throws Exception {

		List<Ghost> ghostList = ghostService.listGhost();
		ExecutorService executor = Executors.newFixedThreadPool(ghostList.size());

		for (int i = 0; i < ghostList.size(); i++) {
			Runnable worker = new GhostThread((Ghost)ghostList.get(i));
			executor.execute(worker);
		}		
	}

	public  class GhostThread implements Runnable {
		private final Ghost ghost;

		GhostThread(Ghost ghost) {
			this.ghost = ghost;
		}
		public void run() {
			boolean run = true;
			int routeIndex = 0;
			
			while(run){
				
				try {
					Thread.sleep(10000);
						
					/*
					 * In this section the Ghost will get its action via the behaviour provider  
					 * For now its just moves in random directions across the map
					 * 
					 */
					
					if(ghost.getRoute()==null){
						//ghost has no route 
						routeIndex = 0;
						ghost.setRoute(googleDirectionService.getRoute(ghost.getLocationFrom(),ghost.getLocationTo()));
					}
					
					
					//update ghost Position in db
					ghost.setLatitude(ghost.getRoute().getRoute().get(routeIndex).getLatitude());
					ghost.setLongitude(ghost.getRoute().getRoute().get(routeIndex).getLongitude());
					ghostService.addGhost(ghost);
					//
					logger.debug("Ghost " + ghost.getId() + " Position : " +  ghost.getLatitude() +","+ ghost.getLongitude() );
					
					routeIndex++;
					
					
					if(routeIndex == ghost.getRoute().getRoute().size()){
						
						logger.debug("Ghost " + ghost.getId() + " Route change.");
						
						// we have reached the end of the  route 
						ghost.setRoute(null);
						//gather data from current position if necessary
						
						List<Location> testList = locationService.getCloseLocations(ghost.getLocationTo());
						//persist new rich location if there is non close by
						if(testList.size()==1){
							locationService.addRichLocation(ghost.getLocationAt());
							logger.debug("Ghost " + ghost.getId() + " rich location added id : " + ghost.getLocationAt().getId());
						}
						
						//send ghost on a new route
						ghost.setLocationAt(ghost.getLocationTo());
						ghost.setLocationFrom(ghost.getLocationTo());
					
						//get a new location
						Location newLocation = LocationUtility.getRandomPoint(ghost.getLocationAt());
						//claim ownerShip
						newLocation.setHive(ghost.getHive());
						//test if this location is already known
						testList = locationService.getCloseLocations(newLocation);
						
						if(testList.size()==0){
							//persist new location into object graph
							locationService.addLocation(newLocation);
							//set to new location
							ghost.setLocationTo(newLocation);
						}else{
							//snap to known location
							ghost.setLocationTo((Location)testList.get(0));
						}						
						
						logger.debug("Ghost " + ghost.getId() + " new Destination : " + ghost.getLocationTo().getLatitude() +" ," +  ghost.getLocationTo().getLongitude());
					}
										
				} catch (InterruptedException e) {
					logger.error("Hive Puls Interrupted Exception : " + e.getLocalizedMessage());
					e.printStackTrace();
				}catch (Exception e) {
					logger.error("Hive Puls General Exception : " + e.getLocalizedMessage());
					e.printStackTrace();
				}
			}
		}
	}
}



