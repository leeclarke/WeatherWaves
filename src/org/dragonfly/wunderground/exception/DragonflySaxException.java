package org.dragonfly.wunderground.exception;


/**
 * Indicates a failure in connecting or parsing the wugrnd feed.
 * @author leeclarke
 */
public class DragonflySaxException extends Exception
{
	private static final long serialVersionUID = 1235634L;

	public DragonflySaxException()
	{
		super();
	}

	public DragonflySaxException(String s, Throwable cause)
	{
		super(s, cause);
	}

	public DragonflySaxException(String s)
	{
		super(s);
	}

	public DragonflySaxException(Throwable cause)
	{
		super(cause);
	}

}
