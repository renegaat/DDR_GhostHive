package net.pearlbay.model.location;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.pearlbay.model.hive.Hive;

import org.codehaus.jackson.annotate.JsonManagedReference;


@Entity
@Table(name="ddr_location")
public class Location {
	private int id;
	private String name;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Set<LocationInfo>locationInfo;
	private LocationTypes locationType;
	private Hive hive;

	public Location() {}
	
	public Location(String name, BigDecimal latitude, BigDecimal longitude, LocationTypes locationType) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationType = locationType;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;	
	}
	public void setId(int id){
		this.id=id;
	}
	
	@Column(name="name")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Column(name="latitude")
	public BigDecimal getLatitude(){
		return latitude;
	}

	public void setLatitude(BigDecimal latitude){
		this.latitude = latitude;
	}
	
	@Column(name="longitude")
	public BigDecimal getLongitude(){
		return longitude;
	}
	
	public void setLongitude(BigDecimal longitude){
		this.longitude = longitude;
	}

	@OneToMany(mappedBy="location",cascade = { javax.persistence.CascadeType.ALL }, orphanRemoval = true,fetch=FetchType.EAGER)
		public Set<LocationInfo> getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(Set<LocationInfo> locationInfo){
		this.locationInfo = locationInfo;
	}
	
	@ManyToOne  
	@JoinColumn(name = "hive")	
	public Hive getHive() {
		return hive;
	}

	public void setHive(Hive hive) {
		this.hive = hive;
	}


	@Column(name="location_type") 
	@Enumerated(EnumType.STRING) 
	public LocationTypes getLocationType() { 
	    return locationType;
	}

	public void setLocationType(LocationTypes locationType){
		this.locationType = locationType;
	}
}
