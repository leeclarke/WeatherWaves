package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;

public class WuImage extends DragonflyDomain
{
	public static final String root = "image";
	
	@Exportable(jsonName = "url")
	private String url;
	
	@Exportable(jsonName = "title")
	private String title;
	
	@Exportable(jsonName = "link")
	private String link;
	
	public WuImage()
	{
		super();
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}
}
