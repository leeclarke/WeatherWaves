package org.dragonfly.wunderground.domain;

import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.service.Exportable;

public class ObservationLocation extends DragonflyDomain
{
	public static final List<String> root = new ArrayList<String>();
	static {
		root.add("display_location");
		root.add("observation_location");
	}
	
	@Exportable(jsonName = "full",  xmlName = "fullName")
	private String fullName;
	
	@Exportable(jsonName = "city")
	private String city;
	
	@Exportable(jsonName = "state")
	private String state;
	
	@Exportable(jsonName = "state_name",  xmlName = "stateName")
	private String stateName;
	
	@Exportable(jsonName = "country")
	private String country;
	
	@Exportable(jsonName = "country_iso3166",  xmlName = "countryIso")
	private String countryIso;
	
	@Exportable(jsonName = "zip")
	private String zip;
	
	@Exportable(jsonName = "latitude")
	private String latitude;
	
	@Exportable(jsonName = "longitude")
	private String longitude;
	
	@Exportable(jsonName = "elevation")
	private String elevation;

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

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCountryIso()
	{
		return countryIso;
	}

	public void setCountryIso(String countryIso)
	{
		this.countryIso = countryIso;
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
	
}
