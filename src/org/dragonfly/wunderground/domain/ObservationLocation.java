package org.dragonfly.wunderground.domain;

import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.service.Exportable;

public class ObservationLocation extends DragonflyDomain
{
	public static final List<String> root = new ArrayList<String>();
	public static final String DISPLAY_LOC = "display_location";
	public static final String OBSERVATION_LOC = "observation_location";
	static {
		root.add(DISPLAY_LOC);
		root.add(OBSERVATION_LOC);
	}
	
	@Exportable(jsonName = "full",  xmlName = "fullName")
	private String fullName;
	
	@Exportable(xmlName = "city")
	private String city;
	
	@Exportable(xmlName = "state")
	private String state;
	
	@Exportable(xmlName = "state_name",  jsonName = "stateName")
	private String state_name;
	
	@Exportable(xmlName = "country")
	private String country;
	
	@Exportable(xmlName = "country_iso3166",  jsonName = "countryIso")
	private String country_iso3166;
	
	@Exportable(xmlName = "zip")
	private String zip;
	
	@Exportable(xmlName = "latitude")
	private String latitude;
	
	@Exportable(xmlName = "longitude")
	private String longitude;
	
	@Exportable(xmlName = "elevation")
	private String elevation;

//	@Exportable(xmlName = "" )
	private String locType;

	
	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getState_name()
	{
		return state_name;
	}

	public void setState_name(String stateName)
	{
		this.state_name = stateName;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCountry_iso3166()
	{
		return country_iso3166;
	}

	public void setCountry_iso3166(String countryIso)
	{
		this.country_iso3166 = countryIso;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getElevation()
	{
		return elevation;
	}

	public void setElevation(String elevation)
	{
		this.elevation = elevation;
	}


	public String getLocType()
	{
		return locType;
	}

	public void setLocType(String locType)
	{
		this.locType = locType;
	}
	
	
}
