package net.pearlbay.model.hive;

import java.math.BigDecimal;

import net.pearlbay.model.helper.HiveColor;

public class SimpleHive {

	@SuppressWarnings("unused")
	public int id;
	@SuppressWarnings("unused")
	public String name;
	@SuppressWarnings("unused")
	public BigDecimal longitude;
	@SuppressWarnings("unused")
	public BigDecimal latitude;
	@SuppressWarnings("unused")
	public HiveColor hiveColor;
	@SuppressWarnings("unused")
	public String teture;
	
	public SimpleHive(Hive referenceHive){
		this.id = referenceHive.getId();
		this.name= referenceHive.getName();
		this.longitude = referenceHive.getLocationAt().getLongitude();
		this.latitude = referenceHive.getLocationAt().getLatitude();
		this.hiveColor = referenceHive.getHiveColor();
		this.teture = referenceHive.getTexture();
	}
}
