package net.pearlbay.model.location;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.pearlbay.model.helper.HiveColor;

public class SimpleLocation {

	@SuppressWarnings("unused")
	public int id;
	@SuppressWarnings("unused")
	public String locationName;
	@SuppressWarnings("unused")
	public BigDecimal latitude;
	@SuppressWarnings("unused")
	public BigDecimal longitude;
	public Set<InlineLocationInfo>locationInfo;
	@SuppressWarnings("unused")
	public LocationTypes locationType;
	@SuppressWarnings("unused")
	public HiveColor hiveColor;
	
	public SimpleLocation(Location referenceLocation) {
		this.id = referenceLocation.getId();
		this.locationName = referenceLocation.getName();
		this.latitude = referenceLocation.getLatitude();
		this.longitude = referenceLocation.getLongitude();
		this.hiveColor = referenceLocation.getHive().getHiveColor();
		
		if(locationInfo==null){
			locationInfo = new HashSet(); {
			}
		}
		for(LocationInfo indexObject : referenceLocation.getLocationInfo()  ){
			locationInfo.add(new InlineLocationInfo(indexObject));
		}
	}
	
	public class InlineLocationInfo{
		public String infoName;
		public InlineLocationInfo(LocationInfo referenceLocationInfo){
			this.infoName = referenceLocationInfo.getName();
		}	
	}	
}
