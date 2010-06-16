package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;
/**
 * Part of the Location object webcam collection.
 * 
 * @author lclarke
 */
public class Cam extends DragonflyDomain implements Comparable<Cam>
{
	public static final String root = "cam";
	public static final String parent = "webcam";
	
	public Cam()
	{
		super();
	}
	
	@Exportable(xmlName = "handle")
	public String handle;
	
	@Exportable(xmlName = "camid")
	public String camid;
	
	@Exportable(xmlName = "camindex")
	public String camindex;
	
	@Exportable(xmlName = "type")
	public String type;

	@Exportable(xmlName = "assoc_station_id", jsonName = "assocStationId")
	public String assocStationId;

	@Exportable(xmlName = "link")
	public String link;
	
	@Exportable(xmlName = "linktext")
	public String linktext;
	
	@Exportable(xmlName = "cameratype")
	public String cameratype;
	
	@Exportable(xmlName = "organization")
	public String organization;
	
	@Exportable(xmlName = "neighborhood")
	public String neighborhood;
	
	@Exportable(xmlName = "address")
	public String address;
	
	@Exportable(xmlName = "zip")
	public String zip;
	
	@Exportable(xmlName = "city")
	public String city;
	
	@Exportable(xmlName = "state")
	public String state;
	
	@Exportable(xmlName = "country")
	public String country;
	
	@Exportable(xmlName = "tzname")
	public String tzname;
	
	@Exportable(xmlName = "hexant")
	public String hexant;
	
	@Exportable(xmlName = "hexid")
	public String hexid;
	
	@Exportable(xmlName = "lat")
	public String lat;
	
	@Exportable(xmlName = "lon")
	public String lon;
	
	@Exportable(xmlName = "updated")
	public String updated;
	
	@Exportable(xmlName = "downloaded")
	public String downloaded;
	
	@Exportable(xmlName = "hash")
	public String hash;

	@Exportable(xmlName = "isrecent")
	public String isrecent;

	@Exportable(xmlName = "frequency")
	public String frequency;

	@Exportable(xmlName = "CURRENTIMAGEURL", jsonName = "currentImageUrl")
	public String currentImageUrl;

	@Exportable(xmlName = "WIDGETCURRENTIMAGEURL", jsonName = "widgitCurrentImageUrl")
	public String widgitCurrentImageUrl;

	@Exportable(xmlName = "CAMURL", jsonName = "camUrl")
	public String camUrl;

	@Override
	public int compareTo(Cam o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public String getHandle()
	{
		return handle;
	}

	public void setHandle(String handle)
	{
		this.handle = handle;
	}

	public String getCamid()
	{
		return camid;
	}

	public void setCamid(String camid)
	{
		this.camid = camid;
	}

	public String getCamindex()
	{
		return camindex;
	}

	public void setCamindex(String camindex)
	{
		this.camindex = camindex;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getAssocStationId()
	{
		return assocStationId;
	}

	public void setAssocStationId(String assocStationId)
	{
		this.assocStationId = assocStationId;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getLinktext()
	{
		return linktext;
	}

	public void setLinktext(String linktext)
	{
		this.linktext = linktext;
	}

	public String getCameratype()
	{
		return cameratype;
	}

	public void setCameratype(String cameratype)
	{
		this.cameratype = cameratype;
	}

	public String getOrganization()
	{
		return organization;
	}

	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

	public String getNeighborhood()
	{
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood)
	{
		this.neighborhood = neighborhood;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
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

	public String getTzname()
	{
		return tzname;
	}

	public void setTzname(String tzname)
	{
		this.tzname = tzname;
	}

	public String getHexant()
	{
		return hexant;
	}

	public void setHexant(String hexant)
	{
		this.hexant = hexant;
	}

	public String getHexid()
	{
		return hexid;
	}

	public void setHexid(String hexid)
	{
		this.hexid = hexid;
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

	public String getUpdated()
	{
		return updated;
	}

	public void setUpdated(String updated)
	{
		this.updated = updated;
	}

	public String getDownloaded()
	{
		return downloaded;
	}

	public void setDownloaded(String downloaded)
	{
		this.downloaded = downloaded;
	}

	public String getHash()
	{
		return hash;
	}

	public void setHash(String hash)
	{
		this.hash = hash;
	}

	public String getIsrecent()
	{
		return isrecent;
	}

	public void setIsrecent(String isrecent)
	{
		this.isrecent = isrecent;
	}

	public String getFrequency()
	{
		return frequency;
	}

	public void setFrequency(String frequency)
	{
		this.frequency = frequency;
	}

	public String getCurrentImageUrl()
	{
		return currentImageUrl;
	}

	public void setCurrentImageUrl(String currentImageUrl)
	{
		this.currentImageUrl = currentImageUrl;
	}

	public String getWidgitCurrentImageUrl()
	{
		return widgitCurrentImageUrl;
	}

	public void setWidgitCurrentImageUrl(String widgitCurrentImageUrl)
	{
		this.widgitCurrentImageUrl = widgitCurrentImageUrl;
	}

	public String getCamUrl()
	{
		return camUrl;
	}

	public void setCamUrl(String camUrl)
	{
		this.camUrl = camUrl;
	}

}
