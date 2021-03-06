package net.toobop.dot.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class Graph extends Attributed
{
	public boolean directed;

	private Set<Edge> edges = new HashSet<Edge>();

	private Set<Node> nodes = new HashSet<Node>();

	public void addEdge(Edge edge)
	{
		edges.add(edge);
	}

	public void addNode(Node node)
	{
		nodes.add(node);
	}

	public Set<Edge> getEdges()
	{
		return edges;
	}

	public Collection<Edge> getEdgesFrom(String edgeId)
	{
		Collection<Edge> foundEdges = new ArrayList<Edge>();
		EdgePoint edgePoint = EdgePoint.create(edgeId);
		for (Edge edge : edges)
		{
			if (edgePoint.equals(edge.getFrom()))
			{
				foundEdges.add(edge);
			}
		}
		return foundEdges;

	}

	public Node getNode(String id)
	{
		for (Node node : nodes)
		{
			if (node.getId().equals(id))
			{
				return node;
			}
		}
		return null;
	}

	public Set<Node> getNodes()
	{
		return nodes;
	}

	public boolean isDirected()
	{
		return directed;
	}

	public void setDirected(boolean directed)
	{
		this.directed = directed;
	}

	public void setEdges(Set<Edge> edges)
	{
		this.edges = edges;
	}

	public void setNodes(Set<Node> nodes)
	{
		this.nodes = nodes;
	}
}
