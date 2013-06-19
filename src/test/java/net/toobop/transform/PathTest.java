package net.toobop.transform;

import java.util.HashSet;
import java.util.Set;

import net.toobop.dot.model.Edge;
import net.toobop.dot.model.Graph;
import net.toobop.dot.model.Node;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class PathTest
{

	private static Graph graph;

	@BeforeClass
	public static void setup()
	{
		graph = new Graph();

		Set<Node> nodes = new HashSet<Node>();
		Node nodeA = new Node();
		nodeA.setId("A");
		nodeA.setValue("label", "hallo");
		nodes.add(nodeA);
		Node nodeB = new Node();
		nodeB.setId("B");
		nodeB.setValue("label", "Hi");
		nodes.add(nodeB);

		graph.setNodes(nodes);

		Set<Edge> edges = new HashSet<Edge>();
		Edge edge = new Edge();
		edge.setFrom(nodeA);
		edge.setTo(nodeB);

		graph.setEdges(edges);
	}

	@Test
	public void testAttribute()
	{
		Object value = JXPathContext.newContext(graph).getValue("nodes/attributes[@label='hallo']");
		Assert.assertTrue(value != null);
	}

	@Test
	public void testFromEdges()
	{
		Object value = JXPathContext.newContext(graph).getValue("nodes[attributes/@label='hallo']/fromEdges");
		Assert.assertTrue(value != null);
	}

}
