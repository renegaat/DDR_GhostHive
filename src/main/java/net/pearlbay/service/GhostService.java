package net.pearlbay.service;

import java.util.List;

import net.pearlbay.model.ghost.Ghost;

public interface GhostService {
	void addGhost(Ghost ghost);
    List<Ghost> listGhost();
    void removeGhost(Integer id);
    Ghost retrieveGhost(Integer id);
    void mergeGhost(Ghost ghost);
 
}
