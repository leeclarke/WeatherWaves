package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.service.Exportable;

public class Radar extends DragonflyDomain implements Comparable<Location>
{
	public static final String root = "radar";
	
	@Exportable(xmlName = "image_url" )
	private String image_url;
	
	@Exportable(xmlName = "url")
	private String url;


	public String getImage_url()
	{
		return image_url;
	}


	public void setImage_url(String image_url)
	{
		this.image_url = image_url;
	}


	public String getUrl()
	{
		return url;
	}


	public void setUrl(String url)
	{
		this.url = url;
	}


	@Override
	public int compareTo(Location o)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
