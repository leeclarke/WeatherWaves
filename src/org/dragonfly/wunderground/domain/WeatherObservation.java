package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.service.Exportable;

public class WeatherObservation extends DragonflyDomain
{
	public static final String root = "current_observation";

	@Exportable(jsonName = "credit")
	public String credit;

	@Exportable(jsonName = "credit_URL",  xmlName = "creditURL")
	public String credit_URL;
	
	@Exportable(jsonName = "termsofservice")
	public String termsofservice;

	@Exportable(jsonName = "station_id", xmlName = "stationId")
	public String stationId;
	
	@Exportable(jsonName = "observation_time",  xmlName = "observationTime")
	public String observationTime;
	
	@Exportable(jsonName = "observation_time_rfc822",  xmlName = "observationTimeRfc822")
	public String observationTimeRfc822;
	
	@Exportable(jsonName = "observation_epoch",  xmlName = "observationEpoch")
	public String observationEpoch;
	
	@Exportable(jsonName = "local_time",  xmlName = "localTime")
	public String localTime;
	
	@Exportable(jsonName = "local_time_rfc822",  xmlName = "creditURL")
	public String local_time_rfc822;
	
	@Exportable(jsonName = "local_epoch",  xmlName = "creditURL")
	public String local_epoch;
	
	@Exportable(jsonName = "weather")
	public String weather;
	
	@Exportable(jsonName = "temperature_string",  xmlName = "temperatureString")
	public String temperatureString;
	
	@Exportable(jsonName = "temp_f",  xmlName = "tempF")
	public String tempF;
	
	@Exportable(jsonName = "temp_c",  xmlName = "tempC")
	public String tempC;
	
	@Exportable(jsonName = "relative_humidity",  xmlName = "relativeHumidity")
	public String relativeHumidity;
	
	@Exportable(jsonName = "wind_string",  xmlName = "windString")
	public String windString;
	
	@Exportable(jsonName = "wind_dir",  xmlName = "winDir")
	public String windDir;
	
	@Exportable(jsonName = "wind_degrees",  xmlName = "windDegrees")
	public String wind_degrees;
	
	@Exportable(jsonName = "wind_mph",  xmlName = "windMph")
	public String windMph;
	
	@Exportable(jsonName = "wind_gust_mph",  xmlName = "windGustMph")
	public String windGustMph;
	
	@Exportable(jsonName = "pressure_string",  xmlName = "pressureString")
	public String pressureString;
	
	@Exportable(jsonName = "pressure_mb",  xmlName = "pressureMb")
	public String pressureMb;
	
	@Exportable(jsonName = "pressure_in",  xmlName = "pressureIn")
	public String pressureIn;
	
	@Exportable(jsonName = "dewpoint_string",  xmlName = "dewpointString")
	public String dewpointString;
	
	@Exportable(jsonName = "dewpoint_f",  xmlName = "dewpointF")
	public String dewpointF;
	
	@Exportable(jsonName = "dewpoint_c",  xmlName = "dewpointC")
	public String dewpointC;
	
	@Exportable(jsonName = "heat_index_string", xmlName = "heatIndexString")
	public String heatIndexString;
	
	@Exportable(jsonName = "heat_index_f", xmlName = "heatIndexF")
	public String heatIndexF;
	
	@Exportable(jsonName = "heat_index_c", xmlName = "heatIndexC")
	public String heatIndexC;
	
	@Exportable(jsonName = "windchill_string", xmlName = "windchillString")
	public String windchillString;
	
	@Exportable(jsonName = "windchill_f", xmlName = "windchillF")
	public String windchillF;
	
	@Exportable(jsonName = "windchill_c", xmlName = "windchillC")
	public String windchillC;
	
	@Exportable(jsonName = "visibility_mi", xmlName = "visibilityMi")
	public String visibilityMi;
	
	@Exportable(jsonName = "visibility_km", xmlName = "visibilityKm")
	public String visibilityKm;
	
	@Exportable(jsonName = "forecast_url", xmlName = "forecastUrl")
	public String forecastUrl;
	
	@Exportable(jsonName = "history_url", xmlName = "historyUrl")
	public String historyUrl;
	
	@Exportable(jsonName = "ob_url",xmlName = "obUrl")
	public String obUrl;
	
	@Exportable(jsonName = "image", xmlName = "wuImage")
	public WuImage wuImage;
	
	@Exportable(jsonName = "display_location", xmlName = "displayLocation")
	public ObservationLocation displayLocation;
	
	@Exportable(jsonName = "observation_locatio", xmlName = "observationLocation")
	public ObservationLocation observationLocation;

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

	public String getStationId()
	{
		return stationId;
	}

	public void setStationId(String stationId)
	{
		this.stationId = stationId;
	}

	public String getObservationTime()
	{
		return observationTime;
	}

	public void setObservationTime(String observationTime)
	{
		this.observationTime = observationTime;
	}

	public String getObservationTimeRfc822()
	{
		return observationTimeRfc822;
	}

	public void setObservationTimeRfc822(String observationTimeRfc822)
	{
		this.observationTimeRfc822 = observationTimeRfc822;
	}

	public String getObservationEpoch()
	{
		return observationEpoch;
	}

	public void setObservationEpoch(String observationEpoch)
	{
		this.observationEpoch = observationEpoch;
	}

	public String getLocalTime()
	{
		return localTime;
	}

	public void setLocalTime(String localTime)
	{
		this.localTime = localTime;
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

	public String getTemperatureString()
	{
		return temperatureString;
	}

	public void setTemperatureString(String temperatureString)
	{
		this.temperatureString = temperatureString;
	}

	public String getTempF()
	{
		return tempF;
	}

	public void setTempF(String tempF)
	{
		this.tempF = tempF;
	}

	public String getTempC()
	{
		return tempC;
	}

	public void setTempC(String tempC)
	{
		this.tempC = tempC;
	}

	public String getRelativeHumidity()
	{
		return relativeHumidity;
	}

	public void setRelativeHumidity(String relativeHumidity)
	{
		this.relativeHumidity = relativeHumidity;
	}

	public String getWindString()
	{
		return windString;
	}

	public void setWindString(String windString)
	{
		this.windString = windString;
	}

	public String getWindDir()
	{
		return windDir;
	}

	public void setWindDir(String windDir)
	{
		this.windDir = windDir;
	}

	public String getWind_degrees()
	{
		return wind_degrees;
	}

	public void setWind_degrees(String windDegrees)
	{
		wind_degrees = windDegrees;
	}

	public String getWindMph()
	{
		return windMph;
	}

	public void setWindMph(String windMph)
	{
		this.windMph = windMph;
	}

	public String getWindGustMph()
	{
		return windGustMph;
	}

	public void setWindGustMph(String windGustMph)
	{
		this.windGustMph = windGustMph;
	}

	public String getPressureString()
	{
		return pressureString;
	}

	public void setPressureString(String pressureString)
	{
		this.pressureString = pressureString;
	}

	public String getPressureMb()
	{
		return pressureMb;
	}

	public void setPressureMb(String pressureMb)
	{
		this.pressureMb = pressureMb;
	}

	public String getPressureIn()
	{
		return pressureIn;
	}

	public void setPressureIn(String pressureIn)
	{
		this.pressureIn = pressureIn;
	}

	public String getDewpointString()
	{
		return dewpointString;
	}

	public void setDewpointString(String dewpointString)
	{
		this.dewpointString = dewpointString;
	}

	public String getDewpointF()
	{
		return dewpointF;
	}

	public void setDewpointF(String dewpointF)
	{
		this.dewpointF = dewpointF;
	}

	public String getDewpointC()
	{
		return dewpointC;
	}

	public void setDewpointC(String dewpointC)
	{
		this.dewpointC = dewpointC;
	}

	public String getHeatIndexString()
	{
		return heatIndexString;
	}

	public void setHeatIndexString(String heatIndexString)
	{
		this.heatIndexString = heatIndexString;
	}

	public String getHeatIndexF()
	{
		return heatIndexF;
	}

	public void setHeatIndexF(String heatIndexF)
	{
		this.heatIndexF = heatIndexF;
	}

	public String getHeatIndexC()
	{
		return heatIndexC;
	}

	public void setHeatIndexC(String heatIndexC)
	{
		this.heatIndexC = heatIndexC;
	}

	public String getWindchillString()
	{
		return windchillString;
	}

	public void setWindchillString(String windchillString)
	{
		this.windchillString = windchillString;
	}

	public String getWindchillF()
	{
		return windchillF;
	}

	public void setWindchillF(String windchillF)
	{
		this.windchillF = windchillF;
	}

	public String getWindchillC()
	{
		return windchillC;
	}

	public void setWindchillC(String windchillC)
	{
		this.windchillC = windchillC;
	}

	public String getVisibilityMi()
	{
		return visibilityMi;
	}

	public void setVisibilityMi(String visibilityMi)
	{
		this.visibilityMi = visibilityMi;
	}

	public String getVisibilityKm()
	{
		return visibilityKm;
	}

	public void setVisibilityKm(String visibilityKm)
	{
		this.visibilityKm = visibilityKm;
	}

	public String getForecastUrl()
	{
		return forecastUrl;
	}

	public void setForecastUrl(String forecastUrl)
	{
		this.forecastUrl = forecastUrl;
	}

	public String getHistoryUrl()
	{
		return historyUrl;
	}

	public void setHistoryUrl(String historyUrl)
	{
		this.historyUrl = historyUrl;
	}

	public String getObUrl()
	{
		return obUrl;
	}

	public void setObUrl(String obUrl)
	{
		this.obUrl = obUrl;
	}

}
