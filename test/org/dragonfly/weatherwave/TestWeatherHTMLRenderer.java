package org.dragonfly.weatherwave;

import java.util.List;

import junit.framework.TestCase;

import org.dragonfly.wunderground.domain.WeatherObservation;
import org.dragonfly.wunderground.exception.DragonflySaxException;
import org.dragonfly.wunderground.service.WUService;

public class TestWeatherHTMLRenderer extends TestCase
{
	
	public void testRenderHTMLListOfDragonflyDomain() throws DragonflySaxException
	{
		WUService wuServ = new WUService();
		
		List<WeatherObservation> rtn = wuServ.getCurrentConditions("33602");
		
		assertNotNull("Test Precondition failed.",rtn);
		System.out.println("\n\n\nresults="+rtn);
		assertNotNull(rtn.get(0).getDisplay_location());
		
		String htmlRend = WeatherHTMLRenderer.renderHTML(rtn); 
		assertNotNull(htmlRend);
		assertTrue(htmlRend.length()>1);
		System.out.println("htmlRend  ==\n"+htmlRend);
		
		//TODO: Template works but Location Objects are returning Null!
	}

	
	public void testRenderHTMLDragonflyDomain()
	{
		fail("Not yet implemented"); // TODO
	}
	
}