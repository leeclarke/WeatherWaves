package org.dragonfly.wunderground.service;
///TODO: Moon Phase not populating
import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.domain.ForecastDay;
import org.dragonfly.wunderground.domain.MoonPhase;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ForcastHandler  extends DragonflySaxHandler
{
	private List<Forecast> forcasts;
	private Forecast currForcast;
	private ForecastDay currForcastDay;
	private MoonPhase currMoonPhase;
	
	//allows for tracking of what object is currently being populated, dictated by the parent tag
	private DragonflyDomain currentlyProcessedObject;
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException
	{
		if(currForcast == null)
			return;
		if(MoonPhase.root.equalsIgnoreCase(name)){
			this.currForcast.setMoon_phase(this.currMoonPhase);
		}
		else if(Forecast.root.equalsIgnoreCase(getNextToLastOnStack()))
		{
			setBeanValue(this.currForcast, name);
		}
		else if(ForecastDay.root.equalsIgnoreCase(getNextToLastOnStack()))
		{
			setBeanValue(this.currForcastDay, name);
		}
		else if(ForecastDay.root.equalsIgnoreCase(name))
		{
			if(getNextToLastOnStack().equalsIgnoreCase("simpleforecast"))
			{
				this.currForcastDay.setType("simpleforecast");
				if(this.currForcast.getSimpleforecast() == null)
					this.currForcast.setSimpleforecast(new ArrayList<ForecastDay>());
				this.currForcast.getSimpleforecast().add(this.currForcastDay);
			}
			else
			{
				this.currForcastDay.setType("txt_forecast");
				if(this.currForcast.getTxt_forecast() == null)
					this.currForcast.setTxt_forecast(new ArrayList<ForecastDay>());
				this.currForcast.getTxt_forecast().add(this.currForcastDay);
			}
		}
		else if(Forecast.children.contains(getNextToLastOnStack()))
		{
			setBeanValue(this.currForcast, name);
		}
		else if(currentlyProcessedObject instanceof ForecastDay)
		{
			//must be in a child tag, convention is that nested values will have filed name of name+directParentName
			String fieldName = getNextToLastOnStack()+ "_"+ name;
			setBeanValue(this.currForcastDay, fieldName);
		}

		else if(Forecast.root.equalsIgnoreCase(name) || Forecast.root.equalsIgnoreCase(localName))
		{
			this.forcasts.add(this.currForcast);
		}
		closeTag();
	}
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException
	{
		//TODO: still have the last tag as parent, could use that to check FogcastChildren for their children.
		if(Forecast.root.equalsIgnoreCase(name) || Forecast.root.equalsIgnoreCase(localName))
		{
			this.currForcast = new Forecast();
			this.currentlyProcessedObject = this.currForcast; 
		}
		else if(Forecast.children.contains(name))
		{
			this.currForcastDay = new ForecastDay();
			this.currentlyProcessedObject = this.currForcastDay;
		}
		else if(MoonPhase.root.equalsIgnoreCase(name))
		{
			this.currMoonPhase = new MoonPhase();
			this.currentlyProcessedObject = this.currMoonPhase;
		}
		else if(attributes.getLength() > 0)
		{
			setBeanAttributes(this.currForcast, name, attributes);
		}
		updateStack(name);
	}

	@Override
	public void startDocument() throws SAXException
	{
		forcasts = new ArrayList<Forecast>();
		builder = new StringBuilder();
	}
	
	
	
	
	@Override
	public List<? extends DragonflyDomain> getRootItems()
	{
		return this.forcasts;
	}

}