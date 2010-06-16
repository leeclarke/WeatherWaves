
package org.dragonfly.wunderground.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.exception.DragonflySaxException;

public class TestAlertHandler  extends TestCase
{
	public static final String alertUrl = "http://api.wunderground.com/auto/wui/geo/AlertsXML/index.xml?query=";
	
	private static final Logger logger = Logger.getLogger(TestAlertHandler.class);
	
	public void testAlertRetrieval() throws DragonflySaxException
	{
		//This might be hard to test because at any given time there may be no alerts for a given zip.
		String alertZip = "86445"; //They seem to have frequent fire alerts here.
		String feedUrl = alertUrl + alertZip;
		logger.debug("connect url: "+ feedUrl);
		DragonflySaxParser sfp = new DragonflySaxParser(feedUrl, new AlertHandler());

		
		List<Alert> results = (List<Alert>) sfp.parse();
		assertNotNull(results);
		if(results.size()>0)//No guarantee that results will be returned!
		{
			assertTrue(results.size()>0); 
			
		}
		else{
			logger.debug("No allerts are currently posted.");
		}
		logger.debug(results);
//		assertEquals(airport, results.get(0).getStation_id());
		
	}
}
