package org.dragonfly.weatherwave.view;

import java.util.List;

import org.dragonfly.weatherwave.exception.WWViewException;
import org.dragonfly.wunderground.domain.Alert;
import org.dragonfly.wunderground.domain.Forecast;
import org.dragonfly.wunderground.domain.ForecastDay;
import org.dragonfly.wunderground.domain.ObservationLocation;
import org.dragonfly.wunderground.domain.WeatherObservation;

import com.google.wave.api.Blip;
import com.google.wave.api.BlipContentRefs;

/**
 * Tool for rendering Weather Objects to a Blip format.
 * 
 * @author leeclarke
 */
public class WeatherBlipRenderer
{

	/**
	 * Simply creates a view from the WeatherObservation object.
	 * 
	 * @param obs
	 * @param weatherBlip
	 */
	public static void renderCurrentConditions(WeatherObservation obs, Blip weatherBlip) throws WWViewException
	{
		if (obs != null && weatherBlip != null)
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
			// temp.annotate("style/fontWeight", "none");
			temp.annotate("style/backgroundColor", "#FFFF99");
			// Humidity
			sb = new StringBuilder("\nHumidity:");
			sb.append(obs.getRelative_humidity());
			weatherBlip.append(sb.toString());

			// Wind
			sb = new StringBuilder("\nWind:");
			sb.append(obs.getWind_string());
			weatherBlip.append(sb.toString());

			// Pressure
			sb = new StringBuilder("\nBarometric Pressure:");
			sb.append(obs.getPressure_string());
			weatherBlip.append(sb.toString());

			// Dewpoint
			sb = new StringBuilder("\nDewPoint:");
			sb.append(obs.getDewpoint_string());
			weatherBlip.append(sb.toString());

			// Heat Index
			sb = new StringBuilder("\nHeat Index:");
			sb.append(obs.getHeat_index_string());
			weatherBlip.append(sb.toString());

			// Wind Chill
			sb = new StringBuilder("\nWind Chill:");
			sb.append(obs.getWindchill_string());
			weatherBlip.append(sb.toString());

			// Visibility
			sb = new StringBuilder("\nVisibility:");
			sb.append(obs.getVisibility_mi()).append("mi (");
			sb.append(obs.getVisibility_km()).append(" km)");
			weatherBlip.append(sb.toString());

			BlipContentRefs title = weatherBlip.range(0, titleLen);
			title.annotate("style/fontWeight", "bold");
			title.annotate("style/backgroundColor", "#3399FF");

			String wugr = "WeatherUnderground";
			sb = new StringBuilder("\nProvided by ");
			sb.append(wugr);
			weatherBlip.append(sb.toString());

			// Annotate link
			BlipContentRefs link = weatherBlip.range(weatherBlip.length() - wugr.length(), weatherBlip.length());
			link.annotate("link/manual", obs.getCredit_URL());
		} else
			throw new WWViewException("No weather results were returned.");
	}

	/**
	 * @param alerts
	 * @param weatherBlip
	 * @throws WWViewException
	 */
	public static void renderAlerts(List<Alert> alerts, Blip weatherBlip, String query) throws WWViewException
	{
		String title = "Weather Alerts for " + query;
		weatherBlip.append(title);
		weatherBlip.range(0, title.length() + 1).annotate("style/backgroundColor", "#3399FF").annotate("style/fontWeight", "bold");
		int pass = 0;
		for (Alert alert : alerts)
		{
			Blip anAlert = weatherBlip.insertInlineBlip(weatherBlip.length());
			StringBuilder sb = new StringBuilder("\n");
			if (pass > 0)
				sb.append("\n");
			sb.append(alert.getDescription());
			anAlert.append(sb.toString()).annotate("style/backgroundColor", "#FFFF99").annotate("style/fontWeight", "none");

			sb = new StringBuilder("\n");
			sb.append("Date: ").append(alert.getDate());
			anAlert.append(sb.toString());

			sb = new StringBuilder("\n");
			sb.append("Expires: ").append(alert.getExpires());
			anAlert.append(sb.toString());

			sb = new StringBuilder("\nMessage:\n");
			sb.append(alert.getMessage());
			anAlert.append(sb.toString());

			String wugr = "WeatherUnderground";
			sb = new StringBuilder("\nProvided by ");
			sb.append(wugr);
			anAlert.append(sb.toString());

			// Annotate link
			BlipContentRefs link = anAlert.range(anAlert.length() - wugr.length(), anAlert.length());
			link.annotate("link/manual", "http://www/wunderground.com");

			anAlert.all(alert.getDescription()).annotate("style/fontWeight", "bold").annotate("style/color", "red");

			pass++;
		}
	}

	/**
	 * @param forecasts
	 * @param weatherBlip
	 * @param query
	 * @throws WWViewException
	 */
	public static void renderForecast(List<Forecast> forecasts, Blip weatherBlip, String query)  throws WWViewException
	{
		//ODO: Finish
		if(forecasts.size() > 0)
		{
			Forecast theForecast = forecasts.get(0);
			String title = "Weather Forecast for " + query;
			weatherBlip.append(title);
			weatherBlip.range(0, title.length()+1).annotate("style/backgroundColor", "#3399FF").annotate("style/fontWeight", "bold");
			for (Forecast forecast : forecasts)
			{
				Blip forecastBlip = weatherBlip.insertInlineBlip(weatherBlip.length());
				List<ForecastDay> txtFc = forecast.getTxt_forecast();
				
			}
		}
	}
}
