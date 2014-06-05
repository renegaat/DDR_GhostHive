package net.pearlbay.model.ghost;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import net.pearlbay.model.helper.PersonalityStates;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="ddr_ghost_personality")
public class GhostPersonality {

	
	private Ghost ghost;
	private int ghostId;

	private PersonalityStates intelligence;
	private PersonalityStates aggression;
	private PersonalityStates curiosity;
		
	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "ghost"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ghost_id", unique = true, nullable = false)

	public int getGhostId() {
		return ghostId;
	}
	public void setGhostId(int ghost_id) {
		this.ghostId = ghost_id;
	}
	
	@OneToOne
	@PrimaryKeyJoinColumn
	@JsonBackReference
	public Ghost getGhost() {
		return ghost;
	}
	public void setGhost(Ghost ghost) {
		this.ghost = ghost;
	}

	@Column(name="intelligence") 
	@Enumerated(EnumType.STRING) 	
	public PersonalityStates getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(PersonalityStates intelligence) {
		this.intelligence = intelligence;
	}
	
	@Column(name="aggression") 
	@Enumerated(EnumType.STRING) 
	public PersonalityStates getAggression() {
		return aggression;
	}
	public void setAggression(PersonalityStates aggression) {
		this.aggression = aggression;
	}
	
	@Column(name="curiosity") 
	@Enumerated(EnumType.STRING) 
	public PersonalityStates getCuriosity() {
		return curiosity;
	}
	public void setCuriosity(PersonalityStates curiosity) {
		this.curiosity = curiosity;
	}
}
