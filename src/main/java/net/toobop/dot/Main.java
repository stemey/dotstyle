package net.toobop.dot;

import net.toobop.common.ParseException;
import net.toobop.dot.model.Graph;
import net.toobop.dot.parser.DotParser;
import net.toobop.dot.parser.GraphContext;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.BasicParseRunner;
import org.parboiled.support.ParsingResult;


public class Main
{
	public Graph parse(String input) throws ParseException
	{
		DotParser parser = Parboiled.createParser(DotParser.class);
		ParsingResult<?> result = new BasicParseRunner(parser.Graph()).run(input);
		GraphContext ctx = (GraphContext) result.valueStack.pop();
		if (result.hasErrors())
		{
			throw new ParseException("error in dot parsing. ", result);
		}
		return ctx.create();
	}
}
