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

import net.pearlbay.model.helper.BodyStates;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ddr_ghost_body")
public class GhostBody {

	private Ghost ghost;
	private int ghostId;
	private BodyStates energy;
	private BodyStates live;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "ghost"))
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
	
	@Column(name="energy") 
	@Enumerated(EnumType.STRING) 
	public BodyStates getEnergy() {
		return energy;
	}
	public void setEnergy(BodyStates energy) {
		this.energy = energy;
	}

	
	@Column(name="live") 
	@Enumerated(EnumType.STRING) 
	public BodyStates getLive() {
		return live;
	}
	public void setLive(BodyStates live) {
		this.live = live;
	}
}
