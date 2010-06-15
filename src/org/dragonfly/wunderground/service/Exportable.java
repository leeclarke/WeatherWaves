package org.dragonfly.wunderground.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated field attribute can be exported/imported to JSON/from xml
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exportable {
	/**
	 * Xml tag name to use for parsing
	 * @return
	 */
	String xmlName() default ""; // Won't accept null;
	
	
	/**
	 * Exported JSON name if different then xmlName
	 * @return
	 */
	String jsonName() default "";
}
