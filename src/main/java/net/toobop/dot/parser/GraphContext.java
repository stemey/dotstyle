package net.toobop.dot.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.toobop.dot.model.Edge;
import net.toobop.dot.model.Graph;
import net.toobop.dot.model.Node;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class GraphContext
{

	private final Map<String, Set<Edge>> fromEndpoints = new HashMap<String, Set<Edge>>();

	private Graph graph = new Graph();

	private final Map<String, Set<Edge>> toEndpoints = new HashMap<String, Set<Edge>>();

	public void addEdge(String from, String to, Edge edge)
	{
		graph.addEdge(edge);
		addEndpoint(fromEndpoints, from, edge);
		addEndpoint(toEndpoints, to, edge);
	}

	private void addEndpoint(Map<String, Set<Edge>> endpoints, String id, Edge edge)
	{
		Set<Edge> set = endpoints.get(id);
		if (set == null)
		{
			set = new HashSet<Edge>();
			endpoints.put(id, set);
		}
		set.add(edge);
	}

	public void addNode(Node node)
	{
		graph.addNode(node);
	}

	public Graph create()
	{

		for (Map.Entry<String, Set<Edge>> entry : toEndpoints.entrySet())
		{
			Node node = getGraph().getNode(entry.getKey());
			if (node != null)
			{
				for (Edge edge : entry.getValue())
				{
					node.addTo(edge);
					edge.setTo(node);
				}
			}
		}
		for (Map.Entry<String, Set<Edge>> entry : fromEndpoints.entrySet())
		{
			Node node = getGraph().getNode(entry.getKey());
			if (node != null)
			{
				for (Edge edge : entry.getValue())
				{
					node.addFrom(edge);
					edge.setFrom(node);
				}
			}
		}

		for (Node node : graph.getNodes())
		{
			if (node.isHtmlLabelShape())
			{
				String value = node.getValue("label");
				if (value != null)
				{
					// strip < and >
					value = value.substring(1, value.length() - 1);
					try
					{
						Document dom =
							DocumentBuilderFactory.newInstance().newDocumentBuilder()
								.parse(new InputSource(new StringReader(value)));
						node.setDom(dom);
					}
					catch (SAXException e)
					{
						// not an html label
					}
					catch (IOException e)
					{
						throw new IllegalStateException("cannot parse html label", e);
					}
					catch (ParserConfigurationException e)
					{
						throw new IllegalStateException("cannot parse html label", e);
					}
				}
			}
		}

		return graph;
	}

	public Collection<Edge> getEdgesFrom(String from)
	{
		return fromEndpoints.get(from);
	}

	public Graph getGraph()
	{
		return graph;
	}

	public void setGraph(Graph graph)
	{
		this.graph = graph;
	}
}
