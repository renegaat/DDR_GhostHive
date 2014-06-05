package net.pearlbay.model.ghost;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.pearlbay.localisation.google.direction.Route;
import net.pearlbay.model.customer.Customer;
import net.pearlbay.model.event.GhostEvent;
import net.pearlbay.model.hive.Hive;
import net.pearlbay.model.location.Location;


@Entity
@Table(name="ddr_ghost")
public class Ghost {
	private int id;
	private String name;
	private Hive hive;
	private Customer customer;
	private Location locationTo;
	private Location locationFrom;
	private Location locationAt;
	private GhostBody ghostBody;
	private GhostPersonality ghostPersonality;
	private List<GhostEvent>ghostEvents;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private Integer neuralNet;


	//non hibernate properties
	
	private Route route;
	int stepIndex;

	
	
	@Transient
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
	@Transient
	public int getStepIndex() {
		return stepIndex;
	}
	public void setStepIndex(int stepIndex) {
		this.stepIndex = stepIndex;
	}


	//

	@Id
	@GeneratedValue
	public int getId() {
		return id;	
	}
	public void setId(int id){
		this.id=id;
	}

	@Column(name="longitude")
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="latitude")
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
		

	@Column(name="name")
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="hive")
	
	public Hive getHive() {
		return hive;
	}

	public void setHive(Hive hive) {
		this.hive = hive;
	}

	@ManyToOne
	@JoinColumn(name="customer")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	@JoinColumn(name="locationTo")
	public Location getLocationTo() {
		return locationTo;
	}
	public void setLocationTo(Location locationTo) {
		this.locationTo = locationTo;
	}

	@ManyToOne
	@JoinColumn(name="locationFrom")
	public Location getLocationFrom() {
		return locationFrom;
	}
	public void setLocationFrom(Location locationFrom) {
		this.locationFrom = locationFrom;
	}	

	@ManyToOne
	@JoinColumn(name="locationAt")
	public Location getLocationAt() {
		return locationAt;
	}
	public void setLocationAt(Location locationAt) {
		this.locationAt = locationAt;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "ghost", cascade = CascadeType.ALL)
	public GhostBody getGhostBody() {
		return this.ghostBody;
	}

	public void setGhostBody(GhostBody ghostBody) {
		this.ghostBody = ghostBody;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "ghost", cascade = CascadeType.ALL)
	public GhostPersonality getGhostPersonality() {
		return ghostPersonality;
	}
	public void setGhostPersonality(GhostPersonality ghostPersonality) {
		this.ghostPersonality = ghostPersonality;
	}

	@OneToMany(targetEntity=GhostEvent.class,mappedBy="ghost",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<GhostEvent> getGhostEvents() {
		return ghostEvents;
	}

	public void setGhostEvents(List<GhostEvent> ghostEvent) {
		this.ghostEvents = ghostEvent;
	}
	
	
	@Column(name="neuralNet")
	public Integer getNeuralNet() {
		return neuralNet;
	}
	public void setNeuralNet(Integer neuralNet) {
		this.neuralNet = neuralNet;
	}
	
}