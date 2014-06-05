package net.pearlbay.localisation.google.places;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import net.pearlbay.localisation.google.GoogleServiceBase;
import net.pearlbay.model.location.Location;
import net.pearlbay.model.location.LocationInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





public class GooglePlacesService extends GoogleServiceBase {

	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	private static final String TYPE_SEARCH = "/search";
	private static final String OUT_JSON = "/json";


	public Set<LocationInfo> getPlacesByLocation (String keyword,Location location){

		logger.debug("Entered getPlacesByLocation keyword : " + keyword + ",location id  : " + location.getId());

		// get all places associated with this location object via google places api
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();

		Set<LocationInfo> resultList = null ;
		
		try {
			StringBuilder sb = new StringBuilder(PLACES_API_BASE);
			sb.append(TYPE_SEARCH);
			sb.append(OUT_JSON);
			sb.append("?sensor=false");
			sb.append("&key=" + this.getApiKey());
			sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
			sb.append("&location=" + String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()));
			sb.append("&radius=" + String.valueOf(this.getRadius()));

			URL url = new URL(sb.toString());
			conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1) {
				jsonResults.append(buff, 0, read);
			}
				
		} catch (IOException e) {
			logger.error("Error connecting to Places API + ", e);
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		// iterate the json result and build location - info objects
		  try {
	            // Create a JSON object hierarchy from the results
	            JSONObject jsonObj = new JSONObject(jsonResults.toString());
	            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

	            resultList = new HashSet<LocationInfo>();
	            
	            // Extract the Place descriptions from the results
	            for (int i = 0; i < predsJsonArray.length(); i++) {
	            
	            	LocationInfo myInfo = new LocationInfo();
	            	
	            	logger.debug("place found Name : " + predsJsonArray.getJSONObject(i).getString("name"));
	            	logger.debug("place found reference : " + predsJsonArray.getJSONObject(i).getString("reference"));
	            	
	            	myInfo.setName( predsJsonArray.getJSONObject(i).getString("name"));
	            	myInfo.setContent((String)predsJsonArray.getJSONObject(i).toString());
	            	myInfo.setType(predsJsonArray.getJSONObject(i).getString("types"));
	            	myInfo.setLocation(location);
	            	resultList.add(myInfo);
	            }
	        } catch (JSONException e) {
	        	logger.error("Error parsing json result  + ", e);
	        }
		
		return resultList;
	}

}
