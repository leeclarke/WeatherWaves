package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;

public class Radar extends DragonflyDomain
{
	public static final String root = "radar";
	
	@Exportable(xmlName = "image_url" )
	private String image_url;
	
	@Exportable(xmlName = "url")
	private String url;

	public Radar()
	{
		super();
	}

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

}
