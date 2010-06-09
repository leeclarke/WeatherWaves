package org.dragonfly.wunderground.service;

import java.lang.annotation.*;

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
