package net.toobop.transform;

import java.util.Iterator;

import net.toobop.dot.model.Attributed;
import net.toobop.dot.model.Graph;
import net.toobop.style.model.Rule;
import net.toobop.style.model.Style;

import org.apache.commons.jxpath.JXPathContext;
import org.parboiled.common.StringUtils;
import org.w3c.dom.Element;


public class StyleTransformer
{
	private Iterator<Object> match(Graph graph, Rule rule)
	{
		return JXPathContext.newContext(graph).selectNodes(rule.getXpath()).iterator();

	}

	public void transform(Graph graph, Style style)
	{
		for (Rule rule : style.getRules())
		{
			Iterator<Object> matches = match(graph, rule);
			while (matches.hasNext())
			{
				Object next = matches.next();
				if (next instanceof Attributed)
				{
					Attributed attributed = (Attributed) next;
					for (String name : rule.getAttributeNames())
					{
						if (!attributed.has(name))
						{
							attributed.setValue(name, rule.get(name));
						}
					}
				}
				else if (next instanceof Element)
				{
					Element element = (Element) next;
					for (String name : rule.getAttributeNames())
					{
						if (StringUtils.isEmpty(element.getAttribute(name)))
						{
							element.setAttribute(name, rule.get(name));
						}
					}
				}
				else
				{
					System.err.println("cannot apply rule for " + rule.getXpath() + " to " + next);
				}
			}
		}
	}
}
