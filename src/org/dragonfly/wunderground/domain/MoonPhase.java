package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.util.Exportable;

public class MoonPhase extends DragonflyDomain
{
	public static final String root = "moon_phase";
	
	@Exportable(xmlName="percentIlluminated")
	private String percentIlluminated;
	
	@Exportable(xmlName="ageOfMoon")
	private String ageOfMoon;

	
	// <current_time>
	// <hour>8</hour>
	// <minute>05</minute>
	// </current_time>
	// <sunset>
	// <hour>20</hour>
	// <minute>27</minute>
	// </sunset>
	//
	// <sunrise>
	// <hour>5</hour>
	// <minute>14</minute>
	// </sunrise>
	
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

}
