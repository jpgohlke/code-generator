package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public class VariableSkeleton<T> extends SkeletonMember implements Comparable<VariableSkeleton<?>> {
	
	private static final AccessModifier DEFAULT_ACCESS_MODIFIER = AccessModifier.PACKAGE; //For method parameters
	
	private final Class<T> type;
	private T value;
	
	
	public VariableSkeleton(Class<T> type, String name) {
		this(type, name, null);
	}
	
	public VariableSkeleton(Class<T> type, String name, T value) {
		super(name, DEFAULT_ACCESS_MODIFIER);
		if(type == null) {
			throw new IllegalArgumentException("A variable must have a type.");
		}
		this.type = type;
		this.value = value;
	}
	
	
	public Class<T> getType() {
		return type;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		VariableSkeleton<?> other = (VariableSkeleton<?>) object;
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
	public int compareTo(VariableSkeleton<?> other) {
		return getName().compareTo(other.getName());
	}

}
