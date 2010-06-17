package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;

public class MoonPhase extends DragonflyDomain
{
	public static final String root = "moon_phase";
	
	@Exportable(xmlName="percentIlluminated")
	private String percentIlluminated;
	
	@Exportable(xmlName="ageOfMoon")
	private String ageOfMoon;

	@Exportable(xmlName="current_time_hour")
	private String current_time_hour;
	
	@Exportable(xmlName="current_time_minute")
	private String current_time_minute;
	
	@Exportable(xmlName="sunset_hour")
	private String sunset_hour;

	@Exportable(xmlName="sunset_minute")
	private String sunset_minute;

	@Exportable(xmlName="sunrise_hour")
	private String sunrise_hour;
	
	@Exportable(xmlName="sunrise_minute")
	private String sunrise_minute;
	
	
	public String getPercentIlluminated()
	{
		return percentIlluminated;
	}

	public void setPercentIlluminated(String percentIlluminated)
	{
		this.percentIlluminated = percentIlluminated;
	}

	public String getAgeOfMoon()
	{
		return ageOfMoon;
	}

	public void setAgeOfMoon(String ageOfMoon)
	{
		this.ageOfMoon = ageOfMoon;
	}

	public String getCurrent_time_hour()
	{
		return current_time_hour;
	}

	public void setCurrent_time_hour(String currentTimeHour)
	{
		current_time_hour = currentTimeHour;
	}

	public String getCurrent_time_minute()
	{
		return current_time_minute;
	}

	public void setCurrent_time_minute(String currentTimeMinute)
	{
		current_time_minute = currentTimeMinute;
	}

	public String getSunset_hour()
	{
		return sunset_hour;
	}

	public void setSunset_hour(String sunsetHour)
	{
		sunset_hour = sunsetHour;
	}

	public String getSunset_minute()
	{
		return sunset_minute;
	}

	public void setSunset_minute(String sunsetMinute)
	{
		sunset_minute = sunsetMinute;
	}

	public String getSunrise_hour()
	{
		return sunrise_hour;
	}

	public void setSunrise_hour(String sunriseHour)
	{
		sunrise_hour = sunriseHour;
	}

	public String getSunrise_minute()
	{
		return sunrise_minute;
	}

	public void setSunrise_minute(String sunriseMinute)
	{
		sunrise_minute = sunriseMinute;
	}

}
