package net.toobop.dot.model;

public class Edge extends Attributed
{
	private Node from;

	private Node to;

	public Node getFrom()
	{
		return from;
	}

	public Node getTo()
	{
		return to;
	}

	public void setFrom(Node from)
	{
		this.from = from;
	}

	public void setTo(Node to)
	{
		this.to = to;
	}
}
