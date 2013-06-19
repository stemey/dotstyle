package net.toobop.style;

import net.toobop.style.model.Style;
import net.toobop.style.parser.StyleContext;
import net.toobop.style.parser.StyleParser;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParsingResult;


public class Main
{
	public Style parse(String input)
	{
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new BasicParseRunner(parser.Style()).run(input);
		StyleContext ctx = (StyleContext) result.valueStack.pop();
		return ctx.create();
	}
}
