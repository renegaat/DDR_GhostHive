package net.pearlbay.service.ghosthiveconnector;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import net.pearlbay.model.ghost.Ghost;
import net.pearlbay.model.ghost.SimpleGhost;
import net.pearlbay.model.hive.SimpleHive;
import net.pearlbay.model.location.SimpleLocation;

public interface GhostHiveService {
	public List<SimpleGhost>  getAllSimpleGhosts();
	public SimpleGhost  getGhostById(int id);
	public List<SimpleHive> getAllSimpleHives();
	public List<SimpleLocation> getLocations();
	public Ghost getComplexGhostById(int id);
	
}
