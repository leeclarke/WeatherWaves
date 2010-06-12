package org.dragonfly.wunderground.service;

import org.dragonfly.wunderground.domain.DragonflyDomain;

public class Station extends DragonflyDomain
{
	enum StationType {AIRPORT, PWS};
	
	@Exportable(xmlName = "type", jsonName = "type")
	private StationType type;
	
	@Exportable(xmlName = "city")
	private String city;
	
	@Exportable(xmlName = "state")
	private String state;
	
	@Exportable(xmlName = "country")
	private String country;
	
	@Exportable(xmlName = "icao")
	private String icao;
	
	@Exportable(xmlName = "lat")
	private String lat;
	
	@Exportable(xmlName = "lon")
	private String lon;
	
	@Exportable(xmlName = "neighborhood")
	private String neighborhood;
	
	@Exportable(xmlName = "id")
	private String id;
	
	@Exportable(xmlName = "distance_km")
	private String distance_km;
	
	@Exportable(xmlName = "distance_mi")
	private String distance_mi;
}
