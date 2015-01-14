package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public class VariableSkeleton extends SkeletonMember implements Comparable<VariableSkeleton> {
	
	private static final AccessModifier DEFAULT_ACCESS_MODIFIER = AccessModifier.PACKAGE; //For method parameters
	
	private final Class<?> type;
	private Object value;
	
	
	public VariableSkeleton(Class<?> type, String name) {
		this(type, name, null);
	}
	
	public VariableSkeleton(Class<?> type, String name, Object value) {
		this(DEFAULT_ACCESS_MODIFIER, type, name, value);
	}
	
	public VariableSkeleton(AccessModifier accessModifier, Class<?> type, String name) {
		this(accessModifier, type, name, null);
	}
	
	public VariableSkeleton(AccessModifier accessModifier, Class<?> type, String name, Object value) {
		super(name, accessModifier);
		if(type == null) {
			throw new IllegalArgumentException("A variable must have a type.");
		}
		this.type = type;
		this.value = value;
	}
	
	
	public Class<?> getType() {
		return type;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	public MethodSkeleton getGetter() {
		MethodSkeleton method = new MethodSkeleton(
				AccessModifier.PUBLIC,
				type.getSimpleName(),
				"get" + StringUtils.capitalize(getName()));
		method.setBody("return " + getName() + ";");
		return method;
	}
	
	public MethodSkeleton getSetter() {
		MethodSkeleton method =  new MethodSkeleton(
				AccessModifier.PUBLIC,
				"void",
				"set" + StringUtils.capitalize(getName()));
		method.addArgument(new VariableSkeleton(type, getName()));
		method.setBody("this." + getName() + " = " + getName() + ";");
		return method;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		VariableSkeleton other = (VariableSkeleton) object;
		return new EqualsBuilder().append(getName(), other.getName()).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}
	
	@Override
	public String toString() {
		String string = getAccessModifier().toString();
		if(string.length() != 0) string += " ";
		string += type.getSimpleName() + " " + getName();
		if(value != null) string += " = " + value;
		return string;
	}

	@Override
	public int compareTo(VariableSkeleton other) {
		return getName().compareTo(other.getName());
	}

}
