package org.dragonfly.wunderground.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.service.Exportable;

/**
 * POJO, represents the Value Object
 * 
 */
public class Location implements Comparable<Location>
{
	// TODO: Pull up this functionality into abstract.
	// TODO: Add Non-runtime annotation to generate the getter/setter
	// TODO: Finish adding the values. maybe add a root to annotation?
	// TODO: Need to handle non-String types when calling setters!
	private static final Logger logger = Logger.getLogger(Location.class);
	public static final String root = "location";
	public static final List<String> fields;

	static
	{
		fields = new ArrayList<String>();
		Field[] rFields = Location.class.getDeclaredFields();
		for (Field field : rFields)
		{
			Annotation[] annotations = field.getDeclaredAnnotations();

			for (Annotation annotation : annotations)
			{
				if (annotation instanceof Exportable)
				{
					// Add to list of exposed fields, helps the setter know what
					// fields are being imported from xml.
					fields.add(field.getName());
					Exportable myAnnotation = (Exportable) annotation;
					logger.debug("field [" + field.getName() + "] \n\txml:: " + myAnnotation.xmlName() + "\n\tjson:: "
							+ myAnnotation.jsonName());
				}
			}
		}

	}

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

	public Location()
	{
		super();
	}

	public Location(String locType)
	{
		super();
		this.locType = locType;
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

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("Location: Type=");
		sb.append("").append(locType).append(" [");
		sb.append("Country: ").append(country).append('\n');
		sb.append("State: ").append(state).append('\n').append("City: ").append(city);
		sb.append("\n tz_short=").append(this.tz_short);
		sb.append("]");
		return sb.toString();
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

	public Object getValue(String name)
	{
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields)
		{
			Exportable annotation = f.getAnnotation(Exportable.class);
			if (annotation == null)
				continue;

			f.setAccessible(true); // Make private fields accessible.
		}
		return null;
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

	public void setLat(Double lat)
	{
		this.lat = lat;
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

	public void setLon(Double lon)
	{
		this.lon = lon;
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

}
