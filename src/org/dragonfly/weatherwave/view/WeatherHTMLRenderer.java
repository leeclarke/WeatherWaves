package org.dragonfly.weatherwave.view;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import org.dragonfly.weatherwave.WeatherWaveContext;
import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.domain.WeatherObservation;

/**
 * Responsible for rendering the Weather objects in a lovely HTML format.
 * (Basically this is my view which sadly is put together like a 2001 Servlet.
 * fun!)
 * 
 * @author leeclarke
 */
public class WeatherHTMLRenderer
{
	private static final Logger logger = Logger.getLogger(WeatherHTMLRenderer.class.getName());
	// TODO: Create keyword Map for Weather Condition --> Image icon
	/**
	 * Renders a predefined HTML format for a given DragonflyDomain Collection.
	 * 
	 * @param rootList
	 * @return
	 */
	public static String renderHTML(List<? extends DragonflyDomain> rootList)
	{
		StringBuilder sb = new StringBuilder();
		if (rootList != null && rootList.size() > 0)
		{
			DragonflyDomain tmp = rootList.get(0);
			if (tmp instanceof WeatherObservation)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderCurrentConditions((WeatherObservation) dragonflyDomain));
				}
			} else if (tmp instanceof Forecast)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderForecast((Forecast) dragonflyDomain));
				}
			}
			if (tmp instanceof Alert)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderAlerts((Alert) dragonflyDomain));
				}
			} else if (tmp instanceof Location)
			{
				for (DragonflyDomain dragonflyDomain : rootList)
				{
					sb.append(renderLocations((Location) dragonflyDomain));
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Renders a predefined HTML format for a given DragonflyDomain Object.
	 * 
	 * @param rootObject
	 * @return
	 */
	public static String renderHTML(DragonflyDomain rootObject)
	{
		StringBuilder sb = new StringBuilder();
		if (rootObject != null)
		{
			if (rootObject instanceof WeatherObservation)
				sb.append(renderCurrentConditions((WeatherObservation) rootObject));
			else if (rootObject instanceof Forecast)
				sb.append(renderForecast((Forecast) rootObject));
			else if (rootObject instanceof Alert)
				sb.append(renderAlerts((Alert) rootObject));
			else if (rootObject instanceof Location)
				sb.append(renderLocations((Location) rootObject));
		}

		return sb.toString();
	}

	/**
	 * @param obs
	 * @return
	 */
	protected static String renderCurrentConditions(WeatherObservation obs)
	{
		if (obs == null)
			throw new NullPointerException("WeatherObservation obejct is null");
		try
		{
			//TODO: add to properties file to have 2 or 3 settings for st path.
			String templatePath = WeatherWaveContext.props.getProperty("string-template.path");
			logger.warning("TemplatePath =="+WeatherWaveContext.getStringTempPath()+templatePath);//"/templates");
			StringTemplateGroup templateGroup = new StringTemplateGroup("WeatherObs", WeatherWaveContext.getStringTempPath()+templatePath);

			StringTemplate obsDisplay = templateGroup.getInstanceOf("ObsDisplay");
			obsDisplay.setAttribute("observation_time", verifyString(obs.getObservation_time()));
			if (obs.getDisplay_location() != null)
			{
				obsDisplay.setAttribute("display_location_city", verifyString(obs.getDisplay_location().getCity()));
				obsDisplay.setAttribute("display_location_state", verifyString(obs.getDisplay_location().getState()));
				obsDisplay.setAttribute("display_location_zip", verifyString(obs.getDisplay_location().getZip()));
			}
			obsDisplay.setAttribute("temp_f", verifyString(obs.getTemp_f()));
			obsDisplay.setAttribute("temp_c", verifyString(obs.getTemp_c()));
//			obsDisplay.setAttribute("relative_humidity", verifyString(obs.getRelative_humidity()));
//			obsDisplay.setAttribute("wind_string", verifyString(obs.getWind_string()));
//			obsDisplay.setAttribute("pressure_string", verifyString(obs.getPressure_string()));
//			obsDisplay.setAttribute("dewpoint_string", verifyString(obs.getDewpoint_string()));
//			obsDisplay.setAttribute("heat_index_string", verifyString(obs.getHeat_index_string()));
//			obsDisplay.setAttribute("windchill_f", verifyString(obs.getWindchill_f()));
//			obsDisplay.setAttribute("windchill_c", verifyString(obs.getWindchill_c()));
//			obsDisplay.setAttribute("visibility_mi", verifyString(obs.getVisibility_mi()));
			obsDisplay.setAttribute("visibility_km", verifyString(obs.getVisibility_km()));

			logger.warning("TEMPATEL RESULT == "+obsDisplay.toString());
			return obsDisplay.toString();
		} catch (Exception e)
		{
			//TODO: Clean up
			StringBuilder sb = new StringBuilder();
			File root = new File(".");
			sb.append("absPath=").append(root.getAbsolutePath());
			sb.append("Path=").append(root.getPath());
			File loc = new File("templates/ObsDisplay.st");
			sb.append("expected name:").append(loc.exists());
			sb.append("Failed 2 build template  ");
			logger.warning(sb.toString()+e);
			return "There was an error generating the response.";
		}

	}

	protected static String renderLocations(Location loc)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (loc != null)
		{

		}

		return sb.toString();
	}

	protected static String renderAlerts(Alert alert)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (alert != null)
		{

		}

		return sb.toString();
	}

	protected static String renderForecast(Forecast forecast)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		if (forecast != null)
		{

		}

		return sb.toString();
	}

	protected static String verifyString(String value)
	{
		return value == null ? "" : value;
	}
}
