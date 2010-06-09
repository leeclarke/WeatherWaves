package org.dragonfly.wunderground.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.domain.Location;

import junit.framework.TestCase;

public class TestSaxFeedParser2 extends TestCase
{

	private static final Logger logger = Logger.getLogger(TestSaxFeedParser2.class);
	
	public void testParse()
	{
		String feedurl = "http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=33584";
		
		SaxFeedParser sfp = new SaxFeedParser(feedurl);
		
//		sfp.setProxyData("proxySvr:port","uid","password");
		List<Location> results = sfp.parse();
		logger.debug(results);
	}

}
