package org.dragonfly.wunderground.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dragonfly.wunderground.util.BeanUtil;
import org.dragonfly.wunderground.util.Exportable;
import org.dragonfly.wunderground.util.ExportableAttribute;

public class DragonflyDomain
{
	private static final Logger logger = Logger.getLogger(DragonflyDomain.class);

	public static String root = "dragonfly";
	public static List<String> fields = new ArrayList<String>();
	public static List<BeanAttributeMap> attributeMap = new ArrayList<BeanAttributeMap>();// <ParentName,

	// parent>

	/**
	 * Constructor evaluates the Bean and builds reference information from the annotations provided in the bean.
	 * @param rootTag
	 *            - root xml tag
	 */
	public DragonflyDomain()
	{
		Field[] rFields = this.getClass().getDeclaredFields();
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
				else if (annotation instanceof ExportableAttribute)
				{
					ExportableAttribute expAtt = (ExportableAttribute) annotation;
					BeanAttributeMap beanAtt = new BeanAttributeMap(field.getName(), expAtt.xmlName(), expAtt.tag(), expAtt.jsonName());
					attributeMap.add( beanAtt);
					// Debug
					logger.debug(beanAtt);
				}
			}
		}
	}

	@Override
	public String toString()
	{
		return BeanUtil.beanToString(this);
	}

	/**
	 * Object specific to bean processing and will be used by DragonflyHandler to process Attributes.
	 * 
	 * TODO: Consider extracting this.
	 * 
	 * @author leeclarke
	 */
	public class BeanAttributeMap implements Comparable<BeanAttributeMap>
	{
		private String fieldName;
		private String xmlName;
		private String tag;
		private String jsonName;

		public BeanAttributeMap()
		{

		}

		public BeanAttributeMap(String fldName, String xml, String tag, String json)
		{
			this.fieldName = fldName;
			this.xmlName = xml;
			this.tag = tag;
			this.jsonName = json;
		}

		public String getFieldName()
		{
			return fieldName;
		}

		public void setFieldName(String fieldName)
		{
			this.fieldName = fieldName;
		}

		public String getXmlName()
		{
			return xmlName;
		}

		public void setXmlName(String xmlName)
		{
			this.xmlName = xmlName;
		}

		public String getTag()
		{
			return tag;
		}

		public void setTag(String tag)
		{
			this.tag = tag;
		}

		public String getJsonName()
		{
			return jsonName;
		}

		public void setJsonName(String jsonName)
		{
			this.jsonName = jsonName;
		}

		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder("[BeanAttributeMap]");
			sb.append(" fieldName=").append(fieldName);
			sb.append(" xmlName=").append(xmlName);
			sb.append(" tag=").append(tag);
			sb.append(" jsonName=").append(jsonName);
			return sb.toString();
		}

		@Override
		public int compareTo(BeanAttributeMap o)
		{
			return this.fieldName.compareTo(o.getFieldName());
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if(obj != null && obj instanceof BeanAttributeMap)
			{
				return this.fieldName.equals(((BeanAttributeMap)obj).getFieldName());
			}
			if(obj != null && obj instanceof String)
			{
				return this.tag.equals((String)obj);
			}
			return false;
		}
	}
}