package net.pearlbay.localisation.utility;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pearlbay.localisation.google.GoogleServiceBase;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.LocationTypes;
import net.pearlbay.service.LocationService;

public class LocationUtility {

	
	public static Logger logger = LoggerFactory.getLogger(GoogleServiceBase.class);
		
	//min distance for random point in m
	final static int RNDMINDISTANCE = 500;
	//max distance for random point in m
	final static int RNDMMAXDISTANCE = 1500;
	//waypoint max distance
	final static int WAYPOINTMAXDISTANCE = 20;
	
	// return distance between two locations
	public static double calcDistance(Location startpoint ,Location endpoint){
		
		double d2r = Math.PI / 180;
		double dlong = (endpoint.getLongitude().doubleValue() - startpoint.getLongitude().doubleValue()) * d2r;
		double dlat = (endpoint.getLatitude().doubleValue() - startpoint.getLatitude().doubleValue()) * d2r;
		double a =
				Math.pow(Math.sin(dlat / 2.0), 2)
				+ Math.cos(startpoint.getLatitude().doubleValue() * d2r)
				* Math.cos(endpoint.getLatitude().doubleValue() * d2r)
				* Math.pow(Math.sin(dlong / 2.0), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = (6367 * c)*1000;

		return  d ;
	}


	// get a random Location . Reference Point is location. Water is ignored 
	public static Location getRandomPoint(Location location){
		 
		Location resultLocation = new Location();
		Random random = new Random();
		// get random distance 
		double radius =  (Math.random() * (RNDMMAXDISTANCE - RNDMINDISTANCE) + RNDMINDISTANCE);

		// Convert radius from meters to degrees	
		double radiusInDegrees = radius / 111000f;

		double u = random.nextDouble(); // 
		double v = random.nextDouble();
		
		double w = radiusInDegrees * Math.sqrt(u); //distance
		
		double t = 2 * Math.PI * v; // longitude 
		double x = w * Math.cos(t);
		double new_x = x / Math.cos(location.getLatitude().doubleValue());
		double foundLongitude = new_x + location.getLongitude().doubleValue();
		
		double y = w * Math.sin(t);
		double foundLatitude = y + location.getLatitude().doubleValue();
		

		resultLocation.setLatitude(new BigDecimal(foundLatitude,MathContext.DECIMAL64));
		resultLocation.setLongitude(new BigDecimal(foundLongitude,MathContext.DECIMAL64));
		resultLocation.setLocationType(LocationTypes.STANDARD);
		resultLocation.setName("");

		return resultLocation; 
	}


	// get shortsteps for the ghost to move from a to b.  the steps are WAYPOINTMAXDISTANCE apart
	public static List<Location> getWaySteps(String startLatitude,String startLongitude,String endLatitude,String endLongitude){
	
		logger.debug("Get WaySteps. Start : " + startLatitude + "  " + startLongitude + " End : " + endLatitude + " " + endLongitude);

		List<Location> resultList = new ArrayList<Location>(); 

		//create location objects
		Location startLocation = new Location("",new BigDecimal(startLatitude),new BigDecimal(startLongitude),LocationTypes.STANDARD);
		Location endLocation = new Location("",new BigDecimal(endLatitude),new BigDecimal(endLongitude),LocationTypes.STANDARD);

		//get distance between two points
		double distance = LocationUtility.calcDistance(startLocation, endLocation);

		if(distance < WAYPOINTMAXDISTANCE){
			resultList.add(startLocation);
			resultList.add(endLocation);
			return resultList;
		}

		// calculatge the number of points to be inserte, we dont have to be 100% correct here 
		int numberOfPoints = (int) (distance / WAYPOINTMAXDISTANCE);
		//get map angle from startpoint to endpoint 
	
		resultList.add(startLocation);

		int reference = numberOfPoints + 1;
		
		BigDecimal coLatitude =  ((endLocation.getLatitude().subtract((startLocation.getLatitude()))).divide(new BigDecimal((double)(reference)),RoundingMode.HALF_UP));
		BigDecimal coLongitude =  ((endLocation.getLongitude().subtract((startLocation.getLongitude()))).divide(new BigDecimal((double)(reference)),RoundingMode.HALF_UP));
		
		for (int i = 1; i <= (numberOfPoints); i++) {
		
			BigDecimal newLatitude  = startLocation.getLatitude().add(coLatitude.multiply(new BigDecimal(i)));
			BigDecimal newLongitude  = startLocation.getLongitude().add(coLongitude.multiply(new BigDecimal(i)));
			logger.debug(newLatitude + "," + newLongitude +"--");
							
			resultList.add(new Location("",newLatitude,newLongitude,LocationTypes.STANDARD));
		}
		
		resultList.add(endLocation);

		return resultList;
	}	

	//return bearing between two points	
	public static double bearing(Location startPoint,Location endPoint){
		  double longitude1 = startPoint.getLongitude().doubleValue();
		  double longitude2 = endPoint.getLongitude().doubleValue();
		  double latitude1 = Math.toRadians(startPoint.getLatitude().doubleValue());
		  double latitude2 = Math.toRadians(endPoint.getLatitude().doubleValue());
		  double longDiff= Math.toRadians(longitude2-longitude1);
		  double y= Math.sin(longDiff)*Math.cos(latitude2);
		  double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);
		  return (Math.toDegrees(Math.atan2(y, x))+360)%360;
		}
}
