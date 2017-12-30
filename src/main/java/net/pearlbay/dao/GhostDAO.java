package net.pearlbay.dao;

import java.util.List;

import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.location.Location;

public interface GhostDAO {
	void addGhost(Ghost ghost);
    List<Ghost> listGhosts();
    void removeGhost(Integer id);
    Ghost getGhost(Integer id);
	void mergeGhost(Ghost ghost);	
}
