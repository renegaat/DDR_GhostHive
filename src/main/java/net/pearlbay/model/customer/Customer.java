package net.pearlbay.model.customer;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

import net.pearlbay.model.ghost.Ghost;

@Entity
@Table(name="ddr_customer")
public class Customer {
	private Integer id;
	private String name;
	private List<Ghost>ghosts;
	
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
	
	@OneToMany(targetEntity=Ghost.class,mappedBy ="customer",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonManagedReference
	public List<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(List<Ghost> ghosts) {
		this.ghosts = ghosts;
	}
}
