package org.dragonfly.wunderground.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.domain.Radar;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Handler for processing the Location XML feed from Wunderground.
 * @author leeclarke
 */
public class GeoLookupHandler extends DragonflySaxHandler
{

	private static final Logger logger = Logger.getLogger(GeoLookupHandler.class);
	private boolean radarSubObjFlag;
	private Radar currRadarSubObj;
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		super.endElement(uri, localName, name);
		if (this.currentMessage != null)
		{
			if(getNextToLastOnStack().equalsIgnoreCase(Radar.root))
			{
				if (DragonflyDomain.fields.contains(name))
				{
					try
					{
						String mthName = BeanUtil.getMethodName(name);
						if(logger.isDebugEnabled())logger.debug("call:" + mthName);
						BeanUtil.invokeMethod(this.radarSubObjFlag, mthName, new Object[] { builder.toString().trim() },
								null);
					}
					catch (Exception e)
					{
						logger.error("Error invoking Method for: " + name, e);
					}
				}
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
				messageItems.add(currentMessage);
			}
			else if(localName.equalsIgnoreCase(Radar.root) || name.equalsIgnoreCase(Radar.root))
			{
				this.currentMessage.setRadar(currRadarSubObj);
			}
			else if(getNextToLastOnStack().equalsIgnoreCase(Location.root))
			{
				if (DragonflyDomain.fields.contains(name))
				{
					logger.debug("tag value=" + name);
					try
					{
						String mthName = BeanUtil.getMethodName(name);
						if(logger.isDebugEnabled())logger.debug("call:" + mthName);
						BeanUtil.invokeMethod(this.currentMessage, mthName, new Object[] { builder.toString().trim() },null);
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

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		messageItems = new ArrayList<Location>();
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
			this.currentMessage = new Location();
			this.currentMessage.setLocType(locType);
			
		}
		else if(localName.equalsIgnoreCase(Radar.root) || name.equalsIgnoreCase(Radar.root))
		{
			this.currRadarSubObj = new Radar();
		}
			
		this.tagStack.add(name); //Add to end of stack
	}
}