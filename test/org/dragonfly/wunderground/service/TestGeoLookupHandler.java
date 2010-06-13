package org.dragonfly.wunderground.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.exception.DragonflySaxException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGeoLookupHandler
{
	public static final String geoBaseURL = "http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=";
	
	private static final Logger logger = Logger.getLogger(TestGeoLookupHandler.class);

	@SuppressWarnings("unchecked")
	@Test
	public void testParse_zip() throws DragonflySaxException
	{
		String zip = "33584";
		String feedurl = geoBaseURL + zip;

		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new GeoLookupHandler());

		// sfp.setProxyData("proxySvr:port","uid","password");
		List<Location> results = (List<Location>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
		assertEquals(zip, results.get(0).getZip());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParse_NonSpecificCity() throws DragonflySaxException
	{
		//Testing with city of Tampa returns 2 <Locations>
		String feedurl = geoBaseURL + "Tampa";

		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new GeoLookupHandler());

		List<Location> results = (List<Location>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
		logger.debug("Location ct:"+results.size());
		assertTrue(results.size() == 2);
		assertNotNull(results.get(0).getName());
		assertNotNull(results.get(1).getName());
	}
	
	@SuppressWarnings("unchecked")
	@Test (expected = DragonflySaxException.class)
	public void testParse_InvalidInput() throws DragonflySaxException
	{
		//bad input causes fault
		String feedurl = geoBaseURL + "34554322";
		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new GeoLookupHandler());
		List<Location> results = (List<Location>) sfp.parse();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testParse_NoResult() throws DragonflySaxException
	{
		//Non-sense to produce no result
		String feedurl = geoBaseURL + "WETAWSD";
		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new GeoLookupHandler());
		List<Location> results = (List<Location>) sfp.parse();
		assertTrue(results.size() == 0);
	}
	
}
