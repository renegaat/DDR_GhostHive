package net.pearlbay.service;

import java.util.List;

import net.pearlbay.model.ghost.Ghost;

public interface GhostService {
	public void addGhost(Ghost ghost);
    public List<Ghost> listGhost();
    public void removeGhost(Integer id);	
    public Ghost getGhost(Integer id);	
    public void mergeGhost(Ghost ghost);	
 
}
