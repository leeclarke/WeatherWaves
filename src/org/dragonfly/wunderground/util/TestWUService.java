package org.dragonfly.wunderground.util;

import java.util.List;

import org.dragonfly.wunderground.domain.Location;
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

	public void testGetForecast()
	{
		fail("Not yet implemented");
	}

	public void testGetAlerts()
	{
		fail("Not yet implemented");
	}

	public void testGetCurrentConditions()
	{
		fail("Not yet implemented");
	}

}
