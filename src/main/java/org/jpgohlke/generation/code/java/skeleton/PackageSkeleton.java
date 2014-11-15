package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PackageSkeleton extends SkeletonMember {
	
	
	public PackageSkeleton(String name) {
		super(name);
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		PackageSkeleton other = (PackageSkeleton) object;
		return new EqualsBuilder().append(name, other.name).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}
	
	@Override
	public String toString() {
		return name == null ? "" : "package " + name + ";";
	}
	
	
	public static PackageSkeleton sameAs(Class<?> klass) {
		return new PackageSkeleton(klass == null ? null : klass.getPackage().getName());
	}

}
