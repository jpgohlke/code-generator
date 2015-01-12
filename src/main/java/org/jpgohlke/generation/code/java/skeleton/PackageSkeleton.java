package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public class PackageSkeleton extends SkeletonMember implements Comparable<PackageSkeleton> {
	
	
	public PackageSkeleton(String name) {
		super(name, AccessModifier.PACKAGE);
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		PackageSkeleton other = (PackageSkeleton) object;
		return new EqualsBuilder().append(getName(), other.getName()).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}
	
	@Override
	public String toString() {
		return getName() == null ? "" : "package " + getName() + ";";
	}
	
	@Override
	public int compareTo(PackageSkeleton other) {
		return getName().compareTo(other.getName());
	}
	
	
	public static PackageSkeleton sameAs(Class<?> klass) {
		return new PackageSkeleton(klass == null ? null : klass.getPackage().getName());
	}

}
