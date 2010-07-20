package org.dragonfly.wunderground.service;
//TODO: Look into to using HTTPClient, might be improved performance. said to retry three times if it cannot connect to the server. Good for mobile apps
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 *
 */
public abstract class BaseFeedParser implements FeedParser
{
	
	private static final Logger logger = Logger.getLogger(BaseFeedParser.class);
	private final URL feedUrl;
	private String proxyPswd;
	private String proxyUid;
	private boolean useProxy = false;

	/**
	 * @param feedUrl - XML URL
	 */
	protected BaseFeedParser(String feedUrl)
	{
		try
		{
			this.feedUrl = new URL(feedUrl);
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Attempts to open the XML doc and throws a RuntimeException if fails.
	 * @return - Buffered for performance improvements in case of poor connection quality, etc.
	 */
	protected BufferedInputStream getInputStream()
	{
		byte [] b = new byte[1];

		try
		{
			if(useProxy)
			{
			Authenticator.setDefault(new Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			        return new
			           PasswordAuthentication(proxyUid,proxyPswd.toCharArray());
			    }});
			}
			
		    HttpURLConnection con = (HttpURLConnection) feedUrl.openConnection();
			return new BufferedInputStream(con.getInputStream());
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Sets up proxy data indicating that the parser should use a proxy for data retrieval.
	 * @param host
	 * @param uid
	 * @param pswd
	 */
	public void setProxyData(String host, String uid, String pswd)
	{
		StringTokenizer st = new StringTokenizer(host, ":");
		String server = st.nextToken();
		String port = st.hasMoreTokens()? st.nextToken():"80";
		System.setProperty("http.proxyHost", server);
		System.setProperty("http.proxyPort", port);
		this.proxyUid = uid;
		this.proxyPswd = pswd;
		this.useProxy  = true;
	}
}