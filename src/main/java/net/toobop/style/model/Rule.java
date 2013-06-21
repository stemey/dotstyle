package net.toobop.style.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Rule
{
	private final Map<String, String> attributes = new HashMap<String, String>();

	private String xpath;

	public void addAttribute(Attribute attribute)
	{
		attributes.put(attribute.getName(), attribute.getValue());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (xpath == null)
		{
			if (other.xpath != null)
				return false;
		}
		else if (!xpath.equals(other.xpath))
			return false;
		return true;
	}

	public String get(String name)
	{
		return attributes.get(name);
	}

	public Collection<String> getAttributeNames()
	{
		return attributes.keySet();
	}

	public String getXpath()
	{
		return xpath;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xpath == null) ? 0 : xpath.hashCode());
		return result;
	}

	public void put(String name, String value)
	{
		attributes.put(name, value);
	}

	public void setXpath(String xpath)
	{
		this.xpath = xpath;
	}

	@Override
	public String toString()
	{
		return "Rule [xpath=" + xpath + "]";
	}

}
