package test.junit;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import net.pearlbay.localisation.google.direction.GoogleDirectionService;
import net.pearlbay.localisation.google.places.GooglePlacesService;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.LocationTypes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGooglePlacesServices {

	GooglePlacesService googlePlacesService ;

	Location location1;
	Location location2;
	
	@Before
	public void setUp() throws Exception {
		location1 = new Location("Location1", new BigDecimal("53.54834"), new BigDecimal("9.988822"), LocationTypes.STANDARD);
		location2 = new Location("Location1", new BigDecimal("53.545471"), new BigDecimal("9.984273"), LocationTypes.STANDARD);	
	
		googlePlacesService = new GooglePlacesService();
		googlePlacesService.setApiKey("AIzaSyAXPhyPN7ekSxT5icTmtXC6iigZ6VA9Bsw");
		googlePlacesService.setRadius("200");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPlacesByLocation() {
		Set test = googlePlacesService.getPlacesByLocation("", location1);
		assertNotNull(test); 
		assertTrue(test.size()>0);
	}
}
