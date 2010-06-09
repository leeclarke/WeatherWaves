package org.dragonfly.wunderground.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
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
	 * @return
	 */
	protected InputStream getInputStream()
	{
		//System.setProperty("java.net.useSystemProxies", "true");
		byte [] b = new byte[1];

		try
		{
			Authenticator.setDefault(new Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			        return new
			           PasswordAuthentication(proxyUid,proxyPswd.toCharArray());
			    }});

			
		    HttpURLConnection con = (HttpURLConnection) feedUrl.openConnection();
			return con.getInputStream();
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void setProxyData(String host, String uid, String pswd)
	{
		StringTokenizer st = new StringTokenizer(host, ":");
		String server = st.nextToken();
		String port = st.hasMoreTokens()? st.nextToken():"80";
		System.setProperty("http.proxyHost", server);
		System.setProperty("http.proxyPort", port);
		this.proxyUid = uid;
		this.proxyPswd = pswd;
	}
	
	protected Proxy getProxy()
	{
		
		List<Proxy> l = null;
		try {
		  l = ProxySelector.getDefault().select(this.feedUrl.toURI());
		}
		catch (URISyntaxException e) {
		  e.printStackTrace();
		}

		if (l != null) {
			for (Proxy proxy : l)
			{
		      System.out.println("proxy hostname : " + proxy.type());
		      InetSocketAddress addr = (InetSocketAddress) proxy.address();
		      if (addr == null) {
		        System.out.println("No Proxy");
		      } 
		      else {
		        System.out.println("proxy hostname : " + addr.getHostName());
		        System.out.println("proxy port : " + addr.getPort());
		      }
		   }
			return l.get(0);
		}
		return Proxy.NO_PROXY;
	}
}