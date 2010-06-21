package org.dragonfly.weatherwave;

import com.google.wave.api.AbstractRobot;
import com.google.wave.api.Blip;
import com.google.wave.api.event.WaveletParticipantsChangedEvent;
import com.google.wave.api.event.WaveletSelfAddedEvent;

public class WeatherWaveRobot extends AbstractRobot
{

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
		return super.getRobotAvatarUrl();
	}

	@Override
	public void onWaveletSelfAdded(WaveletSelfAddedEvent event)
	{
		Blip blip = event.getWavelet().reply("\nHi everybody!");
	}

	@Override
	public void onWaveletParticipantsChanged(WaveletParticipantsChangedEvent event)
	{
		for (String newParticipant : event.getParticipantsAdded())
		{
			Blip blip = event.getWavelet().reply("\nHi : " + newParticipant);
		}
	}
}
