package net.toobop.style.parser;

import net.toobop.style.model.Attribute;
import net.toobop.style.model.Rule;

import org.parboiled.Context;
import org.parboiled.ContextAware;


public class AstActions implements ContextAware<Object>
{

	private Context<Object> ctx;

	public boolean attribute()
	{
		String value = (String) ctx.getValueStack().pop();
		String name = (String) ctx.getValueStack().pop();

		Attribute attribute = new Attribute(name, value);
		ctx.getValueStack().push(attribute);
		return true;

	}

	public boolean rule()
	{
		Rule rule = new Rule();
		Object pop = ctx.getValueStack().pop();
		while (pop instanceof Attribute)
		{
			rule.addAttribute(((Attribute) pop));
			pop = ctx.getValueStack().pop();
		}

		String xpath = (String) pop;

		rule.setXpath(xpath);

		StyleContext styleContext = (StyleContext) ctx.getValueStack().peek();
		styleContext.addRule(rule);
		return true;
	}

	public void setContext(Context<Object> ctx)
	{
		this.ctx = ctx;
	}

	public boolean style()
	{
		StyleContext styleContext = new StyleContext();
		ctx.getValueStack().push(styleContext);
		return true;
	}

}
