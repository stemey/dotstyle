package net.toobop.style.model;

import java.util.ArrayList;
import java.util.List;


public class Style
{
	private final List<Rule> rules = new ArrayList<Rule>();

	public void addRule(Rule rule)
	{
		rules.add(rule);
	}

	public List<Rule> getRules()
	{
		return rules;
	}
}
