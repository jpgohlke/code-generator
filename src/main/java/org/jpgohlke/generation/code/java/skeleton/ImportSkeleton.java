package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public class ImportSkeleton extends SkeletonMember implements Comparable<ImportSkeleton> {
	
	
	public ImportSkeleton(String name) {
		super(name, AccessModifier.PACKAGE);
	}
	
	public ImportSkeleton(Class<?> klass) {
		super(klass == null ? "" : klass.getCanonicalName(), AccessModifier.PACKAGE);
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		ImportSkeleton other = (ImportSkeleton) object;
		return new EqualsBuilder().append(getName(), other.getName()).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}
	
	@Override
	public String toString() {
		return "import " + getName() + ";";
	}
	
	@Override
	public int compareTo(ImportSkeleton member) {
		return getName().compareTo(member.getName());
	}

}
