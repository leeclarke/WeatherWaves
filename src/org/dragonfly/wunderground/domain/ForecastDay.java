package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;

public class ForecastDay  extends DragonflyDomain
{
	public static final String root = "forecastday";
	
	@Exportable(xmlName="type")
	private String type;
	
	@Exportable(xmlName="title")
	private String title;
	
	@Exportable(xmlName="period")
	private String period;
	
	@Exportable(xmlName="conditions")
	private String conditions;
	
	@Exportable(xmlName="skyicon")
	private String skyicon;
	
	@Exportable(xmlName="pop")
	private String pop;
	
	@Exportable(xmlName="icon")
	private String icon; // the only indicator of sky condition.
	
	@Exportable(xmlName="fcttext")
	private String fcttext;
	
	@Exportable(xmlName="date_pretty")
	private String date_pretty;
	
	@Exportable(xmlName="high_fahrenheit")
	private String high_fahrenheit;
	
	@Exportable(xmlName="high_celsius")
	private String high_celsius;

	@Exportable(xmlName="low_fahrenheit")
	private String low_fahrenheit;
	
	@Exportable(xmlName="low_celsius")
	private String low_celsius;
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getPeriod()
	{
		return period;
	}
	public void setPeriod(String period)
	{
		this.period = period;
	}
	public String getConditions()
	{
		return conditions;
	}
	public void setConditions(String conditions)
	{
		this.conditions = conditions;
	}
	public String getSkyicon()
	{
		return skyicon;
	}
	public void setSkyicon(String skyicon)
	{
		this.skyicon = skyicon;
	}
	public String getPop()
	{
		return pop;
	}
	public void setPop(String pop)
	{
		this.pop = pop;
	}
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	public String getFcttext()
	{
		return fcttext;
	}
	public void setFcttext(String fcttext)
	{
		this.fcttext = fcttext;
	}
	public String getDate_pretty()
	{
		return date_pretty;
	}
	public void setDate_pretty(String date_pretty)
	{
		this.date_pretty = date_pretty;
	}
	public String getHigh_fahrenheit()
	{
		return high_fahrenheit;
	}
	public void setHigh_fahrenheit(String high_fahrenheit)
	{
		this.high_fahrenheit = high_fahrenheit;
	}
	public String getHigh_celsius()
	{
		return high_celsius;
	}
	public void setHigh_celsius(String high_celsius)
	{
		this.high_celsius = high_celsius;
	}
	public String getLow_fahrenheit()
	{
		return low_fahrenheit;
	}
	public void setLow_fahrenheit(String low_fahrenheit)
	{
		this.low_fahrenheit = low_fahrenheit;
	}
	public String getLow_celsius()
	{
		return low_celsius;
	}
	public void setLow_celsius(String low_celsius)
	{
		this.low_celsius = low_celsius;
	}

	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
}
