package net.toobop.style.model;

import java.util.Comparator;


public class RuleComparator implements Comparator<Rule>
{

	public int compare(Rule o1, Rule o2)
	{
		int lengthDiff = o2.getXpath().length() - o1.getXpath().length();
		if (lengthDiff == 0)
		{
			return o1.getXpath().compareTo(o2.getXpath());
		}
		else
		{
			return lengthDiff;
		}
	}
}
