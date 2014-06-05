package net.pearlbay.localisation.google.direction;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.pearlbay.localisation.google.GoogleServiceBase;
import net.pearlbay.localisation.utility.LocationUtility;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.LocationTypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleDirectionService extends GoogleServiceBase {

	private static final String DIRECTION_API_BASE = "https://maps.googleapis.com/maps/api/directions";
	private static final String OUT_JSON = "/json";

	//reference mindistance for route calculation
	final static int MINDISTANCE = 10;

	/**
	 * 
	 * @param startLocation
	 * @param endLocation
	 * @return
	 * 
	 */
	
	public Route getRoute(Location startLocation,Location endLocation){

		logger.debug("Entered getRoute. Startlocation : " +  startLocation.getId() + ", Endlocation  : " + endLocation.getId());

		Route myRoute = new Route();

		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();

		try {
			StringBuilder sb = new StringBuilder(DIRECTION_API_BASE);
			sb.append(OUT_JSON);
			sb.append("?sensor=false");
			sb.append("&key=" + this.getApiKey());
			sb.append("&origin="+ String.valueOf(startLocation.getLatitude())+","+ String.valueOf(startLocation.getLongitude()));
			sb.append("&destination="+ String.valueOf(endLocation.getLatitude())+","+ String.valueOf(endLocation.getLongitude()));

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}

		} catch (IOException e) {
			logger.error("Error connecting to Direction API + ", e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray routeJsonArray = jsonObj.getJSONArray("routes");

			List<Location> myList = new ArrayList<Location>(); 

			// Extract the Route info from the json result  into a route bean
			for (int i = 0; i < routeJsonArray.length(); i++) {
				JSONArray legJsonArray = routeJsonArray.getJSONObject(i).getJSONArray("legs");
				for (int x = 0; x< legJsonArray.length(); x++) {
					JSONArray stepJsonArray = legJsonArray.getJSONObject(x).getJSONArray("steps");
					for (int y = 0; y< stepJsonArray.length(); y++){
						JSONObject start =  stepJsonArray.getJSONObject(y).getJSONObject("start_location");
						JSONObject end =  stepJsonArray.getJSONObject(y).getJSONObject("end_location");
						myList =  LocationUtility.getWaySteps(start.getString("lat"), start.getString("lng"),end.getString("lat"),end.getString("lng"));
						myRoute.getRoute().addAll(myList);
					}
				}
			}
		} catch (JSONException e) {
			logger.error("Error parsing json result  + ", e);
			return null;
		}
		return myRoute;
	}

}
