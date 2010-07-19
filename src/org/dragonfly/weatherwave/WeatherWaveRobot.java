package org.dragonfly.weatherwave;
//TODO: Need to  include the Weather image in output
//TODO: Need Bot image
//TODO: Finish Forecast Render

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dragonfly.weatherwave.exception.WWViewException;
import org.dragonfly.weatherwave.view.WeatherBlipRenderer;
import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;
import org.dragonfly.wunderground.service.WUService;

import com.google.wave.api.AbstractRobot;
import com.google.wave.api.Blip;
import com.google.wave.api.BlipContentRefs;
import com.google.wave.api.event.DocumentChangedEvent;
import com.google.wave.api.event.WaveletSelfAddedEvent;

@SuppressWarnings("serial")
public class WeatherWaveRobot extends AbstractRobot
{
	private static final String MSG_SERVICE_DOWN = "Looks like the weather service is not responding, sorry about this. Try again in a bit and see if the service is back up.";

	private static final Logger logger = Logger.getLogger(WeatherWaveRobot.class.getName());

	private static final String ERROR_MSG = "Oops something has gone terribly wrong! OK not really but, you might have found a bug or "
			+ "the weather service is on the fritz. If the problem persists add |debug to the end of your WW "
			+ "query like this:  [query|command|debug] (WW code omitted intentionally) and send me the junk in "
			+ "the debug blip and I'll have our vast army of code monkeys puzzle it over (and fix it if they want "
			+ "any more bananas and Mtn. Dew!)";
	private static final String DEBUG = "debug";
	private static final String BOT_HELP_URL = "http://weatherwaves.appsopt.com";
	private static final String INITIAL_CONTENT_MSG = "\nYou have added WeatherWaves to your wave. Insert current local weather information from the wundergound.com \nFor instructions on use check out our help page."
			+ BOT_HELP_URL + " to getinstructions";
	
	public static final String WW_REG_PATTERN = "@WW\\[.{4}.*\\]"; // regex: @WW\[.....*\]

	private static final String BOT_AVATAR_URL = "http://weatherwaves.appsopt.com/img/Tray-Weather-Forecast.icon.gif";

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws javax.servlet.ServletException
	{
		WeatherWaveContext.setAppEnvironment(getServletContext());
		logger.info("CURR_ENV=" + WeatherWaveContext.CURR_ENV);

		logger.info("****WW_PROPS = " + getServletContext().getRealPath(WeatherWaveContext.PROP_FILE_NAME));
		WeatherWaveContext.loadProperties(getServletContext());
		File rootProp = new File(getServletContext().getRealPath(WeatherWaveContext.PROP_FILE_NAME));
		File rootLoc;
		rootLoc = rootProp.getParentFile();
		WeatherWaveContext.templatePath = rootLoc.getAbsolutePath();
		logger.info("TemplatePath ==" + WeatherWaveContext.templatePath);
		logger.info("props values ==" + WeatherWaveContext.props);
		if (rootLoc.isDirectory())
		{
			StringBuilder sb = new StringBuilder();
			String[] flieList = rootLoc.list();
			for (String fname : flieList)
			{
				sb.append("/n").append(fname);
			}
			logger.info(sb.toString());
		} else
		{
			logger.warning("rootLoc not a dir: " + rootLoc);
		}

	}

	@Override
	protected String getRobotName()
	{
		return "WeatherWaves Robot";
	}

	@Override
	protected String getRobotProfilePageUrl()
	{
		return null;
	}

	@Override
	protected String getRobotAvatarUrl()
	{
		return BOT_AVATAR_URL;
	}

	
	@Override
	public void onWaveletSelfAdded(WaveletSelfAddedEvent event)
	{
		event.getWavelet().reply(INITIAL_CONTENT_MSG);
	}

	/**
	 * Event fires on Doc Changed event if the filter, @WW[query] or
	 * 
	 * @WW[query:serviceName] is present. Valid queries should be a zip code, a
	 *                        city and state or even an airport name prefixed
	 *                        with a 'K' ie 'KTPA'. This is the default behavior
	 *                        which returns the current weather observation.
	 * 
	 * Additional serviceNames: <li>
	 *                            <ul>alert | a
	 *                            <ul>forecast | f
	 *                         </li>
	 * @see com.google.wave.api.AbstractRobot#onDocumentChanged(com.google.wave.api.event.DocumentChangedEvent)
	 */
	@Capability(filter = WW_REG_PATTERN)
	@Override
	public void onDocumentChanged(DocumentChangedEvent event)
	{
		boolean debugBlip = false;
		StringBuilder debug = new StringBuilder("\n WW Debug: ");
		Blip blip = event.getBlip();
		String text = blip.getContent();

		try
		{
			Pattern p = Pattern.compile(WW_REG_PATTERN);
			Matcher m = p.matcher(text);
			int i = 0;

			while (m.find())
			{
				logger.info("Found Match");
				String query = text.substring(m.start() + 4, m.end() - 1);
				debug.append("\nWW_query= ").append(query);
				logger.info("WW_query= " +query);
				BlipContentRefs rep = blip.all("@WW[" + query + "]").replace("\n ");
				int insertPos = m.start();
				if (query.contains("|"))
				{
					logger.info("IN debug or Extended cmd mode.");
					// check for debug
					debugBlip = query.contains(DEBUG);
					callWWCommand(query, blip, debug, insertPos);

				} else
				{
					logger.info(">DEFAULT query = " + query);
					List<WeatherObservation> rtn = new WUService().getCurrentConditions(query);
					if (rtn != null)
					{
						Blip weatherBlip = blip.insertInlineBlip(insertPos);

						WeatherObservation obs = rtn.get(0);
						try
						{
							WeatherBlipRenderer.renderCurrentConditions(obs, weatherBlip);
						} catch (WWViewException e)
						{
							weatherBlip.append("\nNo results were returned for the search query: " + query);
						}

						logger.info("FINISHED processin query");
					}
				}

				i++;
			}
			debug.append("pattern ct match=").append(i);
		} catch (DragonflySaxException de)
		{
			blip.append(MSG_SERVICE_DOWN);
		} catch (Exception e)
		{
			debug.append("Captain we have a problem!" + e);
			blip.append(ERROR_MSG);
		}
		debug.append("    Weatherwaves has tried to update.");
		if (debugBlip)
		{
			event.getWavelet().reply("DEBUG\n" + debug.toString());
		}
	}

	/**
	 * Simply attempts to take the command search request and add it to
	 * 
	 * @param debug
	 * @param blip
	 * @param insertPos 
	 * @param - query string formatted as query|command (DEBUG key is stripped
	 *        out here.)
	 * @return - query results
	 * @throws DragonflySaxException
	 */
	private void callWWCommand(String query, Blip blip, StringBuilder debug, int insertPos) throws DragonflySaxException
	{
		// remove debug keyword
		query = query.contains(DEBUG) ? query.substring(0, query.indexOf(DEBUG) - 1) : query;
		StringTokenizer st = new StringTokenizer(query, "|");

		if (st.countTokens() == 1)
		{
			List<WeatherObservation> rtn = new WUService().getCurrentConditions(query);
			Blip weatherBlip = blip.insertInlineBlip(insertPos);

			WeatherObservation obs = rtn.get(0);
			try
			{
				WeatherBlipRenderer.renderCurrentConditions(obs, weatherBlip);
			} catch (WWViewException e)
			{
				weatherBlip.append("\nNo results were returned for the search query: " + query);
			}
		}
		else
		{
			String q = st.nextToken();
			String cmd = st.nextToken();
			try
			{

				if (cmd.equalsIgnoreCase("F") || cmd.equalsIgnoreCase("forecast"))
				{
					List<Forecast> forecast = new WUService().getForecast(q);
					
					WeatherBlipRenderer.renderForecast(forecast, blip.insertInlineBlip(insertPos),q);
					
				} else if (cmd.equalsIgnoreCase("A") || cmd.equalsIgnoreCase("Alert"))
				{
					List<Alert> alerts = new WUService().getAlerts(q);
					if(alerts.size()<1)
					{
						Blip wwBlip = blip.insertInlineBlip(insertPos);
						wwBlip.append("\nWeatherWave returns no alerts, all is fine!").annotate("style/fontWeight", "bold");
					}
					else
						WeatherBlipRenderer.renderAlerts(alerts, blip.insertInlineBlip(insertPos),q);
				}
			} 
			catch (DragonflySaxException de) {
				blip.append(MSG_SERVICE_DOWN);
			}
			catch (Exception e)
			{
				debug.append("Captain we have a problem!" + e);
				blip.append(ERROR_MSG);
			}
		}
	}

}
