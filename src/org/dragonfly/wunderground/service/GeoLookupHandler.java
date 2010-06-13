package org.dragonfly.wunderground.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.domain.Cam;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.domain.Radar;
import org.dragonfly.wunderground.domain.Station;
import org.dragonfly.wunderground.domain.Station.StationType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Handler for processing the Location XML feed from Wunderground.
 * 
 * @author leeclarke
 */
public class GeoLookupHandler extends DragonflySaxHandler
{

	private static final Logger logger = Logger.getLogger(GeoLookupHandler.class);
	private Radar currRadarSubObj;
	private Cam currCamSubObj;
	private ArrayList<Location> locItems;
	private Station currStation;
	private String stationType;

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		super.endElement(uri, localName, name);
		if (this.currentLocation != null)
		{
			if (getNextToLastOnStack().equalsIgnoreCase(Radar.root))
			{
				setBeanValue(currRadarSubObj, name);
			} else if (getNextToLastOnStack().equalsIgnoreCase(Cam.root))
			{
				setBeanValue(currCamSubObj, name);

			} else if (getNextToLastOnStack().equalsIgnoreCase(Station.root))
			{
				setBeanValue(currStation, name);
			} else if(name.equalsIgnoreCase(Station.root))
			{
				currentLocation.getStations().add(this.currStation);
			}
			else if (name.equalsIgnoreCase(Location.root))
			{
				locItems.add(currentLocation);
			} else if (localName.equalsIgnoreCase(Radar.root) || name.equalsIgnoreCase(Radar.root))
			{
				if (logger.isDebugEnabled())
					logger.debug("Obj: Radar");
				this.currentLocation.setRadar(currRadarSubObj);
			} else if (localName.equalsIgnoreCase(Cam.root) || name.equalsIgnoreCase(Cam.root))
			{
				if (logger.isDebugEnabled())
					logger.debug("Obj: Cam");
				this.currentLocation.getWebCams().add(currCamSubObj);
			} else if (getNextToLastOnStack().equalsIgnoreCase(Location.root))
			{
				setBeanValue(currentLocation, name);
			}
			builder.setLength(0);
			this.tagStack.removeLast();
		}
	}

	/**
	 * Helper method because the code just keeps repeating..
	 * @param dbean - Domain bean to set
	 * @param name - field to set.
	 */
	private void setBeanValue(DragonflyDomain dbean, String name)
	{
		if (dbean.fields.contains(name))
		{
			try
			{
				String mthName = BeanUtil.getMethodName(name);
				if (logger.isDebugEnabled())
					logger.debug("call:" + mthName);
				BeanUtil.invokeMethod(dbean, mthName, new Object[] { builder.toString().trim() }, null);
			} catch (Exception e)
			{
				logger.error("Error invoking Method for: " + name, e);
			}
		}
		
	}

	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		locItems = new ArrayList<Location>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(Location.root) || name.equalsIgnoreCase(Location.root))
		{
			String locType = null;
			if (attributes != null)
			{
				locType = attributes.getValue(0);
			}
			this.currentLocation = new Location();
			this.currentLocation.setLocType(locType);

		} else if (localName.equalsIgnoreCase(Radar.root) || name.equalsIgnoreCase(Radar.root))
		{
			this.currRadarSubObj = new Radar();
		} else if (localName.equalsIgnoreCase(Cam.root) || name.equalsIgnoreCase(Cam.root))
		{
			currCamSubObj = new Cam();
		} else if(StationType.AIRPORT.getName().equalsIgnoreCase(localName) || StationType.AIRPORT.getName().equalsIgnoreCase(name)
				||StationType.PWS.getName().equalsIgnoreCase(localName) || StationType.PWS.getName().equalsIgnoreCase(name))
		{
			this.stationType = name;
		}
		else if(localName.equalsIgnoreCase(Station.root) || name.equalsIgnoreCase(Station.root))
		{
			currStation = new Station();
			currStation.setType(StationType.valueOf(stationType.toUpperCase()));
		}

		this.tagStack.add(name); // Add to end of stack
	}

	@Override
	public List<? extends DragonflyDomain> getRootItems()
	{
		return this.locItems;
	}
}