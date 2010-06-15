package org.dragonfly.wunderground.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;

public class TestCurrentObservationHandler  extends TestCase
{
	public static final String obsBaseURL = "http://api.wunderground.com/auto/wui/geo/WXCurrentObXML/index.xml?query=";
	public static final String pwsURL = "http://api.wunderground.com/weatherstation/WXCurrentObXML.asp?ID=";
	
	
	private static final Logger logger = Logger.getLogger(TestCurrentObservationHandler.class);
	
	@SuppressWarnings("unchecked")
	public void testParse_Airport() throws DragonflySaxException
	{
		String airport = "KTPA";
		String feedurl = obsBaseURL + airport;

		logger.debug("connect url:"+ feedurl);
		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new CurrentObservationHandler());

		// sfp.setProxyData("proxySvr:port","uid","password");
		List<WeatherObservation> results = (List<WeatherObservation>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
		assertEquals(airport, results.get(0).getStation_id());
	}
	
	public void testParse_WeatherStation() throws DragonflySaxException
	{
		//NOTE this is a totally different URL call.
		String pwdId = "KFLOCALA28";
		String feedurl = pwsURL + pwdId;

		logger.debug("connect url:"+ feedurl);
		DragonflySaxParser sfp = new DragonflySaxParser(feedurl, new CurrentObservationHandler());

		// sfp.setProxyData("proxySvr:port","uid","password");
		List<WeatherObservation> results = (List<WeatherObservation>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size()>0);
		logger.debug(results);
		assertEquals(pwdId, results.get(0).getStation_id());
	}
}

