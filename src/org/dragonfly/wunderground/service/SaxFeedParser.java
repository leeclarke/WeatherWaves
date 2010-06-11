package org.dragonfly.wunderground.service;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dragonfly.wunderground.domain.Location;

public class SaxFeedParser extends BaseFeedParser
{

	protected SaxFeedParser(String feedUrl)
	{
		super(feedUrl);
	}

	public List<Location> parse()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			SAXParser parser = factory.newSAXParser();
			DragonflySaxHandler handler = new GeoLookupHandler();
			parser.parse(this.getInputStream(), handler);
			return handler.getMessageItems();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
