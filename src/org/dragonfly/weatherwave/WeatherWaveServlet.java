package org.dragonfly.weatherwave;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dragonfly.wunderground.exception.DragonflySaxException;
import org.dragonfly.wunderground.service.WUService;

/**
 * HTTP request handler, will respond to Gets, providing JSON responses. Call
 * with params wtype=[loc,curr,alert,fcst] , query=[zip,city,etc]
 * 
 * @author leeclarke
 */
@SuppressWarnings("serial")
public class WeatherWaveServlet extends HttpServlet
{
	private static final String QUERY = "query";
	private static final String WEATHER_REQ_TYPE = "wtype";
	private static final String LOCATION = "loc";
	private static final String CURRENT_OBSERVATION = "curr";
	private static final String ALERT = "alert";
	private static final String FORECAST = "fcst";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String cmd = req.getParameter(WEATHER_REQ_TYPE);
		String query = req.getParameter(QUERY);
		WUService wuService = new WUService();
		resp.setContentType("text/plain");
		if (LOCATION.equalsIgnoreCase(cmd))
		{
			try
			{
				resp.getWriter().println(wuService.getGeoLocationJSON(query));
			} 
			catch (DragonflySaxException e)
			{
				resp.getWriter().println("error");
			}
		} 
		else if (CURRENT_OBSERVATION.equalsIgnoreCase(cmd))
		{
			try
			{
				resp.getWriter().println(wuService.getCurrentConditionsJSON(query));
			} 
			catch (DragonflySaxException e)
			{
				resp.getWriter().println("error");
			}
		} 
		else if (ALERT.equalsIgnoreCase(cmd))
		{
			try
			{
				resp.getWriter().println(wuService.getAlertsJSON(query));
			} 
			catch (DragonflySaxException e)
			{
				resp.getWriter().println("error");
			}
		} 
		else if (FORECAST.equalsIgnoreCase(cmd))
		{
			try
			{
				resp.getWriter().println(wuService.getForcastJSON(query));
			} 
			catch (DragonflySaxException e)
			{
				resp.getWriter().println("error");
			}
		} 
		else
		{
			//TODO: display a test page w query textbox, drop down with cmd options, and submit button.
			resp.getWriter().println("WeatherWave Test Page. WebApp version Coming soon!  " );
		}
	}
}
