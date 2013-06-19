package net.toobop.dot.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Attributed
{
	private final Map<String, String> attributes = new HashMap<String, String>();

	public void addAttribute(Attribute attribute)
	{
		attributes.put(attribute.getName(), attribute.getValue());
	}

	public void addAttribute(String name, String value)
	{
		attributes.put(name, value);
	}

	public Collection<String> getAttributeNames()
	{
		return attributes.keySet();
	}

	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public String getValue(String name)
	{
		return attributes.get(name);
	}

	public boolean has(String name)
	{
		return attributes.containsKey(name);
	}

	public void setValue(String name, String value)
	{
		attributes.put(name, value);
	}

}
