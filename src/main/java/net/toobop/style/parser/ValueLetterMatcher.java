package net.toobop.style.parser;

import org.parboiled.MatcherContext;
import org.parboiled.matchers.CustomMatcher;


public class ValueLetterMatcher extends CustomMatcher
{

	public ValueLetterMatcher()
	{
		super("valueletter");
	}

	private boolean acceptChar(char currentChar)
	{
		return Character.isLetter(currentChar) || Character.isDigit(currentChar);
	}

	@Override
	public boolean canMatchEmpty()
	{
		return false;
	}

	@Override
	public char getStarterChar()
	{
		return 0;
	}

	@Override
	public boolean isSingleCharMatcher()
	{
		return true;
	}

	@Override
	public boolean isStarterChar(char c)
	{
		return acceptChar(c);
	}

	public <V> boolean match(MatcherContext<V> context)
	{
		if (!acceptChar(context.getCurrentChar()))
		{
			return false;
		}
		context.advanceIndex(1);
		context.createNode();
		return true;
	}

}
