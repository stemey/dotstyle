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

	public void put(String name, String value)
	{
		attributes.put(name, value);
	}

	public void setXpath(String xpath)
	{
		this.xpath = xpath;
	}

}
