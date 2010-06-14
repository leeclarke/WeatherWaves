package org.dragonfly.wunderground.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;

public class TestCurrentObservationHandler  extends TestCase
{
	public static final String geoBaseURL = "http://api.wunderground.com/auto/wui/geo/WXCurrentObXML/index.xml?query=TPA";
	
	
	private static final Logger logger = Logger.getLogger(TestCurrentObservationHandler.class);
	
	@SuppressWarnings("unchecked")
	public void testParse_Airport() throws DragonflySaxException
	{
		String airport = "KTPA";
		String feedurl = geoBaseURL + airport;

		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new CurrentObservationHandler());

		// sfp.setProxyData("proxySvr:port","uid","password");
		sfp.setProxyData("Stamiproxy:8888","lclarke","Tika6848");
		List<WeatherObservation> results = (List<WeatherObservation>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
		assertEquals(airport, results.get(0).getStationId());
	}
}
//Added below cuz I cant change the file through the web ui.
/*
package org.dragonfly.wunderground.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;

public class TestCurrentObservationHandler  extends TestCase
{
	public static final String geoBaseURL = "http://api.wunderground.com/auto/wui/geo/WXCurrentObXML/index.xml?query=TPA";
	
	
	private static final Logger logger = Logger.getLogger(TestCurrentObservationHandler.class);
	
	@SuppressWarnings("unchecked")
	public void testParse_Airport() throws DragonflySaxException
	{
		String airport = "KTPA";
		String feedurl = geoBaseURL + airport;

		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new CurrentObservationHandler());

		// sfp.setProxyData("proxySvr:port","uid","password");
		sfp.setProxyData("Stamiproxy:8888","lclarke","Tika6848");
		List<WeatherObservation> results = (List<WeatherObservation>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
		assertEquals(airport, results.get(0).getStationId());
	}
}
*/
