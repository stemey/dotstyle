package net.toobop.dot.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;


@BuildParseTree
public class DotParser extends BaseParser<Object>
{

	AstActions actions = new AstActions();

	final Rule Apo = String("\"");

	final Rule Comma = Sequence(String(","), WhiteSpace());

	final Rule DirectedLine = String("->");

	final Rule Equal = String("=");

	final Rule Semicolon = String(";");

	final Rule UndirectedLine = String("--");

	Rule Attribute()
	{
		return Sequence(Id(), Equal(), Value(), ACTION(actions.attribute()));
	}

	Rule AttributeList()
	{
		return ZeroOrMore(Brackets(Sequence(ZeroOrMore(Sequence(Attribute(), Comma)), Optional(Attribute()))));
	}

	Rule Body()
	{
		return this.ZeroOrMore(FirstOf(Edge(), Node()));
	}

	Rule Braces(Rule body)
	{
		return Sequence(String("{"), WhiteSpace(), body, String("}"), WhiteSpace());
	}

	Rule Brackets(Rule body)
	{
		return Sequence(String("["), WhiteSpace(), body, String("]"), WhiteSpace());
	}

	Rule Edge()
	{
		return Sequence(Id(),// 1
			EdgeLine(),//
			Id(), //
			AttributeList(),//
			OptionalSemicolon(),//
			ACTION(actions.edge()));
	}

	Rule EdgeLine()
	{
		return Sequence(FirstOf(DirectedLine, UndirectedLine), WhiteSpace());
	}

	Rule Equal()
	{
		return Sequence(Equal, WhiteSpace());
	}

	public Rule Graph()
	{
		return Sequence(Optional(String("strict")),//
			push(matchOrDefault("")),//
			FirstOf(String("graph"), String("digraph")),//
			push(matchOrDefault("")),//
			WhiteSpace(),//
			ACTION(actions.graph()),//
			Braces(Body()));
	}

	Rule Id()
	{

		return Sequence(OneOrMore(IdLetter()), push(matchOrDefault("")), WhiteSpace());
	}

	Rule IdLetter()
	{
		return new IdLetterMatcher();
	}

	Rule Node()
	{
		return Sequence(Id(), AttributeList(), OptionalSemicolon(), ACTION(actions.node()));
	}

	Rule OptionalSemicolon()
	{
		return ZeroOrMore(Sequence(Semicolon, WhiteSpace()));
	}

	Rule Value()
	{
		return Sequence(Apo, OneOrMore(ValueLetter()), push(matchOrDefault("")), Apo, WhiteSpace());
	}

	Rule ValueLetter()
	{
		return new ValueLetterMatcher();
	}

	Rule WhiteSpace()
	{
		return ZeroOrMore(AnyOf("\r\n \t\f"));
	}

}
