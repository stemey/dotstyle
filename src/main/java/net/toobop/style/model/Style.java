package net.toobop.style.model;

import java.util.Collection;
import java.util.TreeSet;



public class Style
{
	private final Collection<Rule> rules = new TreeSet<Rule>(new RuleComparator());

	public void addRule(Rule rule)
	{
		rules.add(rule);
	}

	public Collection<Rule> getRules()
	{
		return rules;
	}
}
