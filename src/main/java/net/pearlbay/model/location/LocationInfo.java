package net.pearlbay.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ddr_location_info")
public class LocationInfo {


	private Location location;
	private int location_info_Id;

	private String name;
	private String content;
	private String type;


	@ManyToOne  
	@JoinColumn(name = "location_id")	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Id  
	@GeneratedValue  
	@Column(name = "location_info_id")  
	public int getLocation_info_Id() {
		return location_info_Id;
	}

	public void setLocation_info_Id(int location_info_Id) {
		this.location_info_Id = location_info_Id;
	}

	@Column(name="name")
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}



