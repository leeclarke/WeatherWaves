package org.dragonfly.wunderground.service;

import java.util.List;

import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;
import org.dragonfly.wunderground.service.WUService;

import junit.framework.TestCase;

public class TestWUService extends TestCase
{

	public void testGetGeoLocationData() throws DragonflySaxException
	{
		String zipCode = "33584";
		WUService wuService = new WUService();
		List<Location> locs = wuService.getGeoLocationData(zipCode);
		assertNotNull(locs);
		System.out.println(locs);
	}

	public void testGetGeoLocationJSON() throws DragonflySaxException
	{
		String zipCode = "33584";
		WUService wuService = new WUService();
		String jsonRtn = wuService.getGeoLocationJSON(zipCode);
		assertNotNull(jsonRtn);
		
		System.out.println("JSON results: "+jsonRtn);
	}
	
	public void testGetForecast()
	{
		fail("Not yet implemented");
	}

	public void testGetAlerts()
	{
		fail("Not yet implemented");
	}

	public void testGetCurrentConditions() throws DragonflySaxException
	{
		String zip = "33602";
		WUService wuService = new WUService();
		List<WeatherObservation> rtn = wuService.getCurrentConditions(zip);
		assertTrue("Valid Search should return results!", rtn.size()>0);
		
	}
	
	public void testGetCurrentConditions_CityState() throws DragonflySaxException
	{
		String zip = "New York, NY";
		WUService wuService = new WUService();
		List<WeatherObservation> rtn = wuService.getCurrentConditions(zip);
		assertTrue("Valid Search should return results!", rtn.size()>0);
		
	}

}
