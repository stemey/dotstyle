package net.toobop.style.model;

import java.util.Comparator;


public class RuleComparator implements Comparator<Rule>
{

	public int compare(Rule o1, Rule o2)
	{
		return o2.getXpath().length() - o1.getXpath().length();
	}

}
