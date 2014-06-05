package test.junit;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import net.pearlbay.localisation.google.direction.GoogleDirectionService;
import net.pearlbay.localisation.google.direction.Route;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.LocationTypes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGoogleDirectionService {


	//location1 53.54834,9.988822
	//location2 53.545471,9.984273
	//distance ca 200 m 
	
	GoogleDirectionService googleDirectionsService ;

	Location location1;
	Location location2;
	
	@Before
	public void setUp() throws Exception {
		location1 = new Location("Location1", new BigDecimal("53.54834"), new BigDecimal("9.988822"), LocationTypes.STANDARD);
		location2 = new Location("Location1", new BigDecimal("53.545471"), new BigDecimal("9.984273"), LocationTypes.STANDARD);	
		googleDirectionsService  = new GoogleDirectionService();
		googleDirectionsService.setApiKey("AIzaSyAXPhyPN7ekSxT5icTmtXC6iigZ6VA9Bsw");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRoute() {
		Route test = googleDirectionsService.getRoute(location1, location2);
		assertNotNull(test);
		assertTrue(test.getRoute().size()>0);
	}

}
