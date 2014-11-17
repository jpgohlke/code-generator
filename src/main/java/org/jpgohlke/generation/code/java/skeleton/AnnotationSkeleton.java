package org.jpgohlke.generation.code.java.skeleton;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AnnotationSkeleton implements Comparable<AnnotationSkeleton> {
	
	public static final AnnotationSkeleton OVERRIDE = new AnnotationSkeleton("Override");
	
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
	
	public void addField(String key, String value) {
		fields.put(key, value);
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		
		AnnotationSkeleton other = (AnnotationSkeleton) object;
		
		return new EqualsBuilder().append(name, other.name).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
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
