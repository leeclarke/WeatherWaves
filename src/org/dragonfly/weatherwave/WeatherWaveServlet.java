package org.dragonfly.weatherwave;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class WeatherWaveServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/plain");
		resp.getWriter().println("WeatherWave Test Page. App Coming soon!");
	}
}
