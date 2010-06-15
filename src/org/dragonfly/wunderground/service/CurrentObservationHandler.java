package org.dragonfly.wunderground.service;

import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.ObservationLocation;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.domain.WuImage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Processes the xml for the Current Weather Observation service. Currently only  works on Airport codes.
 * @author leeclarke
 */
public class CurrentObservationHandler extends DragonflySaxHandler
{
	private ArrayList<WeatherObservation> wObItems;
	private WeatherObservation currWOb;
	private ObservationLocation currObLoc;
	private WuImage curImage;
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		if (this.currWOb == null)
			return;
		
		if(ObservationLocation.root.contains(getNextToLastOnStack()))
		{
			setBeanValue(currObLoc, name);
		}
		else if(WuImage.root.equalsIgnoreCase(getNextToLastOnStack()))
		{
			setBeanValue(curImage, name);
		}
		else if(ObservationLocation.root.contains(name))	
		{
			if(ObservationLocation.DISPLAY_LOC.equalsIgnoreCase(currObLoc.getLocType()))
				currWOb.setDisplay_location(currObLoc);
			else 
				currWOb.setObservation_location(currObLoc) ;
		}
		else if(WuImage.root.equalsIgnoreCase(name))
		{
			this.currWOb.setImage(this.curImage);
		}
		else if(WeatherObservation.root.equalsIgnoreCase(getNextToLastOnStack()))
		{
			setBeanValue(currWOb, name);
		}
		else if(WeatherObservation.root.equalsIgnoreCase(name))
		{
			if(this.currWOb.getTermsofservice() == null  || this.currWOb.getTermsofservice().trim().length() == 0)
				this.currWOb.setTermsofservice("http://www.wunderground.com/members/tos.asp#api");
			wObItems.add(this.currWOb);
		}
		builder.setLength(0);
		this.tagStack.removeLast();
	}
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		//create new object for start of each root tag
		if (localName.equalsIgnoreCase(WeatherObservation.root) || name.equalsIgnoreCase(WeatherObservation.root))
		{
			this.currWOb =  new WeatherObservation();
		}
		else if (ObservationLocation.root.contains(localName) || ObservationLocation.root.contains(name))
		{
			this.currObLoc = new ObservationLocation();
			this.currObLoc.setLocType(name);
		}
		else if(WuImage.root.equalsIgnoreCase(localName) || WuImage.root.equalsIgnoreCase(name))
		{
			this.curImage = new WuImage();
		}
		
		this.tagStack.add(name); // Add to end of stack
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		wObItems = new ArrayList<WeatherObservation>();
		builder = new StringBuilder();
	}
	
	@Override
	public List<? extends DragonflyDomain> getRootItems()
	{
		return this.wObItems;
	}

}
