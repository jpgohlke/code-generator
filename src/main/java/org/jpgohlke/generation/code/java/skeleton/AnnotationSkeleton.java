package org.jpgohlke.generation.code.java.skeleton;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AnnotationSkeleton implements Comparable<AnnotationSkeleton> {
	
	private String name;
	private Map<String, String> fields;
	
	
	public AnnotationSkeleton(String name) {
		this.name = name;
		this.fields = new LinkedHashMap<>();
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, String> getFields() {
		return fields;
	}
	
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}
	
	
	@Override
	public String toString() {
		String string = "@" + name;
		if(fields != null && fields.size() != 0) {
			string += "(";
			for(Entry<String, String> entry : fields.entrySet()) {
				string += entry.getKey() + " = \"" + entry.getValue() + "\", ";
			}
			string = string.substring(0, string.length() - 2);
			string += ")";
		}
		return string;
	}


	@Override
	public int compareTo(AnnotationSkeleton other) {
		return name.compareTo(other.name);
	}

}
