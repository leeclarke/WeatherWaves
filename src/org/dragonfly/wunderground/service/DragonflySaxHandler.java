package org.dragonfly.wunderground.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.DragonflyDomain.BeanAttributeMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Extension of the Sax <code>DefaultHandler</code> simply to externalize reusable methods.
 * 
 * TODO: Most of the code built in the start/endElement overrides could likely be eliminated with the use of a class
 * level tag. The user could easily specify .class information in an init method then the handler could manage the rest.
 * If any field in a root bean could specify that it is a bean object then the handler would know what object to set for
 * each level tag.(This could also work for List collections.)
 * 
 * Example : RootBean MyOtherBean - where this would be annotated as a bean object [@BeanObject(collection=true)]implying that a tag would contain
 * Multiple child tags with further data. An example would be an AddressBean. Person.Address as in Person.workAddress a
 * method to specify this as a List of items would also be useful.
 * 
 * The processing is simple in the start/end Element methods then 
 * - Each BeanObject should be checked to see if the current tag is a BeanObject, if so create or add to parent bean.
 * - if root is NextToLast() then call setters on the BeanObject
 * 
 * if done right it might eliminate customHandlers for most situations!
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
		builder.append(ch, start, length);
	}

	/**
	 * @return - item before the last on the Tag processing stack.
	 */
	protected String getNextToLastOnStack()
	{
		String nextToLastTag = "";
		if (this.tagStack.size() > 1) nextToLastTag = this.tagStack.get(this.tagStack.size() - 2);

		return nextToLastTag;
	}

	/**
	 * Helper method which will determine what bean method to call and will also check for tag attributes that need to be set  
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
				if (logger.isDebugEnabled()) logger.debug("call:" + mthName);
				BeanUtil.invokeMethod(dbean, mthName, new Object[] { builder.toString().trim() }, null);
			}
			catch (Exception e)
			{
				logger.error("Error invoking Method for: " + name, e);
			}
		}
	}
	
	/**
	 * @param name
	 * @param attributes
	 */
	protected void setBeanAttributes(DragonflyDomain dbean, String name, Attributes attributes)
	{
		List<BeanAttributeMap> beanAttrs = getAttributes(dbean, name);
		if(dbean != null && attributes != null)
		{
			for (BeanAttributeMap beanAtt : beanAttrs)
			{
				logger.debug(">>>>"+beanAtt);
				String mthName = BeanUtil.getMethodName(beanAtt.getFieldName());
				logger.debug("mthName=="+mthName);
				try
				{
					BeanUtil.invokeMethod(dbean, mthName, new Object[] { attributes.getValue(beanAtt.getXmlName()).trim() }, null);
				}
				catch (Exception e)
				{
					logger.error("Error invoking Method for: " + name, e);
				}
			}
		}
		
	}

	/**
	 * Checks the bean to see if it expects any fields to be set by xml attributes.
	 * @param bean
	 * @param name
	 * @return
	 */
	private List<BeanAttributeMap> getAttributes(DragonflyDomain bean,String name)
	{
		List<BeanAttributeMap> list = null;
		for (BeanAttributeMap attMap : bean.attributeMap)
		{
			if(attMap.equals(name))
			{	
				if(list == null) list = new ArrayList<BeanAttributeMap>();
				list.add(attMap);
			}
		}	
		return list;
	}

	/**
	 * Updates the internal Tag tracking stack so that the handler can easily determine where it is in the tag hiarchy.
	 * 
	 * @param name
	 */
	public void updateStack(String name)
	{
		this.tagStack.add(name); // Add to end of stack
	}

	/**
	 * Maintains the Parsing state and updates the Tag Stack, this must be the last call of your endElement method.
	 */
	public void closeTag()
	{
		builder.setLength(0);
		this.tagStack.removeLast();
	}

	public abstract List<? extends DragonflyDomain> getRootItems();

}