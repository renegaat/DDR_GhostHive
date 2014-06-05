package net.pearlbay.localisation.google;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GoogleServiceBase {
	private String apiKey;
	private String radius;
	
	public Logger logger = LoggerFactory.getLogger(GoogleServiceBase.class);
		

	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}		
}
