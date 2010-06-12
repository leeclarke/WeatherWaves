package org.dragonfly.wunderground.domain;
//TODO: different Location searchs have diff tags!
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.service.Exportable;

/**
 * POJO, represents the Data Object
 * 
 */
public class Location extends DragonflyDomain implements Comparable<Location>
{
	public static final String root = "location";

	static SimpleDateFormat FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");

	@Exportable(xmlName = "termsofservice", jsonName = "termsofservice")
	private String termsofservice;

	@Exportable(xmlName = "lat", jsonName = "latitude")
	private Double lat;

	@Exportable(xmlName = "lon", jsonName = "Longitude")
	private Double lon;

	@Exportable(xmlName = "lon", jsonName = "Longitude")
	private String wmo;

	@Exportable(xmlName = "magic")
	private String magic;

	@Exportable(xmlName = "requesturl")
	private String requesturl;

	@Exportable(xmlName = "wuiurl")
	private String wuiurl;

	@Exportable(xmlName = "country", jsonName = "country")
	private String country;

	@Exportable(xmlName = "state", jsonName = "state")
	private String state;

	@Exportable(xmlName = "city", jsonName = "city")
	private String city;

	@Exportable(xmlName = "tz_short", jsonName = "timezone-short")
	private String tz_short;

	@Exportable(xmlName = "zip", jsonName = "zip-code")
	private String zip;

	@Exportable(xmlName = "tz_unix", jsonName = "timezone-unix")
	private String tz_unix;

	@Exportable(xmlName = "type", jsonName = "location-type")
	private String locType;

	@Exportable(xmlName = "radar", jsonName = "radar")
	private Radar radar = new Radar();
	
	@Exportable(xmlName = "webcams", jsonName = "webCams")
	private List<Cam> webCams = new ArrayList<Cam>();
	
	@Exportable(xmlName = "stations")
	private List<Station> stations = new ArrayList<Station>();
	
	public Location()
	{
		super();
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country.trim();
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setDescription(String description)
	{
		this.city = description.trim();
	}

	public int compareTo(Location another)
	{
		if (another == null)
			return 1;
		// sort descending, most recent first
		return another.tz_short.compareTo(tz_short);
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getTz_short()
	{
		return tz_short;
	}

	public void setTz_short(String tz_short)
	{
		this.tz_short = tz_short;
	}

	public String getTz_unix()
	{
		return tz_unix;
	}

	public void setTz_unix(String tz_unix)
	{
		this.tz_unix = tz_unix;
	}

	public String getLocType()
	{
		return locType;
	}

	public void setLocType(String locType)
	{
		this.locType = locType;
	}

	public String getTermsofservice()
	{
		return termsofservice;
	}

	public void setTermsofservice(String termsofservice)
	{
		this.termsofservice = termsofservice;
	}

	public Double getLat()
	{
		return lat;
	}

	public void setLat(String lat)
	{
		try
		{
			this.lat = new Double(lat);
		} catch (Exception e)
		{
		}

	}

	public Double getLon()
	{
		return lon;
	}


	/**
	 * Doesnt protect you from setting an invalid String value!
	 * 
	 * @param lon
	 */
	public void setLon(String lon)
	{
		try
		{
			this.lon = new Double(lon);
		} catch (Exception e)
		{
		}
	}

	public String getWmo()
	{
		return wmo;
	}

	public void setWmo(String wmo)
	{
		this.wmo = wmo;
	}

	public String getMagic()
	{
		return magic;
	}

	public void setMagic(String magic)
	{
		this.magic = magic;
	}

	public String getRequesturl()
	{
		return requesturl;
	}

	public void setRequesturl(String requesturl)
	{
		this.requesturl = requesturl;
	}

	public String getWuiurl()
	{
		return wuiurl;
	}

	public void setWuiurl(String wuiurl)
	{
		this.wuiurl = wuiurl;
	}

	public Radar getRadar()
	{
		return radar;
	}

	public void setRadar(Radar radar)
	{
		this.radar = radar;
	}

	public List<Cam> getWebCams()
	{
		return webCams;
	}

	public void setWebCams(List<Cam> webCams)
	{
		this.webCams = webCams;
	}

	public List<Station> getStations()
	{
		return stations;
	}

	public void setStations(List<Station> stations)
	{
		this.stations = stations;
	}
}
// public String getDate()
// {
// return FORMATTER.format(this.date);
// }

// public void setDate(String date)
// {
// // pad the date if necessary
// while (!date.endsWith("00"))
// {
// date += "0";
// }
// try
// {
// this.date = FORMATTER.parse(date.trim());
// }
// catch (ParseException e)
// {
// throw new RuntimeException(e);
// }
// }