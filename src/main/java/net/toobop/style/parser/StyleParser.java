package net.toobop.style.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;


@BuildParseTree
public class StyleParser extends BaseParser<Object>
{

	protected AstActions actions = new AstActions();

	final Rule Colon = String(":");

	final Rule Semicolon = String(";");

	Rule Attribute()
	{
		return Sequence(Id(), WhiteSpace(), Colon, WhiteSpace(), Value(), ACTION(actions.attribute()), WhiteSpace());
	}

	Rule AttributeList()
	{
		return Braces(Sequence(Attribute(), ZeroOrMore(Sequence(Semicolon, WhiteSpace(), Attribute()))));
	}

	Rule Braces(Rule body)
	{
		return Sequence(String("{"), WhiteSpace(), body, String("}"), WhiteSpace());
	}

	Rule Id()
	{

		return Sequence(OneOrMore(IdLetter()), push(matchOrDefault("")), WhiteSpace());
	}

	Rule IdLetter()
	{
		return new IdLetterMatcher();
	}

	Rule Rule()
	{
		return Sequence(Xpath(), WhiteSpace(), AttributeList(), ACTION(actions.rule()));
	}

	public Rule Style()
	{
		return Sequence(ACTION(actions.style()), this.ZeroOrMore(Rule()));
	}

	Rule Value()
	{
		return Sequence(OneOrMore(ValueLetter()), push(matchOrDefault("")));
	}

	Rule ValueLetter()
	{
		return new ValueLetterMatcher();
	}

	Rule WhiteSpace()
	{
		return ZeroOrMore(AnyOf(" \t\f\n\r"));
	}

	Rule Xpath()
	{
		return Sequence(OneOrMore(new XpathLetterMatcher()), push(matchOrDefault("")), WhiteSpace());
	}
}
