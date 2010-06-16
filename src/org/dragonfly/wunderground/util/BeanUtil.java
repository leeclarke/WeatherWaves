package org.dragonfly.wunderground.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.dragonfly.wunderground.domain.DragonflyDomain;
import org.dragonfly.wunderground.domain.Location;

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
	public static final Object invokeMethod(Object targetObj, String invMethod, Object[] values, Class<?>[] paramTypes) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		if(targetObj != null && invMethod != null)
		{
			Method m =  (paramTypes != null)? targetObj.getClass().getDeclaredMethod(invMethod, paramTypes):selectMethod(targetObj, invMethod);
			return m.invoke(targetObj, values);
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
	
	public static final String getGetterMethodName(String name)
	{
		StringBuilder sb = new StringBuilder("get");
		sb.append(name.substring(0, 1).toUpperCase());
		sb.append(name.substring(1));
		return sb.toString();
	}
	
	/**
	 * @param bean
	 * @return
	 */
	public static final String beanToString(DragonflyDomain bean)
	{
		StringBuilder sb = new StringBuilder();
		Field[] rFields = bean.getClass().getDeclaredFields();
		for (Field field : rFields)
		{
			Annotation[] annotations = field.getDeclaredAnnotations();

			for (Annotation annotation : annotations)
			{
				if (annotation instanceof Exportable || annotation instanceof ExportableAttribute)
				{
					// Add to list of exposed fields, helps the setter know what
					// fields are being imported from xml.
					try
					{
						sb.append(" ").append(field.getName()).append(" = ").append(BeanUtil.invokeMethod(bean, BeanUtil.getGetterMethodName(field.getName()), new Object[]{}, null));
					} catch (Exception e)
					{
						
					} 
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Output annotated fields as JSON
	 * @param bean
	 * @return
	 */
	public static final String beanToJSON(DragonflyDomain bean)
	{
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
}
