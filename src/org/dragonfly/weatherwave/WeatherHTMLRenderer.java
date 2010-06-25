package org.dragonfly.weatherwave;

import java.util.List;

import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.domain.WeatherObservation;

/**
 * Responsible for rendering the Weather objects in a lovely HTML format. (Basically this is my view which sadly is put
 * together like a 2001 Servlet. fun!)
 * 
 * @author leeclarke
 */
public class WeatherHTMLRenderer
{
	//TODO: Create keyword Map for Weather Condition --> Image icon 
	/**
	 * Renders a predefined HTML format for a given DragonflyDomain Collection.
	 * @param rootList
	 * @return
	 */
	public static String renderHTML(List<? extends DragonflyDomain> rootList)
	{
		StringBuilder sb = new StringBuilder();
		if(rootList != null && rootList.size()>0)
		{
			DragonflyDomain tmp = rootList.get(0);
			if(tmp instanceof WeatherObservation)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderCurrentConditions((WeatherObservation)dragonflyDomain));
				}
			}
			else if(tmp instanceof Forecast)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderForecast((Forecast)dragonflyDomain));
				}
			}
			if(tmp instanceof Alert)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderAlerts((Alert)dragonflyDomain));
				}
			}
			else if(tmp instanceof Location)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderLocations((Location)dragonflyDomain));
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Renders a predefined HTML format for a given DragonflyDomain Object.
	 * @param rootObject
	 * @return
	 */
	public static String renderHTML(DragonflyDomain rootObject)
	{
		StringBuilder sb = new StringBuilder();
		if(rootObject != null)
		{
			if(rootObject instanceof WeatherObservation)
					sb.append(renderCurrentConditions((WeatherObservation)rootObject));
			else if(rootObject instanceof Forecast)
					sb.append(renderForecast((Forecast)rootObject));
			else if(rootObject instanceof Alert)
					sb.append(renderAlerts((Alert)rootObject));
			else if(rootObject instanceof Location)
					sb.append(renderLocations((Location)rootObject));
		}
		
		return sb.toString();
	}
	
	protected static String renderCurrentConditions(WeatherObservation obs)
	{
		StringBuilder sb = new StringBuilder();
		if(obs != null)
		{
			sb.append("<table>");
			sb.append("<tr><th colspan='2'>").append("").append("</th></tr>");
			sb.append("<tr><td>").append("").append("</td></tr>");
			sb.append("<tr><td>").append("").append("</td></tr>");
			sb.append("</table>");
		}
		
		return sb.toString();
	}
	
	protected static String renderLocations(Location loc)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if(loc != null)
		{
			
		}
		
		return sb.toString();
	}

	protected static String renderAlerts(Alert alert)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if(alert != null)
		{
			
		}
		
		return sb.toString();
	}

	protected static String renderForecast(Forecast forecast)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if(forecast != null)
		{
			
		}
		
		return sb.toString();
	}
}
