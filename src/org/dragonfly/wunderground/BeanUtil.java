package org.dragonfly.wunderground;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtil
{
	
	/**
	 * Invokes a method on the given object.
	 * @param targetObj - 
	 * @param invMethod - method to call.
	 * @param values - values to set params to.
	 * @param paramTypes - param type of the method. Will try to look up method param types if not known.
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static final void invokeMethod(Object targetObj, String invMethod, Object[] values, Class<?>[] paramTypes) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if(targetObj != null && invMethod != null)
		{
			Method m =  (paramTypes != null)? targetObj.getClass().getDeclaredMethod(invMethod, paramTypes):selectMethod(targetObj, invMethod);
			m.invoke(targetObj, values);
		}
		else
		{
			throw new NullPointerException();
		}
	}
	
	/**
	 * Tries to select the correct method when only the name is known.
	 * @param targetObj - object to inspect
	 * @param invMethod - name of Method to look up type for.
	 * @return - class type of field
	 */
	public static Method selectMethod(Object targetObj, String invMethod)
	{
		Method rtnMethod = null;
		if(targetObj != null && invMethod != null)
		{
			try
			{
				Method targetMethod = null;
				Method[] decMeth = targetObj.getClass().getDeclaredMethods();
				for (Method method : decMeth)
				{
					if(method.getName().equalsIgnoreCase(invMethod))
					{
						targetMethod = method;
						break; //Take the first match as there is no way to pick between multiple matches
					}
					
				}
				
				rtnMethod = targetMethod;				
			}
			catch (Exception e)
			{
				//if didnt work out just let the caller deal with issue and return null
			}
		}
		return rtnMethod;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static final String getMethodName(String name)
	{
		StringBuilder sb = new StringBuilder("set");
		sb.append(name.substring(0, 1).toUpperCase());
		sb.append(name.substring(1));
		return sb.toString();
	}
}
