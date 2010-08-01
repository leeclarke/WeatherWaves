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
		
		/**
		 * REturns the moon type based on the age (days old) of the moon
		 * @param age
		 * @return
		 */
		public static  MOON_PHASE getByAge(int age)
		{
			switch (age) {
				case 29 : case 30 : case 0 : case 1 : case 2 : 	case 3 :
					return MOON_PHASE.NEW;
				
				case 4 : case 5 : case 6 :
					return MOON_PHASE.WAXING_CRESCENT;
					
				case 7 : case 8 : case 9 :
					return MOON_PHASE.FIRST_QUARTER;
					
				case 10 : case 11 : case 12 : case 13 :
					return MOON_PHASE.WAXING_GIBBIOUS;
					
				case 14 : case 15 : case 16 : case 17 :
					return MOON_PHASE.FULL;
				
				case 18: case 19: case 20: case 21:
					return MOON_PHASE.WANING_GIBBOUS;
				
				case 22: case 23: case 24: case 25:
					return MOON_PHASE.THIRD_QUARTER;
				
				case 26: case 27: case 28:
					return MOON_PHASE.WANING_CRESCENT;
			}
			return NA;
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
		return (MOON_PHASE.getByAge(moonInt)).getTxtName();
	}
	
	/**
	 * Convenience methods to get formatted info 
	 * @return
	 */
	public String getMoonPhaseString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Phase: ").append(getMoonPhaseName());
		sb.append(", Percent Illuminated: ").append(percentIlluminated).append("%");
		
		return sb.toString();
	}
	
	/**
	 * Convenience methods to get formatted info
	 * @return
	 */
	public String getSunsetString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(sunset_hour).append(":").append(sunset_minute).append(" PM");
		return sb.toString();
	}
	
	/**
	 * Convenience methods to get formatted info
	 * @return
	 */
	public String getSunriseString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(sunrise_hour).append(":").append(sunrise_minute).append(" AM");
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
