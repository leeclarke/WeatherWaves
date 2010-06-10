package org.dragonfly.wunderground.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.BeanUtil;
import org.dragonfly.wunderground.service.Exportable;

public class DragonflyDomain
{
	private static final Logger logger = Logger.getLogger(DragonflyDomain.class);
	
	public static String root = "dragonfly";
	public static List<String> fields = new ArrayList<String>();

	/**
	 * 
	 * @param rootTag - root xml tag
	 */
	public DragonflyDomain()
	{
		Field[] rFields = Location.class.getDeclaredFields();
		for (Field field : rFields)
		{
			Annotation[] annotations = field.getDeclaredAnnotations();

			for (Annotation annotation : annotations)
			{
				if (annotation instanceof Exportable)
				{
					// Add to list of exposed fields, helps the setter know what
					// fields are being imported from xml.
					fields.add(field.getName());
					Exportable myAnnotation = (Exportable) annotation;
					logger.debug("field [" + field.getName() + "] \n\txml:: " + myAnnotation.xmlName() + "\n\tjson:: "
							+ myAnnotation.jsonName());
				}
			}
		}
	}	
	
	@Override
	public String toString()
	{
		return BeanUtil.beanToString(this);
	}
}