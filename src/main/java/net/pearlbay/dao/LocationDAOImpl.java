package net.pearlbay.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.pearlbay.localisation.google.places.GooglePlacesService;
import net.pearlbay.model.location.Location;


@Transactional
@Repository
public class LocationDAOImpl implements LocationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired 
	private GooglePlacesService googlePlacesService;
	
	public void addRichLocation(Location location) {
		location.setLocationInfo(googlePlacesService.getPlacesByLocation("", location));
		sessionFactory.getCurrentSession().saveOrUpdate(location);
	}

	public void addLocation(Location Location) {
		sessionFactory.getCurrentSession().save(Location);
	}
	
	public List<Location> listLocation() {
        return sessionFactory.getCurrentSession().createQuery("from Location").list();
	}
	
	public void removeLocation(Integer id) {		
		Location myLocation =  (Location)sessionFactory.getCurrentSession().load(Location.class,id);

		if(myLocation!=null){
			sessionFactory.getCurrentSession().delete(myLocation);
		}	
	}
	
	public Location getLocation(Integer id){
		return (Location)sessionFactory.getCurrentSession().load(Location.class,id);
	}

 	
	@SuppressWarnings("unchecked")
	public List<Location> getCloseLocations(Location location) {
	
		StringBuffer queryString =  new StringBuffer();	
		queryString.append("SELECT ((ACOS(SIN(:latitude * PI() / 180) * SIN(latitude * PI() / 180) + COS(:latitude * PI() / 180) * COS(latitude * PI() / 180) * COS((:longitude - longitude) *");
		queryString.append("PI() / 180)) * 180 / PI()) * 60 ) AS distance, id, name, latitude, longitude, location_type, hive FROM ddr_location HAVING distance <='0.2' ORDER BY distance ASC");
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(queryString.toString());
		query.setParameter("latitude",location.getLatitude());
		query.setParameter("longitude",location.getLongitude());
		query.addEntity(Location.class);
	
		return query.list();
	}
}
	