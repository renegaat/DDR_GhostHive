package net.pearlbay.model.event;

import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.pearlbay.model.ghost.Ghost;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ddr_ghost_event")
public class GhostEvent {
	
	private Ghost ghost;
	private int ghostId;
	private String description;
	private Date dateTime; 
	private GhostEventTypes ghostEventType;
	
	
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ghost_id",insertable = false, updatable = false)
	@JsonIgnore
	public Ghost getGhost() {
		return ghost;
	}
	public void setGhost(Ghost ghost) {
		this.ghost = ghost;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateTime() {
		return dateTime;
	}	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	
	@Column(name="type") 
	@Enumerated(EnumType.STRING) 	
	public GhostEventTypes getGhostEventType() {
		return ghostEventType;
	}
	public void setGhostEventType(GhostEventTypes ghostEventType) {
		this.ghostEventType = ghostEventType;
	}
}
