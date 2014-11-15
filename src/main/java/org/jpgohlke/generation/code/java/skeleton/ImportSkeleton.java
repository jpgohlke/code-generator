package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ImportSkeleton extends SkeletonMember {
	
	
	public ImportSkeleton(String name) {
		super(name);
	}
	
	public ImportSkeleton(Class<?> klass) {
		super(klass.getCanonicalName());
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		ImportSkeleton other = (ImportSkeleton) object;
		return new EqualsBuilder()
						.append(name, other.name)
						.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}
	
	@Override
	public String toString() {
		return "import " + name + ";";
	}

}
