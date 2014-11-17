package org.jpgohlke.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter extends FileProcessor {
	
	public FileWriter() { }
	
	public FileWriter(String fileName) throws IOException {
		this(new File(fileName));
	}
	
	public FileWriter(File file) throws IOException {
		this.file = file;
	}
	
	
	public void write(Object object) throws IOException {
		write(object.toString());
	}
	
	public void write(String string) throws IOException {
		try(BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
			for(String line : string.split("\n")) {
				writer.write(line);
				writer.newLine();
			}
		}

	}

}
