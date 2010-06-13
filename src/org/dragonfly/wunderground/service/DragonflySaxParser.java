package org.dragonfly.wunderground.service;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.exception.DragonflySaxException;

/**
 * @author leeclarke
 */
public class DragonflySaxParser extends BaseFeedParser
{
	private DragonflySaxHandler handler;
	
	
	/**
	 * @param feedUrl
	 * @param dHandler
	 */
	protected DragonflySaxParser(String feedUrl, DragonflySaxHandler dHandler)
	{
		super(feedUrl);
		if(dHandler == null)
			throw new NullPointerException();
		this.handler = dHandler;
	}

	public List<? extends DragonflyDomain> parse() throws DragonflySaxException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			SAXParser parser = factory.newSAXParser();
			parser.parse(this.getInputStream(), handler);
			return handler.getRootItems();
		}
		catch (Exception e)
		{
			throw new DragonflySaxException(e); 
		}
	}
}
