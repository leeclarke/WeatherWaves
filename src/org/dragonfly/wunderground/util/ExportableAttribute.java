package org.dragonfly.wunderground.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tags fields as originating from and XML attribute. When the setters are
 * called for this field it expects the field name to be formatted as
 * "tagname"+"AttributeName] propercase on the attribute name.
 * 
 * @author leeclarke
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExportableAttribute {

	/**
	 * Parent Tag in which the attribute is contained.
	 * 
	 * @return
	 */
	String tag() default "";

	/**
	 * Xml tag name to use for parsing
	 * 
	 * @return
	 */
	String xmlName() default ""; // Won't accept null;

	/**
	 * Exported JSON name if different then xmlName
	 * 
	 * @return
	 */
	String jsonName() default "";
}