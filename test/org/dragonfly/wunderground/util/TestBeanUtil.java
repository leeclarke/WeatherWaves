package org.dragonfly.wunderground.util;

import junit.framework.TestCase;

import org.dragonfly.wunderground.domain.Location;

public class TestBeanUtil extends TestCase
{
	public void testInvokeMethod() throws Exception
	{
		String expected = "Tampa";
		Location loc = new Location();
		
		BeanUtil.invokeMethod(loc, "setCity", new Object[]{expected}, new Class[]{String.class});
		 
		assertNotNull(loc);
		assertNotNull(loc.getCity());
		assertEquals(expected, loc.getCity());
	}

	public void testInvokeMethod_UnspecifiedParams() throws Exception
	{
		String expected = "Tampa";
		Location loc = new Location();
		
		BeanUtil.invokeMethod(loc, "setCity", new Object[]{expected}, null);
		 
		assertNotNull(loc);
		assertNotNull(loc.getCity());
		assertEquals(expected, loc.getCity());
	}
	
	public void testGetMethodName()
	{
		String property = "state";
		String expected = "setState";
		String rtnName = BeanUtil.getSetterMethodName(property);
		
		assertEquals(expected, rtnName);
	}

}
