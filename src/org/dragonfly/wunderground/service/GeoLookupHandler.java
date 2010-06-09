package org.dragonfly.wunderground.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.domain.Location;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GeoLookupHandler extends DefaultHandler
{

	private static final Logger logger = Logger.getLogger(GeoLookupHandler.class);
	private List<Location> locations;
	private Location currentMessage;
	private StringBuilder builder;
	private LinkedList<String> tagStack = new LinkedList<String>();

	public List<Location> getLocations()
	{
		return this.locations;
	}

	
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		super.endElement(uri, localName, name);
		if (this.currentMessage != null)
		{
			if(getNextToLastOnStack().equalsIgnoreCase("radar"))
			{
				//TODO: Process radar info.
			}
			else if(getNextToLastOnStack().equalsIgnoreCase("webcams"))
			{
				//TODO: process webcams
			}
			else if(getNextToLastOnStack().equalsIgnoreCase("nearby_weather_stations"))
			{
				//TODO: nearby_weather_stations
			}
			else if (name.equalsIgnoreCase(Location.root))
			{
				locations.add(currentMessage);
			}
			else if(getNextToLastOnStack().equalsIgnoreCase(Location.root))
			{
				if (Location.fields.contains(name))
				{
					logger.debug("tag value=" + name);
					try
					{
						String mthName = BeanUtil.getMethodName(name);
						logger.debug("call:" + mthName);
						BeanUtil.invokeMethod(this.currentMessage, mthName, new Object[] { builder.toString().trim() },
								null);
					}
					catch (Exception e)
					{
						logger.error("Error invoking Method for: " + name, e);
					}
	
				}
			}
			builder.setLength(0);
			this.tagStack.removeLast();
		}
	}

	/**
	 * @return - item before the last.
	 */
	private String getNextToLastOnStack()
	{
		String nextToLastTag = "";
		if(this.tagStack.size() >1)
			nextToLastTag = this.tagStack.get(this.tagStack.size()-2);
		
		return nextToLastTag;
	}



	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		locations = new ArrayList<Location>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(Location.root) || name.equalsIgnoreCase(Location.root))
		{
			String locType = null;
			if(attributes != null)
			{
				locType = attributes.getValue(0);
			}
			this.currentMessage = new Location(locType);
			
		}
		this.tagStack.add(name); //Add to end of stack
	}
}