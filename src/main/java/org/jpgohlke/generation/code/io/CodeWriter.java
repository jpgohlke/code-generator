package org.jpgohlke.generation.code.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter extends FileProcessor {
	
	public CodeWriter() { }
	
	public CodeWriter(String fileName) throws IOException {
		this(new File(fileName));
	}
	
	public CodeWriter(File file) throws IOException {
		this.file = file;
	}
	
	
	public void write(Object object) throws IOException {
		write(object.toString());
	}
	
	public void write(String string) throws IOException {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for(String line : string.split("\n")) {
				writer.write(line);
				writer.newLine();
			}
		}

	}

}
