package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.service.Exportable;

public class WeatherObservation extends DragonflyDomain
{
	public static final String root = "current_observation";

	@Exportable(xmlName = "credit")
	public String credit;

	@Exportable(xmlName = "credit_URL",  jsonName = "creditURL")
	public String credit_URL;
	
	@Exportable(xmlName = "termsofservice")
	public String termsofservice;

	@Exportable(xmlName = "station_id", jsonName = "stationId")
	public String station_id;
	
	@Exportable(xmlName = "observation_time",  jsonName = "observationTime")
	public String observation_time;
	
	@Exportable(xmlName = "observation_time_rfc822",  jsonName = "observationTimeRfc822")
	public String observation_time_rfc822;
	
	@Exportable(xmlName = "observation_epoch",  jsonName = "observationEpoch")
	public String observation_epoch;
	
	@Exportable(xmlName = "local_time",  jsonName = "localTime")
	public String local_time;
	
	@Exportable(xmlName = "local_time_rfc822",  jsonName = "creditURL")
	public String local_time_rfc822;
	
	@Exportable(xmlName = "local_epoch",  jsonName = "creditURL")
	public String local_epoch;
	
	@Exportable(xmlName = "weather")
	public String weather;
	
	@Exportable(xmlName = "temperature_string",  jsonName = "temperatureString")
	public String temperature_string;
	
	@Exportable(xmlName = "temp_f",  jsonName = "tempF")
	public String temp_f;
	
	@Exportable(xmlName = "temp_c",  jsonName = "tempC")
	public String temp_c;
	
	@Exportable(xmlName = "relative_humidity",  jsonName = "relativeHumidity")
	public String relative_humidity;
	
	@Exportable(xmlName = "wind_string",  jsonName = "windString")
	public String wind_string;
	
	@Exportable(xmlName = "wind_dir",  jsonName = "winDir")
	public String wind_dir;
	
	@Exportable(xmlName = "wind_degrees",  jsonName = "windDegrees")
	public String wind_degrees;
	
	@Exportable(xmlName = "wind_mph",  jsonName = "windMph")
	public String wind_mph;
	
	@Exportable(xmlName = "wind_gust_mph",  jsonName = "windGustMph")
	public String wind_gust_mph;
	
	@Exportable(xmlName = "pressure_string",  jsonName = "pressureString")
	public String pressure_string;
	
	@Exportable(xmlName = "pressure_mb",  jsonName = "pressureMb")
	public String pressure_mb;
	
	@Exportable(xmlName = "pressure_in",  jsonName = "pressureIn")
	public String pressure_in;
	
	@Exportable(xmlName = "dewpoint_string",  jsonName = "dewpointString")
	public String dewpoint_string;
	
	@Exportable(xmlName = "dewpoint_f",  jsonName = "dewpointF")
	public String dewpoint_f;
	
	@Exportable(xmlName = "dewpoint_c",  jsonName = "dewpointC")
	public String dewpoint_c;
	
	@Exportable(xmlName = "heat_index_string", jsonName = "heatIndexString")
	public String heat_index_string;
	
	@Exportable(xmlName = "heat_index_f", jsonName = "heatIndexF")
	public String heat_index_f;
	
	@Exportable(xmlName = "heat_index_c", jsonName = "heatIndexC")
	public String heat_index_c;
	
	@Exportable(xmlName = "windchill_string", jsonName= "windchillString")
	public String windchill_string;
	
	@Exportable(xmlName = "windchill_f", jsonName = "windchillF")
	public String windchill_f;
	
	@Exportable(xmlName = "windchill_c", jsonName = "windchillC")
	public String windchill_c;
	
	@Exportable(xmlName = "visibility_mi", jsonName = "visibilityMi")
	public String visibility_mi;
	
	@Exportable(xmlName = "visibility_km", jsonName = "visibilityKm")
	public String visibility_km;
	
	@Exportable(xmlName = "forecast_url", jsonName = "forecastUrl")
	public String forecast_url;
	
	@Exportable(xmlName = "history_url", jsonName = "historyUrl")
	public String history_url;
	
	@Exportable(xmlName = "ob_url", jsonName = "obUrl")
	public String ob_url;
	
	@Exportable(xmlName = "image", jsonName = "wuImage")
	public WuImage image;
	
	@Exportable(xmlName = "display_location", jsonName = "displayLocation")
	public ObservationLocation display_location;
	
	@Exportable(xmlName = "observation_location", jsonName = "observationLocation")
	public ObservationLocation observation_location;

	public WeatherObservation()
	{
		super();
	}

	public String getCredit()
	{
		return credit;
	}

	public void setCredit(String credit)
	{
		this.credit = credit;
	}

	public String getCredit_URL()
	{
		return credit_URL;
	}

	public void setCredit_URL(String creditURL)
	{
		credit_URL = creditURL;
	}

	public String getTermsofservice()
	{
		return termsofservice;
	}

	public void setTermsofservice(String termsofservice)
	{
		this.termsofservice = termsofservice;
	}

	public String getStation_id()
	{
		return station_id;
	}

	public void setStation_id(String stationId)
	{
		station_id = stationId;
	}

	public String getObservation_time()
	{
		return observation_time;
	}

	public void setObservation_time(String observationTime)
	{
		observation_time = observationTime;
	}

	public String getObservation_time_rfc822()
	{
		return observation_time_rfc822;
	}

	public void setObservation_time_rfc822(String observationTimeRfc822)
	{
		observation_time_rfc822 = observationTimeRfc822;
	}

	public String getObservation_epoch()
	{
		return observation_epoch;
	}

	public void setObservation_epoch(String observationEpoch)
	{
		observation_epoch = observationEpoch;
	}

	public String getLocal_time()
	{
		return local_time;
	}

	public void setLocal_time(String localTime)
	{
		local_time = localTime;
	}

	public String getLocal_time_rfc822()
	{
		return local_time_rfc822;
	}

	public void setLocal_time_rfc822(String localTimeRfc822)
	{
		local_time_rfc822 = localTimeRfc822;
	}

	public String getLocal_epoch()
	{
		return local_epoch;
	}

	public void setLocal_epoch(String localEpoch)
	{
		local_epoch = localEpoch;
	}

	public String getWeather()
	{
		return weather;
	}

	public void setWeather(String weather)
	{
		this.weather = weather;
	}

	public String getTemperature_string()
	{
		return temperature_string;
	}

	public void setTemperature_string(String temperatureString)
	{
		temperature_string = temperatureString;
	}

	public String getTemp_f()
	{
		return temp_f;
	}

	public void setTemp_f(String tempF)
	{
		temp_f = tempF;
	}

	public String getTemp_c()
	{
		return temp_c;
	}

	public void setTemp_c(String tempC)
	{
		temp_c = tempC;
	}

	public String getRelative_humidity()
	{
		return relative_humidity;
	}

	public void setRelative_humidity(String relativeHumidity)
	{
		relative_humidity = relativeHumidity;
	}

	public String getWind_string()
	{
		return wind_string;
	}

	public void setWind_string(String windString)
	{
		wind_string = windString;
	}

	public String getWind_dir()
	{
		return wind_dir;
	}

	public void setWind_dir(String windDir)
	{
		wind_dir = windDir;
	}

	public String getWind_degrees()
	{
		return wind_degrees;
	}

	public void setWind_degrees(String windDegrees)
	{
		wind_degrees = windDegrees;
	}

	public String getWind_mph()
	{
		return wind_mph;
	}

	public void setWind_mph(String windMph)
	{
		wind_mph = windMph;
	}

	public String getWind_gust_mph()
	{
		return wind_gust_mph;
	}

	public void setWind_gust_mph(String windGustMph)
	{
		wind_gust_mph = windGustMph;
	}

	public String getPressure_string()
	{
		return pressure_string;
	}

	public void setPressure_string(String pressureString)
	{
		pressure_string = pressureString;
	}

	public String getPressure_mb()
	{
		return pressure_mb;
	}

	public void setPressure_mb(String pressureMb)
	{
		pressure_mb = pressureMb;
	}

	public String getPressure_in()
	{
		return pressure_in;
	}

	public void setPressure_in(String pressureIn)
	{
		pressure_in = pressureIn;
	}

	public String getDewpoint_string()
	{
		return dewpoint_string;
	}

	public void setDewpoint_string(String dewpointString)
	{
		dewpoint_string = dewpointString;
	}

	public String getDewpoint_f()
	{
		return dewpoint_f;
	}

	public void setDewpoint_f(String dewpointF)
	{
		dewpoint_f = dewpointF;
	}

	public String getDewpoint_c()
	{
		return dewpoint_c;
	}

	public void setDewpoint_c(String dewpointC)
	{
		dewpoint_c = dewpointC;
	}

	public String getHeat_index_string()
	{
		return heat_index_string;
	}

	public void setHeat_index_string(String heatIndexString)
	{
		heat_index_string = heatIndexString;
	}

	public String getHeat_index_f()
	{
		return heat_index_f;
	}

	public void setHeat_index_f(String heatIndexF)
	{
		heat_index_f = heatIndexF;
	}

	public String getHeat_index_c()
	{
		return heat_index_c;
	}

	public void setHeat_index_c(String heatIndexC)
	{
		heat_index_c = heatIndexC;
	}

	public String getWindchill_string()
	{
		return windchill_string;
	}

	public void setWindchill_string(String windchillString)
	{
		windchill_string = windchillString;
	}

	public String getWindchill_f()
	{
		return windchill_f;
	}

	public void setWindchill_f(String windchillF)
	{
		windchill_f = windchillF;
	}

	public String getWindchill_c()
	{
		return windchill_c;
	}

	public void setWindchill_c(String windchillC)
	{
		windchill_c = windchillC;
	}

	public String getVisibility_mi()
	{
		return visibility_mi;
	}

	public void setVisibility_mi(String visibilityMi)
	{
		visibility_mi = visibilityMi;
	}

	public String getVisibility_km()
	{
		return visibility_km;
	}

	public void setVisibility_km(String visibilityKm)
	{
		visibility_km = visibilityKm;
	}

	public String getForecast_url()
	{
		return forecast_url;
	}

	public void setForecast_url(String forecastUrl)
	{
		forecast_url = forecastUrl;
	}

	public String getHistory_url()
	{
		return history_url;
	}

	public void setHistory_url(String historyUrl)
	{
		history_url = historyUrl;
	}

	public String getOb_url()
	{
		return ob_url;
	}

	public void setOb_url(String obUrl)
	{
		ob_url = obUrl;
	}

	public WuImage getImage()
	{
		return image;
	}

	public void setImage(WuImage image)
	{
		this.image = image;
	}

	public ObservationLocation getDisplay_location()
	{
		return display_location;
	}

	public void setDisplay_location(ObservationLocation displayLocation)
	{
		display_location = displayLocation;
	}

	public ObservationLocation getObservation_location()
	{
		return observation_location;
	}

	public void setObservation_location(ObservationLocation observationLocation)
	{
		observation_location = observationLocation;
	}

	

}
