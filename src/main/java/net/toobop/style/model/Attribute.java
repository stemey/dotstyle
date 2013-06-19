package net.toobop.style.model;

public class Attribute
{
	private String name;

	private String value;

	public Attribute(String name, String value)
	{
		super();
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		return value;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
