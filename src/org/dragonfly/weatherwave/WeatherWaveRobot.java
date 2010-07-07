package org.dragonfly.weatherwave;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dragonfly.wunderground.domain.ObservationLocation;
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
	private static final Logger logger = Logger.getLogger(WeatherWaveRobot.class.getName());
	
	private static final String ERROR_MSG = "Oops something has gone terribly wrong! OK not really but, you might have found a bug or " +
						"the weather service is on the fritz. If the problem persists add |debug to the end of your WW " +
						"query like this:  [query|command|debug] (WW code omitted intentionally) and send me the junk in " +
						"the debug blip and I'll have our vast army of code monkeys puzzle it over (and fix it if they want " +
						"any more bananas and Mtn. Dew!)";
	private static final String DEBUG = "debug";
	private static final String BOT_HELP_URL = "http://weatherwaves.appsopt.com";
	private static final String INITIAL_CONTENT_MSG = "\nYou have added WeatherWaves to your wave. Insert current local weather information from the wundergound.com \nFor instructions on use check out our help page."
									+ BOT_HELP_URL + " to getinstructions";
	// TODO: Ensure only one match at a time. Currently it could match multiples at once.
	public static final String WW_REG_PATTERN = "@WW\\[.{4}.*\\]";
	// regex: @WW\[.....*\]

	public void init() throws javax.servlet.ServletException 
	{
		WeatherWaveContext.setAppEnvironment(getServletContext());
		logger.warning("CURR_ENV=" + WeatherWaveContext.CURR_ENV);
		
//		WeatherWaveContext.templatePath = getServletContext().getContextPath();
//		logger.warning("****templatePAth = "+WeatherWaveContext.templatePath);
		logger.warning("****WW_PROPS = "+getServletContext().getRealPath(WeatherWaveContext.PROP_FILE_NAME));
		WeatherWaveContext.loadProperties(getServletContext());
		File rootProp = new File(getServletContext().getRealPath(WeatherWaveContext.PROP_FILE_NAME));
		File rootLoc;
		rootLoc = rootProp.getParentFile();
		WeatherWaveContext.templatePath = rootLoc.getAbsolutePath();
		logger.warning("TemplatePath ==" + WeatherWaveContext.templatePath);
		logger.warning("props values ==" + WeatherWaveContext.props);
		if(rootLoc.isDirectory())
		{
			StringBuilder sb = new StringBuilder();
			String[] flieList = rootLoc.list();
			for (String fname : flieList)
			{
				sb.append("/n").append(fname);
			}
			logger.warning(sb.toString());
		}
		else
		{
			logger.warning("rootLoc not a dir: "+ rootLoc);
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
		return BOT_HELP_URL;
	}

	@Override
	public void onWaveletSelfAdded(WaveletSelfAddedEvent event)
	{
		event.getWavelet().reply(INITIAL_CONTENT_MSG);
	}

	/**
	 * Event fires on Doc Changed event if the filter, @WW[query] or
	 * @WW[query:serviceName] is present. Valid queries should be a zip code, a
	 * city and state or even an airport name prefixed with a 'K' ie 'KTPA'.
	 * This is the default behavior which returns the current weather
	 * observation.
	 * 
	 * Additional serviceNames: 
	 * <li>
	 * 		<ul> alert | a 
	 * 		<ul> forecast | f 
	 * </li> 
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

				//				List<DragonflyDomain> rtn = new ArrayList<DragonflyDomain>();
				if (query.contains("|"))
				{
					//check for debug
					debugBlip = query.contains(DEBUG);
					//rtn = callWWCommand(query);
				} else
				{
					List<WeatherObservation> rtn = new WUService().getCurrentConditions(query);
					if(rtn != null)
					{
						Blip weatherBlip = blip.insertInlineBlip(m.start());
						BlipContentRefs rep = blip.all("@WW[" + query + "]").replace("\n ");
						
						WeatherObservation obs = rtn.get(0);
//						weatherBlip.appendMarkup("<div bgcolor=#3399ff>div test here</div>");
						StringBuilder sb = new StringBuilder("\n\n\n");
						ObservationLocation loc = obs.getDisplay_location();
						sb.append("Current conditions at ").append(loc.getCity()).append(", ").append(loc.getZip()).append(" ");
						int titleLen = sb.length();
						BlipContentRefs head = weatherBlip.append(sb.toString());
						
						
						sb = new StringBuilder("\nTemprature:");
						sb.append(obs.temp_f).append(" F ( ").append(obs.temp_c).append(" C )");
						
						BlipContentRefs temp = weatherBlip.append(sb.toString());
						//temp.annotate("style/fontWeight", "none");
						temp.annotate("style/backgroundColor", "#FFFF99");
						//Humidity
						sb = new StringBuilder("\nHumidity:");
						sb.append(obs.getRelative_humidity());
						weatherBlip.append(sb.toString());
						
						//Wind
						sb = new StringBuilder("\nWind:");
						sb.append(obs.getWind_string());
						weatherBlip.append(sb.toString());
						
						//Pressure
						sb = new StringBuilder("\nBarometric Pressure:");
						sb.append(obs.getPressure_string());
						weatherBlip.append(sb.toString());
						
						//Dewpoint
						sb = new StringBuilder("\nDewPoint:");
						sb.append(obs.getDewpoint_string());
						weatherBlip.append(sb.toString());
						
						//Heat Index
						sb = new StringBuilder("\nHeat Index:");
						sb.append(obs.getHeat_index_string());
						weatherBlip.append(sb.toString());
						
						//Wind Chill
						sb = new StringBuilder("\nWind Chill:");
						sb.append(obs.getWindchill_string());
						weatherBlip.append(sb.toString());
						
						//Visibility
						sb = new StringBuilder("\nVisibility:");
						sb.append(obs.getVisibility_mi()).append("mi (");
						sb.append(obs.getVisibility_km()).append(" km)");
						weatherBlip.append(sb.toString());
						
						BlipContentRefs title = weatherBlip.range(0, titleLen);
						title.annotate("style/fontWeight", "bold");
						title.annotate("style/backgroundColor", "#3399FF");
						
//						weatherBlip.appendMarkup(WeatherHTMLRenderer.renderCurrentConditions(rtn.get(0)));
						logger.info("FINISHED processin query");
					}
				}
				
				i++;
			}
			debug.append("pattern ct match=").append(i);
		} catch (Exception e)
		{
			debug.append("Captain we have a problem!" + e);
			blip.append(ERROR_MSG);
		}
		debug.append("    Weatherwaves has tried to update.");
		if(debugBlip)
		{
			event.getWavelet().reply("DEBUG\n"+debug.toString());
		}
	}

	/**
	 * Simply attempts to take the command search request and add it to 
	 * @param - query string formatted as query|command  (DEBUG key is stripped out here.)
	 * @return - query results
	 * @throws DragonflySaxException 
	 */
	private String callWWCommand(String query) throws DragonflySaxException
	{
		String rtn = null;
		//remove debug keyword
		query = query.contains(DEBUG)? query.substring(0,query.indexOf(DEBUG)-1):query;
		StringTokenizer st = new StringTokenizer(query,"|");
		if(st.countTokens() == 1)
			rtn =  new WUService().getCurrentConditionsJSON(query);
		else
		{
			String q = st.nextToken();
			String cmd = st.nextToken();
			if(cmd.equalsIgnoreCase("F")||cmd.equalsIgnoreCase("forecast"))
			{
				rtn =  new WUService().getForcastJSON(q);
			}
			else if(cmd.equalsIgnoreCase("A")||cmd.equalsIgnoreCase("Alert"))
			{
				rtn =  new WUService().getAlertsJSON(q);
			}
		}
		return rtn;
	}
}
