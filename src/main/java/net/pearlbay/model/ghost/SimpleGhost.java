package net.pearlbay.model.ghost;

import java.math.BigDecimal;

import net.pearlbay.model.helper.HiveColor;

public class SimpleGhost {
	@SuppressWarnings("unused")
	public int id;
	@SuppressWarnings("unused")
	public String name;
	@SuppressWarnings("unused")
	public BigDecimal longitude;
	@SuppressWarnings("unused")
	public BigDecimal latitude;
	@SuppressWarnings("unused")
	public int destinationId;
	@SuppressWarnings("unused")
	public HiveColor hiveColor;

	public SimpleGhost(Ghost referenceGhost) {
		this.id = referenceGhost.getId();
		this.name = referenceGhost.getName();
		this.longitude = referenceGhost.getLongitude();
		this.latitude = referenceGhost.getLatitude();
		this.destinationId = referenceGhost.getLocationTo().getId();
		this.hiveColor = referenceGhost.getHive().getHiveColor();
	}
}
