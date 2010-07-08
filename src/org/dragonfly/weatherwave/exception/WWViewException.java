package org.dragonfly.weatherwave.exception;

/**
 * @author leelclarke
 */
@SuppressWarnings("serial")
public class WWViewException extends Exception
{

	public WWViewException()
	{
		super();
	}

	public WWViewException(String message)
	{
		super(message);
	}

	public WWViewException(Throwable cause)
	{
		super(cause);
	}

	public WWViewException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
