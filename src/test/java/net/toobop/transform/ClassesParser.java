package net.toobop.transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import net.toobop.common.ParseException;
import net.toobop.dot.Main;
import net.toobop.dot.model.Graph;
import net.toobop.dot.writer.DotWriter;
import net.toobop.style.model.Style;

import org.apache.commons.io.IOUtils;


public class ClassesParser
{
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
	{
		String dir = args[0];

		File f = new File(dir + "/classes.dot");
		String input = IOUtils.toString(new FileReader(f));
		Graph graph = new Main().parse(input);

		File s = new File(dir + "/classes.dss");
		String sinput = IOUtils.toString(new FileReader(s));
		Style style = new net.toobop.style.Main().parse(sinput);

		StyleTransformer styleTransformer = new StyleTransformer();
		styleTransformer.transform(graph, style);

		DotWriter dotWriter = new DotWriter();
		StringWriter writer = new StringWriter();
		dotWriter.write(graph, writer);
		System.out.println(writer.toString());
	}
}
