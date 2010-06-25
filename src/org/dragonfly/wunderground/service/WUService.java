package org.dragonfly.wunderground.service;

import java.util.ArrayList;
import java.util.List;

import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.domain.Location;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;

import com.google.gson.Gson;

/**
 * Actual service object which effectively provides a unified interface for making calls to the WU SaxHandlers.
 * Functional business logic will be contained at this point providing both direct access to the service results as well
 * as more refined application oriented services.
 * 
 * @author leeclarke
 */
public class WUService
{
	// TODO: Extract to a properties file so that if this changes in the future it wont require a recompile to fix.
	//TODO: Also include Proxy settings in the properties file!
	public static final String GEO_BASE_URL = "http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=";

	public static final String FORECAST_BASE_Url = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=";

	public static final String OBSERVATION_BASE_URL = "http://api.wunderground.com/auto/wui/geo/WXCurrentObXML/index.xml?query=";
	public static final String PWS_BASE_URL = "http://api.wunderground.com/weatherstation/WXCurrentObXML.asp?ID=";

	public static final String ALERT_BASE_URL = "http://api.wunderground.com/auto/wui/geo/AlertsXML/index.xml?query=";

	private String proxyServer = null;

	private String proxyUser = null;

	private String proxyPassword = null;

	/**
	 * Retrieves a thin list of geo location data simply to let the user pick their preferred location. The primary goal
	 * of this function is to provide an abbreviated data-set that can be used to select a preferred weather station
	 * from the list of airport and pws stations.
	 * 
	 * @param queryStr
	 *            - Parameters for the search, such as zip, city name, log|lat station id
	 * @return - List containing a single location with weather station options to pick from or a list of possible
	 *         Location matches which will require further querying.
	 * @throws DragonflySaxException
	 */
	public List<String> getGeoLocationPickList(String queryStr) throws DragonflySaxException
	{
		List<Location> locations = this.getGeoLocationData(queryStr);
		// initially will call the same handler etc as the full method call but may be refined if the performance is an
		// issue.
		// TODO: do some manipulation to produce a reduced data object and output to desired format.
		// TODO: Might not really need this.
		return null;
	}

	
	/**
	 * Returns the Location search results as JSON formatted data.
	 * @param queryStr
	 * @return
	 * @throws DragonflySaxException
	 */
	public String getGeoLocationJSON(String queryStr) throws DragonflySaxException
	{
		List<Location> locResults = this.getGeoLocationData(queryStr);
		Gson gson = new Gson();
		
		return gson.toJson(locResults);
	}
	
	/**
	 * 
	 * @param queryStr
	 *            - parameters for the search, such as zip, city and or state name, log|lat station id
	 * @return - List containing a single matching location or a list of possible options to pick from.
	 * @throws DragonflySaxException
	 *             - if something goes really wrong with connecting to the service.
	 */
	@SuppressWarnings("unchecked")
	public List<Location> getGeoLocationData(String queryStr) throws DragonflySaxException
	{
		List<Location> results = new ArrayList<Location>();
		if (queryStr != null && queryStr.trim().length() > 0)
		{
			DragonflySaxParser sfp = new DragonflySaxParser(GEO_BASE_URL + queryStr.trim(), new GeoLookupHandler());
			checkProxy(sfp);
			// TODO: Get proxy setting from properties file as well for custom deploy options.
			// sfp.setProxyData("proxySvr:port","uid","password");
			results = (List<Location>) sfp.parse();
		}
		return results;
	}

	/**
	 * 
	 * @param queryStr
	 *            - parameters for the search, such as zip, city and or state name, log|lat station id
	 * @return - List containing a Forcast object.
	 * @throws DragonflySaxException
	 */
	@SuppressWarnings("unchecked")
	public List<Forecast> getForecast(String queryStr) throws DragonflySaxException
	{
		List<Forecast> results = null;
		if (queryStr != null && queryStr.trim().length() > 0)
		{
			DragonflySaxParser sfp = new DragonflySaxParser(FORECAST_BASE_Url + queryStr.trim(), new ForcastHandler());
			checkProxy(sfp);
			results = (List<Forecast>) sfp.parse();
		}
		return results;
	}
	
	/**
	 * Converts the Forcast results into JSON.
	 * @param queryStr
	 * @return
	 * @throws DragonflySaxException
	 */
	public String getForcastJSON(String queryStr) throws DragonflySaxException
	{
		List<Forecast> results = getForecast(queryStr);
		Gson gson = new Gson();
		
		return gson.toJson(results);
	}

	/**
	 * 
	 * @param queryStr
	 * @return - List containing Alerts for the requested area if any have been issued.
	 * @throws DragonflySaxException 
	 */
	@SuppressWarnings("unchecked")
	public List<Alert> getAlerts(String queryStr) throws DragonflySaxException
	{
		List<Alert> results = null;
		if (queryStr != null && queryStr.trim().length() > 0)
		{
			DragonflySaxParser sfp = new DragonflySaxParser(ALERT_BASE_URL + queryStr.trim(), new AlertHandler());
			checkProxy(sfp);
			results = (List<Alert>) sfp.parse();
		}
		return results;
	}

	/**
	 * Converts the Alerts results into JSON.
	 * @param queryStr
	 * @return
	 * @throws DragonflySaxException
	 */
	public String getAlertsJSON(String queryStr) throws DragonflySaxException
	{
		List<Alert> results = getAlerts(queryStr);
		Gson gson = new Gson();
		
		return gson.toJson(results);
		
		
	}
	
	/**
	 * @param queryStr
	 *            - airport or pws station id, best option is to retrieve the list of station ids from the get
	 *            GeoLocationData service first.
	 * @return
	 * @throws DragonflySaxException 
	 */
	@SuppressWarnings("unchecked")
	public List<WeatherObservation> getCurrentConditions(String queryStr) throws DragonflySaxException
	{
		List<WeatherObservation> results = null;
		if (queryStr != null && queryStr.trim().length() > 0)
		{
			String feedUrl = null;
			if(queryStr.length() >4)
			{
				feedUrl = PWS_BASE_URL;
			}
			else
				feedUrl = OBSERVATION_BASE_URL;
			
			//TODO: Determine if requesting a PWS station, what are rules? Looks like they all contain the word 'LOCAL'
			//if cant tell then treat as regular.
			
			DragonflySaxParser sfp = new DragonflySaxParser(feedUrl, new CurrentObservationHandler());
			checkProxy(sfp);
			results = (List<WeatherObservation>) sfp.parse();
		}
		return results;
	}
	
	/**
	 * Checks to see if a proxy is used and sets if needed.
	 * @param sfp
	 */
	private void checkProxy(DragonflySaxParser sfp)
	{
		if(this.proxyServer != null)
		{
			sfp.setProxyData(this.proxyServer,this.proxyUser,this.proxyPassword);
		}
	}

	/**
	 * Sets values to use if proxy is needed for HTTP calls.
	 * @param server
	 * @param user
	 * @param password
	 */
	public void setProxyData(String server, String user, String password)
	{
		this.proxyServer = server;
		this.proxyUser = user;
		this.proxyPassword = password;
	}

	/**
	 * Converts the Current Conditions results in JSON format.
	 * @param queryStr
	 * @return
	 * @throws DragonflySaxException
	 */
	public String getCurrentConditionsJSON(String queryStr) throws DragonflySaxException
	{
		List<WeatherObservation> results = getCurrentConditions(queryStr);
		Gson gson = new Gson();
		
		return gson.toJson(results);
	}
}
