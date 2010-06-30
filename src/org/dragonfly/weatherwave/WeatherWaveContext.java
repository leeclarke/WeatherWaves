package org.dragonfly.weatherwave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

/**
 * @author leeclarke
 *
 */
public class WeatherWaveContext
{
	private static final Logger logger = Logger.getLogger(WeatherWaveContext.class.getName());
	
	public static enum ENVIRONMENT{TEST, LOCAL, PROD}
	
	public static ENVIRONMENT CURR_ENV = ENVIRONMENT.TEST;
	
	public static String templatePath = null;

	public static String PROP_FILE_NAME = "weatherwave.properties";
	
	public static Properties props = null; 
	
	/**
	 * Loads the apps poroperty file.
	 */
	public Properties loadProperties(boolean reload)
	{
		if(reload || props == null)
		{	
			props = new Properties();
			try
			{
				File propsLoc = new File(templatePath + File.pathSeparator + PROP_FILE_NAME);
				if(!propsLoc.exists())
					throw new FileNotFoundException("Cant Find Properties file at" + propsLoc.getAbsolutePath());
				props.load(new FileReader(propsLoc));
				
				
			} catch (Exception e)
			{
				logger.warning(e.getMessage());
			}
		}
		
		return props;
	}
	
	/**
	 * Sets the Web Server environment if applicable. This helps determine pathing for loading properties and StringTemplate.
	 * @param context
	 */
	public void setAppEnvironment(ServletContext context)
	{
		if(context == null)
			return;
		
		if (context.getServerInfo().startsWith("Dev")) {
		    CURR_ENV = ENVIRONMENT.LOCAL;
		} else {
			CURR_ENV = ENVIRONMENT.PROD;
		} 

	}
	
}
