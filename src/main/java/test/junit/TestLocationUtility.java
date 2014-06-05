package test.junit;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import net.pearlbay.localisation.utility.LocationUtility;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.LocationTypes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLocationUtility {

	//location1 53.54834,9.988822
	//location2 53.545471,9.984273
	//distance ca 200 m 
	
	//location 3 53.544045, 9.980342
	Location location1;
	Location location2;
	
	@Before
	public void setUp() throws Exception {
	
		location1 = new Location("Location1", new BigDecimal("53.54834"), new BigDecimal("9.988822"), LocationTypes.STANDARD);
		location2 = new Location("Location1", new BigDecimal("53.545471"), new BigDecimal("9.984273"), LocationTypes.STANDARD);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalcDistance() {
		double distance = LocationUtility.calcDistance(location1, location2);
		assertFalse((distance < 200)&&(distance > 250));
	}

	@Test
	public void testGetRandomPoint() {
		Location randomLocation = LocationUtility.getRandomPoint(location1);
		assertNotNull(randomLocation);
	}

	
	@Test
	public void testGetWaySteps() {
		List<Location> test = LocationUtility.getWaySteps(location1.getLatitude().toString(),location1.getLongitude().toEngineeringString(), location2.getLatitude().toEngineeringString(), location2.getLongitude().toEngineeringString());
		assertNotNull(test);
		assertTrue(test.size()>0);
	}

	@Test
	public void testBearing() {
		double bearing = LocationUtility.bearing(location1, location2);
		assertFalse((bearing < 80)&&(bearing > 60));
	}

}
