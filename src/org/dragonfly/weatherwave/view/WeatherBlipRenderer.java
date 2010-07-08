package org.dragonfly.weatherwave.view;

import org.dragonfly.weatherwave.exception.WWViewException;
import org.dragonfly.wunderground.domain.ObservationLocation;
import org.dragonfly.wunderground.domain.WeatherObservation;

import com.google.wave.api.Blip;
import com.google.wave.api.BlipContentRefs;

/**
 * Tool for rendering Weather Objects to a Blip format.
 * @author leeclarke
 */
public class WeatherBlipRenderer
{

	/**
	 * Simply creates a view from the WeatherObservation object.
	 * @param obs
	 * @param weatherBlip
	 */
	public static void renderCurrentConditions(WeatherObservation obs, Blip weatherBlip) throws WWViewException
	{
		if(obs != null && weatherBlip != null)
		{
			StringBuilder sb = new StringBuilder("\n\n\n");
			ObservationLocation loc = obs.getDisplay_location();
			sb.append("Current conditions at ").append(loc.getCity()).append(", ").append(loc.getZip()).append(" ");
			int titleLen = sb.length();
			BlipContentRefs head = weatherBlip.append(sb.toString());
			
			
			sb = new StringBuilder("\n");
			sb.append(obs.observation_time);
			BlipContentRefs temp = weatherBlip.append(sb.toString());
			temp.annotate("style/backgroundColor", "#FFFF99");
			
			sb = new StringBuilder("\nTemprature:");
			sb.append(obs.temp_f).append(" F ( ").append(obs.temp_c).append(" C )");
			
			weatherBlip.append(sb.toString());
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
			
			String wugr = "WeatherUnderground";
			sb = new StringBuilder("\nProvided by ");
			int preLen = sb.length();
			sb.append(wugr);
			weatherBlip.append(sb.toString());
			
			//Annotate link
			BlipContentRefs link = weatherBlip.range(weatherBlip.length()-wugr.length(), weatherBlip.length());
			link.annotate("link/manual", obs.getCredit_URL());
		}
		else
			throw new WWViewException("No weather results were returned.");
	}

}
