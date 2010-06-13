package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.service.Exportable;

/**
 * Station TO is contained by a Location.
 * 
 * @author leeclarke
 */
public class Station extends DragonflyDomain implements Comparable<Station>
{
	public static final String root = "station";
	
	public enum StationType {
		AIRPORT("airport"), PWS("pws");
		
		private String name;
		
		StationType(String name)
		{
			this.name = name;
		}
		
		public String getName()
		{
			return this.name;
		}
	};

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

	public StationType getType()
	{
		return type;
	}

	public void setType(StationType type)
	{
		this.type = type;
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

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getIcao()
	{
		return icao;
	}

	public void setIcao(String icao)
	{
		this.icao = icao;
	}

	public String getLat()
	{
		return lat;
	}

	public void setLat(String lat)
	{
		this.lat = lat;
	}

	public String getLon()
	{
		return lon;
	}

	public void setLon(String lon)
	{
		this.lon = lon;
	}

	public String getNeighborhood()
	{
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood)
	{
		this.neighborhood = neighborhood;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDistance_km()
	{
		return distance_km;
	}

	public void setDistance_km(String distanceKm)
	{
		distance_km = distanceKm;
	}

	public String getDistance_mi()
	{
		return distance_mi;
	}

	public void setDistance_mi(String distanceMi)
	{
		distance_mi = distanceMi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dragonfly.wunderground.domain.DragonflyDomain#toString()
	 */
	@Override
	public String toString()
	{
		return BeanUtil.beanToString(this);
	}

	@Override
	public int compareTo(Station o)
	{
		//TODO: expand compare to include state/city
		return this.type.name().compareTo(o.type.name());
	}
}
