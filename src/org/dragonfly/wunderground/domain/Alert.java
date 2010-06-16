package org.dragonfly.wunderground.domain;

import org.dragonfly.wunderground.service.Exportable;
import org.dragonfly.wunderground.util.ExportableAttribute;


/**
 * Contains data resulting from an Alert notification
 * @author leeclarke
 */
public class Alert extends DragonflyDomain
{
	public static final String root = "AlertItem";
	
	@Exportable(xmlName = "type")
	private String type;
	
	@Exportable(xmlName = "description")
	private String description;
	
	@Exportable(xmlName = "date")
	private String date;
	
	@Exportable(xmlName = "expires")
	private String expires;

	@ExportableAttribute(tag = "date", xmlName = "epoch", jsonName = "dateEpoch")
	private String dateEpoch;

	@ExportableAttribute(tag = "expires", xmlName = "epoch", jsonName = "expiresEpoch")
	private String expiresEpoch;

	@Exportable(xmlName = "message")
	private String message;
	
	@Exportable(xmlName = "phenomena")
	private String phenomena;
	
	@Exportable(xmlName = "significance")
	private String significance;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getExpires()
	{
		return expires;
	}

	public void setExpires(String expires)
	{
		this.expires = expires;
	}

	public String getDateEpoch()
	{
		return dateEpoch;
	}

	public void setDateEpoch(String dateEpoch)
	{
		this.dateEpoch = dateEpoch;
	}

	public String getExpiresEpoch()
	{
		return expiresEpoch;
	}

	public void setExpiresEpoch(String expiresEpoch)
	{
		this.expiresEpoch = expiresEpoch;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getPhenomena()
	{
		return phenomena;
	}

	public void setPhenomena(String phenomena)
	{
		this.phenomena = phenomena;
	}

	public String getSignificance()
	{
		return significance;
	}

	public void setSignificance(String significance)
	{
		this.significance = significance;
	}
}