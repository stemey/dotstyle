package net.toobop.dot.model;

public class Edge extends Attributed
{
	private EdgePoint from;

	private EdgePoint to;

	public EdgePoint getFrom()
	{
		return from;
	}

	public EdgePoint getTo()
	{
		return to;
	}

	public void setFrom(EdgePoint from)
	{
		this.from = from;
	}

	public void setTo(EdgePoint to)
	{
		this.to = to;
	}

	@Override
	public String toString()
	{
		return "Edge [from=" + from + ", to=" + to + "]";
	}

}
