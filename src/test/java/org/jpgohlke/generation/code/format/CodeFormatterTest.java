package org.jpgohlke.generation.code.format;

import static org.jpgohlke.generation.code.format.CodeFormatter.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CodeFormatterTest {
	
	@Test
	public void testNewLines_None() {
		assertEquals("", newLines(0));
	}
	
	@Test
	public void testNewLines_Negative() {
		assertEquals("", newLines(-1));
	}
	
	@Test
	public void testNewLines_One() {
		assertEquals("\n", newLines(1));
	}
	
	@Test
	public void testNewLines_Other() {
		assertEquals("\n\n\n", newLines(3));
	}
	
	@Test
	public void testIndent_Null() {
		assertEquals("\t", indent(null));
	}
	
	@Test
	public void testIndent_EmptyString() {
		assertEquals("\t", indent(""));
	}
	
	@Test
	public void testIndent_BlankString() {
		assertEquals("\t   ", indent("   "));
	}
	
	@Test
	public void testIndent_OneLine() {
		String line = "public String toString() {";
		assertEquals("\t" + line, indent(line));
	}
	
	@Test
	public void testIndent_MultipleLines() {
		String line = "public String toString() {\n";
		line += "\treturn null;\n";
		line += "}";
		String expected = "\tpublic String toString() {\n";
		expected += "\t\treturn null;\n";
		expected += "\t}";
		assertEquals(expected, indent(line));
	}
	
	@Test
	public void testIndent_MultipleIndents() {
		String line = "public String toString() {";
		assertEquals("\t\t\t" + line, indent(line, 3));
	}
	
	@Test
	public void testIndent_MultipleIndentsMultipleLines() {
		String line = "public String toString() {\n";
		line += "\treturn null;\n";
		line += "}";
		String expected = "\t\t\tpublic String toString() {\n";
		expected += "\t\t\t\treturn null;\n";
		expected += "\t\t\t}";
		assertEquals(expected, indent(line, 3));
	}

}
