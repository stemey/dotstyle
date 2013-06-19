package net.toobop.transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import net.toobop.dot.model.Graph;
import net.toobop.dot.writer.DotWriter;
import net.toobop.style.model.Style;

import org.apache.commons.io.IOUtils;


public class Main
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		try
		{
			File f = new File(args[0]);
			String input = IOUtils.toString(new FileReader(f));
			Graph graph = new net.toobop.dot.Main().parse(input);

			File s = new File(args[1]);
			String sinput = IOUtils.toString(new FileReader(s));
			Style style = new net.toobop.style.Main().parse(sinput);

			StyleTransformer styleTransformer = new StyleTransformer();
			styleTransformer.transform(graph, style);

			DotWriter dotWriter = new DotWriter();
			StringWriter writer = new StringWriter();
			dotWriter.write(graph, writer);
			System.out.println(writer.toString());
		}
		catch (Exception e)
		{
			System.err.println("cannot transform dot: " + e.getMessage());
		}
	}
}
