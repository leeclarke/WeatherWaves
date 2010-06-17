package org.dragonfly.wunderground.domain;

import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.util.Exportable;
import org.dragonfly.wunderground.util.ExportableAttribute;

public class Forecast extends DragonflyDomain
{
	public static final String root = "forecast";
	
	public static final List<String> children = new ArrayList<String>();
	static{
		children.add("txt_forecast");
		children.add("simpleforecast");
	}
	
	@ExportableAttribute(tag = "termsofservice", xmlName = "link", jsonName = "termsofservice")
	private String termsofservice; //att
	
	@Exportable(jsonName="shortForecast")
	private List<ForecastDay> txt_forecast;
	
	@Exportable(xmlName="date")
	private String date; //from txt_forecast
	
	@Exportable(jsonName="detailedForecast")
	private List<ForecastDay> simpleforecast;
	
	@Exportable(jsonName="moonPhase")
	private MoonPhase moon_phase;

	public String getTermsofservice()
	{
		return termsofservice;
	}
	public void setTermsofservice(String termsofservice)
	{
		this.termsofservice = termsofservice;
	}
	public List<ForecastDay> getTxt_forecast()
	{
		return txt_forecast;
	}
	public void setTxt_forecast(List<ForecastDay> txt_forecast)
	{
		this.txt_forecast = txt_forecast;
	}
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public List<ForecastDay> getSimpleforecast()
	{
		return simpleforecast;
	}
	public void setSimpleforecast(List<ForecastDay> simpleforecast)
	{
		this.simpleforecast = simpleforecast;
	}
	public MoonPhase getMoon_phase()
	{
		return moon_phase;
	}
	public void setMoon_phase(MoonPhase moon_phase)
	{
		this.moon_phase = moon_phase;
	}
}