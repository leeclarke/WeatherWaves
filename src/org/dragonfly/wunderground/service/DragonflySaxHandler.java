package org.dragonfly.wunderground.service;

import java.util.LinkedList;
import java.util.List;

import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Location;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Extension of the Sax <code>DefaultHandler</code> simply to externalize reusable methods.
 * @author leeclarke
 */
public abstract class DragonflySaxHandler extends DefaultHandler
{

	protected Location currentLocation;
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
		if(this.tagStack.size() >1)
			nextToLastTag = this.tagStack.get(this.tagStack.size()-2);
		
		return nextToLastTag;
	}

	public abstract List<? extends DragonflyDomain> getRootItems();

}