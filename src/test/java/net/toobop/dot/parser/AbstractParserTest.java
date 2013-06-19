package net.toobop.dot.parser;

import org.junit.Assert;
import org.parboiled.errors.InvalidInputError;
import org.parboiled.errors.ParseError;
import org.parboiled.support.MatcherPath;
import org.parboiled.support.ParsingResult;

public abstract class AbstractParserTest
{

	protected void assertParse(ParsingResult<?> result)
	{
		if (!result.matched)
		{
			StringBuilder builder = new StringBuilder();
			for (ParseError error : result.parseErrors)
			{
				if (error.getErrorMessage() != null)
				{
					builder.append(error.getErrorMessage());
					builder.append("\n");
				}
				else if (error instanceof InvalidInputError)
				{
					builder.append("\nfailed matchers: ");
					builder.append("\n at: ");
					builder.append(error.getInputBuffer().extract(0, error.getEndIndex()));
					for (MatcherPath matcher : ((InvalidInputError) error).getFailedMatchers())
					{
						builder.append("\n");
						builder.append(matcher.toString());
					}
				}
			}
			Assert.fail(builder.toString());
	
		}
	}

}
