package net.toobop.dot.parser;

import net.toobop.common.ParseUtils;

import org.junit.Assert;
import org.parboiled.support.ParsingResult;


public abstract class AbstractParserTest
{

	protected void assertParse(ParsingResult<?> result)
	{
		if (!result.matched)
		{
			Assert.fail(ParseUtils.createErrorMessage(result));

		}
	}

}
