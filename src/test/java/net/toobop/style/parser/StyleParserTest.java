package net.toobop.style.parser;

import java.util.Collection;

import net.toobop.dot.parser.AbstractParserTest;
import net.toobop.style.model.Rule;

import org.junit.Assert;
import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;


public class StyleParserTest extends AbstractParserTest
{

	@Test
	public void testFail()
	{
		String input = "nod  es :::}}";
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new ReportingParseRunner(parser.Style()).run(input);
		Assert.assertTrue(result.hasErrors());
	}

	@Test
	public void testRules()
	{
		String input = "nodes[@id='asd'] {\n color:green \n}";
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new ReportingParseRunner(parser.Style()).run(input);
		assertParse(result);

		StyleContext ctx = (StyleContext) result.valueStack.pop();
	}

	@Test
	public void testRuleWithOneAttributes()
	{
		String input = "/graph {color:red}";
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new ReportingParseRunner(parser.Style()).run(input);
		assertParse(result);

		StyleContext ctx = (StyleContext) result.valueStack.pop();
		Assert.assertEquals(1, ctx.create().getRules().size());
	}

	@Test
	public void testRuleWithOneAttributesWhiteSpace()
	{
		String input = "/graph {\ncolor:red\n}";
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new ReportingParseRunner(parser.Style()).run(input);
		assertParse(result);

		StyleContext ctx = (StyleContext) result.valueStack.pop();
		Assert.assertEquals(1, ctx.create().getRules().size());
	}

	@Test
	public void testRuleWithoutAttributes()
	{
		String input = "/graph {}";
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new ReportingParseRunner(parser.Style()).run(input);
		assertParse(result);

		StyleContext ctx = (StyleContext) result.valueStack.pop();
		Assert.assertEquals(0, ctx.create().getRules().size());
	}

	@Test
	public void testRuleWithTwoAttributes()
	{
		String input = "/graph {color:red; height:100}";
		StyleParser parser = Parboiled.createParser(StyleParser.class);
		ParsingResult<?> result = new ReportingParseRunner(parser.Style()).run(input);
		assertParse(result);

		StyleContext ctx = (StyleContext) result.valueStack.pop();
		Collection<Rule> rules = ctx.create().getRules();
		Assert.assertEquals(1, rules.size());
		Rule rule = rules.iterator().next();
		Assert.assertEquals("red", rule.get("color"));
		Assert.assertEquals("100", rule.get("height"));

	}
}
