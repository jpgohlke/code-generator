package org.jpgohlke.generation.code.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader extends FileProcessor {
	
	
	public FileReader() { }
	
	public FileReader(String filePath) {
		this(new File(filePath));
	}
	
	public FileReader(File file) {
		this.file = file;
	}
	
	
	public String read() throws FileNotFoundException {
		try(Scanner scanner = new Scanner(file)) {
			String result = "";
			while(scanner.hasNext()) {
				result += scanner.next();
			}
			return result;
		}
	}
	

}