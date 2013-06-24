package net.toobop.dot.parser;

import net.toobop.dot.model.Attribute;
import net.toobop.dot.model.Edge;
import net.toobop.dot.model.EdgePoint;
import net.toobop.dot.model.Node;

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

	public boolean edge()
	{
		Edge edge = new Edge();
		Object attribute = ctx.getValueStack().peek();
		while (attribute instanceof Attribute)
		{
			edge.addAttribute(((Attribute) ctx.getValueStack().pop()));
			attribute = ctx.getValueStack().peek();
		}
		EdgePoint to = (EdgePoint) ctx.getValueStack().pop();
		EdgePoint from = (EdgePoint) ctx.getValueStack().pop();

		GraphContext graphContext = (GraphContext) ctx.getValueStack().peek();

		graphContext.addEdge(from, to, edge);
		return true;
	}

	public boolean edgePoint()
	{
		EdgePoint edgePoint;
		Object peek = ctx.getValueStack().peek(1);
		if (peek.equals(":"))
		{
			String port = (String) ctx.getValueStack().pop();
			ctx.getValueStack().pop();
			String id = (String) ctx.getValueStack().pop();
			edgePoint = new EdgePoint(id, port);
		}
		else
		{
			edgePoint = new EdgePoint((String) ctx.getValueStack().pop());
		}
		ctx.getValueStack().push(edgePoint);
		return true;
	}

	public boolean graph()
	{
		GraphContext graphContext = new GraphContext();
		graphContext.getGraph().setDirected(ctx.getValueStack().pop().equals("digraph"));
		ctx.getValueStack().push(graphContext);
		return true;
	}

	public boolean graphAttribute()
	{
		String value = (String) ctx.getValueStack().pop();
		String name = (String) ctx.getValueStack().pop();

		GraphContext graphContext = (GraphContext) ctx.getValueStack().peek();
		graphContext.getGraph().setValue(name, value);
		return true;

	}

	public boolean node()
	{
		Node node = new Node();
		Object pop = ctx.getValueStack().pop();
		while (pop instanceof Attribute)
		{
			node.addAttribute(((Attribute) pop));
			pop = ctx.getValueStack().pop();
		}
		node.setId((String) pop);
		GraphContext graphContext = (GraphContext) ctx.getValueStack().peek();
		graphContext.addNode(node);
		return true;
	}

	public void setContext(Context<Object> ctx)
	{
		this.ctx = ctx;
	}

}
