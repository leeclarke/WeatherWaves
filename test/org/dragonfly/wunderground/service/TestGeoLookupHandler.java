package org.dragonfly.wunderground.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.Location;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGeoLookupHandler
{
	public static final String geoBaseURL = "http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=";
	
	private static final Logger logger = Logger.getLogger(TestGeoLookupHandler.class);

	@SuppressWarnings("unchecked")
	@Test
	public void testParse_zip()
	{
		String feedurl = geoBaseURL + "33584";

		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new GeoLookupHandler());

		// sfp.setProxyData("proxySvr:port","uid","password");
		List<Location> results = (List<Location>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
	}
	
	public void testParse_NonSpecificCity()
	{
		//Testing with city of Tampa returns 2 <Locations>
		
	}
}
