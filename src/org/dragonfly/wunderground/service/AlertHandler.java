package org.dragonfly.wunderground.service;


import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Sax handler for the WeatherUndergrounds Alerts Ws located at:  
 * 
 * http://api.wunderground.com/auto/wui/geo/AlertsXML/index.xml?query=[zip_Code] 
 * @author leeclarke
 */
public class AlertHandler extends DragonflySaxHandler
{

	private List<Alert> alerts;
	private Alert currAlert;

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		if(currAlert == null)
			return;
		if(Alert.root.equalsIgnoreCase(getNextToLastOnStack()))
		{
			setBeanValue(this.currAlert, name);
		}
		else if(Alert.root.equalsIgnoreCase(name) || Alert.root.equalsIgnoreCase(localName))
		{
			this.alerts.add(this.currAlert);
		}
		closeTag();
	}
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		
		if(Alert.root.equalsIgnoreCase(name) || Alert.root.equalsIgnoreCase(localName))
		{
			this.currAlert = new Alert();
		}
		else if(attributes.getLength() > 0)
		{
			setBeanAttributes(this.currAlert, name, attributes);
		}
		updateStack(name);
	}

	@Override
	public void startDocument() throws SAXException
	{
		alerts = new ArrayList<Alert>();
		builder = new StringBuilder();
	}
	
	@Override
	public List<? extends DragonflyDomain> getRootItems()
	{
		return this.alerts;
	}

}