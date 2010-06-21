package org.dragonfly.weatherwave;

import com.google.wave.api.AbstractRobot;
import com.google.wave.api.Blip;
import com.google.wave.api.event.DocumentChangedEvent;
import com.google.wave.api.event.WaveletParticipantsChangedEvent;
import com.google.wave.api.event.WaveletSelfAddedEvent;

public class WeatherWaveRobot extends AbstractRobot
{
	//TODO: Ensure only one match at a time. Currently it could match multiples at once.
	public static final String WW_REG_PATTERN = "@WW\\[.{4}.*\\]";//    regex:  @WW\[.....*\]
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
	
	// Note that "\" is an escape character in Java strings, so it must be double escaped.  @WW[33584]      
	//@WW[123]
	//@WW[]
	//TODO: Determine if need a terminator on the WW call.
	@Capability(filter = WW_REG_PATTERN)
	@Override
	public void onDocumentChanged(DocumentChangedEvent event)
	{
		//TODO: Handle WW call.
	}
}
