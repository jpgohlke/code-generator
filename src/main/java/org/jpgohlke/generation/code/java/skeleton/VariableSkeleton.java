package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VariableSkeleton extends SkeletonMember {
	
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
		return new EqualsBuilder().append(name, other.name).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}
	
	
	@Override
	public String toString() {
		return type.getSimpleName() + " " + name;
	}

}