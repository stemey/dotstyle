package net.toobop.style.model;

import org.junit.Assert;
import org.junit.Test;


public class RuleComparatorTest
{

	@Test
	public void testRuleHierachy()
	{
		Style style = new Style();
		Rule rule2 = new Rule();
		rule2.setXpath("/short");
		style.addRule(rule2);
		Rule longRule = new Rule();
		longRule.setXpath("/graph/nodes/verlong");
		style.addRule(longRule);

		Assert.assertEquals(longRule, style.getRules().iterator().next());
	}

}
