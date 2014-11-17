package org.jpgohlke.io;

import java.io.File;

public abstract class FileProcessor {
	
	protected File file;
	
	
	public void setFile(String filePath) {
		setFile(new File(filePath));
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	

}
