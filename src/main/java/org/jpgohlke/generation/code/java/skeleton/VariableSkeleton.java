package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VariableSkeleton extends SkeletonMember implements Comparable<VariableSkeleton> {
	
	private Class<?> type;
	
	
	public VariableSkeleton(Class<?> type, String name) {
		super(name);
		if(type == null) {
			throw new IllegalArgumentException("A variable must have a type.");
		}
		this.type = type;
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
		return type.getSimpleName() + " " + getName();
	}

	@Override
	public int compareTo(VariableSkeleton other) {
		return getName().compareTo(other.getName());
	}

}
