package org.dragonfly.wunderground.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.exception.DragonflySaxException;

public class TestForcastHandler extends TestCase
{
	public static final String forcastUrl = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=";

	private static final Logger logger = Logger.getLogger(TestForcastHandler.class);

	public void testAlertRetrieval() throws DragonflySaxException
	{
		// This might be hard to test because at any given time there may be no alerts for a given zip.
		String forcastCity = "33584"; // They seem to have frequent fire alerts here.
		String feedUrl = forcastUrl + forcastCity;
		logger.debug("connect url: " + feedUrl);
		DragonflySaxParser sfp = new DragonflySaxParser(feedUrl, new ForcastHandler());

		List<Forecast> results = (List<Forecast>) sfp.parse();
		assertNotNull(results);
		assertTrue(results.size() > 0);

		logger.debug(results);
		// assertEquals(airport, results.get(0).getStation_id());

	}
}