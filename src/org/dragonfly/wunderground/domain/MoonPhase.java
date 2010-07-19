package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;

public class MoonPhase extends DragonflyDomain
{
	public static final String root = "moon_phase";

	enum MOON_PHASE { 
		NA("n/a") ,WAXING_GIBBIOUS("Waxing Gibbous"), FIRST_QUARTER("First Quarter"), WAXING_CRESCENT("Waxing Crecent"), NEW("New"), WANING_CRESCENT("Waining Crecent") ,THIRD_QUARTER("Third Quarter"), WANING_GIBBOUS("Waining Gibbous"), FULL("Full");
		
		private String txtName = "";
		
		MOON_PHASE(String textName)
		{
			txtName = textName;
		}
		
		public String getTxtName()
		{
			return txtName;
		}
	};
	
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
	
	
	/**
	 * Returns a Strin title of the MoonPhase
	 * @return
	 */
	public String getMoonPhaseName()
	{
		int moonInt;
		try
		{
			moonInt = Integer.valueOf(ageOfMoon);
		} catch (Exception e)
		{
			moonInt = 0;
		}
		return (MOON_PHASE.values()[moonInt]).getTxtName();
	}
	
	/**
	 * Convenience methods to get formatted info 
	 * @return
	 */
	public String getMoonPhaseString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Phase: ").append(getMoonPhaseName());
		sb.append(" Percent Illuminated:").append(percentIlluminated);
		
		return sb.toString();
	}
	
	/**
	 * Convenience methods to get formatted info
	 * @return
	 */
	public String getSunsetString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(sunset_hour).append(":").append(sunset_minute);
		return sb.toString();
	}
	
	/**
	 * Convenience methods to get formatted info
	 * @return
	 */
	public String getSunriseString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(sunrise_hour).append(":").append(sunrise_minute);
		return sb.toString();
	}
	
	
	
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
