package net.toobop.common;

import org.parboiled.support.ParsingResult;


public class ParseException extends Exception
{
	public ParseException(String message, ParsingResult<?> result)
	{
		super(message + "\n" + ParseUtils.createErrorMessage(result));
	}
}
