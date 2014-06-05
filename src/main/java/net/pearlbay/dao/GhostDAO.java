package net.pearlbay.dao;

import java.util.List;

import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.location.Location;

public interface GhostDAO {
	public void addGhost(Ghost ghost);
    public List<Ghost> listGhost();
    public void removeGhost(Integer id);	
    public Ghost getGhost(Integer id);
	void mergeGhost(Ghost ghost);	
}
