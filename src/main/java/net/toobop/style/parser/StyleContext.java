package net.toobop.style.parser;

import net.toobop.style.model.Rule;
import net.toobop.style.model.Style;


public class StyleContext
{
	Style style = new Style();

	public void addRule(Rule rule)
	{
		style.addRule(rule);
	}

	public Style create()
	{
		return style;
	}
}
