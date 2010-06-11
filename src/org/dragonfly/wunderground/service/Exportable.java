package org.dragonfly.wunderground.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated field can be exported/imported to JSON/from xml
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exportable {
	String xmlName() default ""; // Won't accept null;
	String jsonName() default "";
}
