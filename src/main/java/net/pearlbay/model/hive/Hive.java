package net.pearlbay.model.hive;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.helper.HiveColor;
import net.pearlbay.model.location.Location;

@Entity
@Table(name="ddr_hive")
public class Hive {
	
	
	private Integer id;
	private String name;
	private Integer energy;
	private HiveColor hiveColor;
	private String texture;
	private Location locationAt;
	private Integer neuralNet;

	private List<Ghost>ghosts;
	private Set<Location>ownedLocations;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;	
	}
	public void setId(Integer id){
		this.id=id;
	}
	
	@Column(name="name")
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Column(name="energy")
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	
	@OneToOne
	@JoinColumn(name="locationAt")
	public Location getLocationAt() {
		return locationAt;
	}
	public void setLocationAt(Location locationAt) {
		this.locationAt = locationAt;
	}


	@OneToMany(mappedBy="hive",cascade = { javax.persistence.CascadeType.ALL }, orphanRemoval = true,fetch=FetchType.EAGER)
	public Set<Location> getOwnedLocations() {
		return ownedLocations;
	}
	public void setOwnedLocations(Set<Location> ownedLocations) {
		this.ownedLocations = ownedLocations;
	}

	
	@Column(name="hiveColor") 
	@Enumerated(EnumType.STRING) 
	public HiveColor getHiveColor() { 
	    return hiveColor;
	}

	public void setHiveColor(HiveColor hiveColor){
		this.hiveColor = hiveColor;
	}	
	
	@Column(name="texture")
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}

	@Column(name="neuralNet")
	public Integer getNeuralNet() {
		return neuralNet;
	}
	public void setNeuralNet(Integer neuralNet) {
		this.neuralNet = neuralNet;
	}
	
	@OneToMany(targetEntity=Ghost.class,mappedBy="hive",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Ghost> getGhosts() {
		return ghosts;
	}
	public void setGhosts(List<Ghost> ghosts) {
		this.ghosts = ghosts;
	}
	
}
