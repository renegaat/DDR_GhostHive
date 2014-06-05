package net.pearlbay.model.ghost;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

public class GhostHistory {

	private Ghost ghost;
	private int ghostId;

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
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Ghost getGhost() {
		return ghost;
	}
	public void setGhost(Ghost ghost) {
		this.ghost = ghost;
	}
}
