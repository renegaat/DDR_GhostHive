package test.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHiveWebService {

	private final String URL = "http://localhost:8080/DDR_GhostHive/admin/";
	private final String USER_AGENT = "Mozilla/5.0";

	@Before
	public void setUp() throws Exception {

	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllSimpleGhosts() {
		try {
			URL obj = new URL(URL + "getAllSimpleGhosts");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT); 		
			int responseCode = con.getResponseCode();
			assertTrue(responseCode==200);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			assertTrue(response.indexOf("name")!=-1);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail();

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}	
	}

	@Test
	public void testLocations() {
		try{
			URL obj = new URL(URL + "getLocations");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT); 		
			int responseCode = con.getResponseCode();
			assertTrue(responseCode==200);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			assertTrue(response.indexOf("locationName")!=-1);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail();

		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}	
	}
}
