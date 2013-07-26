package net.toobop.style;

import net.toobop.common.ParseException;
import net.toobop.style.model.Style;
import net.toobop.style.parser.StyleContext;
import net.toobop.style.parser.StyleParser;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParsingResult;


public class Main
{
	public Style parse(String input) throws ParseException
	{
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new BasicParseRunner(parser.Style()).run(input);
		if (result.hasErrors())
		{
			throw new ParseException("error in dot parsing. ", result);
		}
		StyleContext ctx = (StyleContext) result.valueStack.pop();
		return ctx.create();
	}
}
