package org.dragonfly.wunderground.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Extension of the Sax <code>DefaultHandler</code> simply to externalize
 * reusable methods.
 * 
 * @author leeclarke
 */
public abstract class DragonflySaxHandler extends DefaultHandler
{
	private static final Logger logger = Logger.getLogger(DragonflySaxHandler.class);
	protected StringBuilder builder;
	protected LinkedList<String> tagStack = new LinkedList<String>();

	/**
	 * No-op calls super
	 */
	public DragonflySaxHandler()
	{
		super();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}

	/**
	 * @return - item before the last on the Tag processing stack.
	 */
	protected String getNextToLastOnStack()
	{
		String nextToLastTag = "";
		if (this.tagStack.size() > 1)
			nextToLastTag = this.tagStack.get(this.tagStack.size() - 2);

		return nextToLastTag;
	}

	/**
	 * Helper method because the code just keeps repeating..
	 * 
	 * @param dbean
	 *            - Domain bean to set
	 * @param name
	 *            - field to set.
	 */
	protected void setBeanValue(DragonflyDomain dbean, String name)
	{
		if (dbean.fields.contains(name))
		{
			try
			{
				String mthName = BeanUtil.getMethodName(name);
				if (logger.isDebugEnabled())
					logger.debug("call:" + mthName);
				BeanUtil.invokeMethod(dbean, mthName, new Object[] { builder.toString().trim() }, null);
			} catch (Exception e)
			{
				logger.error("Error invoking Method for: " + name, e);
			}
		}

	}

	public abstract List<? extends DragonflyDomain> getRootItems();

}