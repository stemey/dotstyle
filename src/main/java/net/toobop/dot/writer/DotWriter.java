package net.toobop.dot.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import net.toobop.dot.model.Attributed;
import net.toobop.dot.model.Edge;
import net.toobop.dot.model.Graph;
import net.toobop.dot.model.Node;


public class DotWriter
{

	public String indentString(int indent)
	{
		StringBuilder indentBuilder = new StringBuilder();
		for (int i = 0; i < indent; i++)
		{
			indentBuilder.append("\t");
		}
		String indentString = indentBuilder.toString();
		return indentString;
	}

	public void write(Graph graph, Writer writer) throws IOException
	{
		BufferedWriter w = new BufferedWriter(writer);
		if (graph.isDirected())
		{
			w.write("di");
		}
		w.write("graph{\n");
		writeBody(graph, w);
		w.write("\n}");
		w.flush();
	}

	private void writeAttributes(int indent, Attributed attributed, BufferedWriter w, boolean addBrackets)
		throws IOException
	{
		String indentString = indentString(indent);
		w.write(indentString);
		if (addBrackets)
		{
			w.write("[\n");
		}
		for (String name : attributed.getAttributeNames())
		{
			w.write(indentString);
			w.write("\t");
			w.write(name);
			w.write("=");
			attributed.writeValue(name, w);
			if (addBrackets)
			{
				w.write(",\n");
			}
			else
			{
				w.write(";\n");
			}
		}

		w.write(indentString);
		if (addBrackets)
		{
			w.write("]\n");
		}

	}

	private void writeBody(Graph graph, BufferedWriter w) throws IOException
	{
		writeAttributes(0, graph, w, false);
		for (Node node : graph.getNodes())
		{
			writeNode(1, node, w);
		}
		for (Edge edge : graph.getEdges())
		{
			writeEdge(1, graph.isDirected(), edge, w);
		}
	}

	private void writeEdge(int indent, boolean directed, Edge edge, BufferedWriter w) throws IOException
	{
		String indentString = indentString(indent);
		w.write(indentString);
		w.write(edge.getFrom().getFullId());
		w.write(" ");
		if (directed)
		{
			w.write("-> ");
		}
		else
		{
			w.write("-- ");
		}
		w.write(edge.getTo().getFullId());
		writeAttributes(indent + 1, edge, w, true);
	}

	private void writeNode(int indent, Node node, BufferedWriter w) throws IOException
	{
		String indentString = indentString(indent);
		w.write(indentString);
		w.write(node.getId());
		writeAttributes(indent + 1, node, w, true);
	}

}
