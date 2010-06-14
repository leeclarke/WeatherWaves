package org.dragonfly.wunderground.service;
//TODO: Finish Handler.
import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.ObservationLocation;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class CurrentObservationHandler extends DragonflySaxHandler
{
	private ArrayList<WeatherObservation> wObItems;
	private WeatherObservation currWOb;
	private ObservationLocation currObLoc;
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		super.endElement(uri, localName, name);
		if (this.currWOb != null)
		{
			
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, name, attributes);
		//create new object for start of each root tag
		if (localName.equalsIgnoreCase(WeatherObservation.root) || name.equalsIgnoreCase(WeatherObservation.root))
		{
			this.currWOb =  new WeatherObservation();
		}
		else if (ObservationLocation.root.contains(localName) || ObservationLocation.root.contains(name))
		{
			
		}
		
		this.tagStack.add(name); // Add to end of stack
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		super.startDocument();
		wObItems = new ArrayList<WeatherObservation>();
		builder = new StringBuilder();
	}
	
	@Override
	public List<? extends DragonflyDomain> getRootItems()
	{
		return this.wObItems;
	}

}
