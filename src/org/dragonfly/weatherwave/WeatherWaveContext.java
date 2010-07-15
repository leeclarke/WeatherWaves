package org.dragonfly.weatherwave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

/**
 * Provide a server context as well as configuration from properties file.
 * @author leeclarke
 */
public class WeatherWaveContext
{
	private static final Logger logger = Logger.getLogger(WeatherWaveContext.class.getName());
	
	public static enum ENVIRONMENT{TEST, LOCAL, PROD}
	
	public static ENVIRONMENT CURR_ENV = ENVIRONMENT.TEST;
	
	public static String templatePath = null;

	public static String PROP_FILE_NAME = "weatherwave.properties";
	
	public static String WEB_PATH = "WEB-INF";
	
	public static Properties props = null;

	private static File PROPERTY_FILE_PATH; 
	
	/**
	 * Loads the apps poroperty file.
	 */
	public static Properties loadProperties(boolean reload)
	{
		if(reload || props == null)
		{	
			props = new Properties();
			try
			{
				String fullPath = templatePath + File.pathSeparator + WEB_PATH +  File.pathSeparator + PROP_FILE_NAME;
				if(CURR_ENV != ENVIRONMENT.PROD)
					fullPath = templatePath + File.pathSeparator + PROP_FILE_NAME;
				File propsLoc = new File(fullPath);
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
	 * Loads for GAE only at moment but should handle a null context for test..
	 * @param context
	 * @return
	 */
	public static Properties loadProperties(ServletContext context)
	{
		if(context != null)
		{	
			String tempPath = new File(context.getRealPath(WeatherWaveContext.PROP_FILE_NAME)).getParentFile().getAbsolutePath() + File.separator + WEB_PATH + File.separator + PROP_FILE_NAME;
			logger.warning("@@@@  "+tempPath);
			PROPERTY_FILE_PATH =new File(tempPath);
			logger.warning("PROP_FILE_NAME "+PROPERTY_FILE_PATH);
			props = new Properties();
			try
			{
//				String fullPath = templatePath + File.separator + WEB_PATH +  File.separator + PROP_FILE_NAME;
//				if(CURR_ENV != ENVIRONMENT.PROD)
//					fullPath = templatePath + File.separator + PROP_FILE_NAME;
//				File propsLoc = new File(PROPERTY_FILE_PATH);
				if(!PROPERTY_FILE_PATH.exists())
					throw new FileNotFoundException("Cant Find Properties file at" + PROPERTY_FILE_PATH.getAbsolutePath());
				
				props.load(new FileReader(PROPERTY_FILE_PATH));
				
				
			} catch (Exception e)
			{
				logger.warning(e.getMessage());
			}
		}
		else
		{
			//TODO: add for null context.. JUnit
		}
		logger.warning("props==     "+WeatherWaveContext.props);
		return props;
	}
	/**
	 * Sets the Web Server environment if applicable. This helps determine pathing for loading properties and StringTemplate.
	 * @param context
	 */
	public static void setAppEnvironment(ServletContext context)
	{
		if(context == null)
			return;
		
		if (context.getServerInfo().startsWith("Dev")) {
		    CURR_ENV = ENVIRONMENT.LOCAL;
		} else {
			CURR_ENV = ENVIRONMENT.PROD;
		} 

	}
	
	
	public static String getStringTempPath()
	{
		
		return PROPERTY_FILE_PATH.getParentFile().getAbsolutePath();
	}
}
