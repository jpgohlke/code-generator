package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.chop;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MethodSkeleton extends SkeletonMember implements Comparable<MethodSkeleton> {
	
	private boolean isSynchronized; //TODO
	private List<VariableSkeleton<?>> arguments;
	
	
	public MethodSkeleton(String name) {
		super(name);
		arguments = new ArrayList<VariableSkeleton<?>>();
	}
	
	
	
	public void setArguments(List<VariableSkeleton<?>> arguments) {
		this.arguments = arguments;
	}
	
	public List<VariableSkeleton<?>> getArguments() {
		return arguments;
	}
	
	public void setSynchronized(boolean isSynchronized) {
		this.isSynchronized = isSynchronized;
	}
	
	public boolean isSynchronized() {
		return isSynchronized;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		
		MethodSkeleton other = (MethodSkeleton) object;
		
		if(isStatic() != other.isStatic()) return false;
		if(getName() != other.getName()) return false;
		
		if(arguments == null && other.arguments == null) return true;
		if(arguments == null || other.arguments == null) return false;
		
		if(arguments.equals(other.arguments)) return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).append(arguments).toHashCode();
	}
	
	@Override
	public String toString() {
		String signature = "";
		if(isNotEmpty(getAnnotations())) {
			signature = join(getAnnotations(), newLines(1));
		}
		signature = chop(signature);
		
		//TODO: access modifiers, etc.
		signature += getName() + "(";
		if(isNotEmpty(arguments)) {
			join(arguments.iterator(), ", ");
			signature = signature.substring(0, signature.length() - 2);
		}
		signature += ") " + newLines(1);
		signature += indent(newLines(1));
		signature += "}";
		return signature;
	}

	@Override
	public int compareTo(MethodSkeleton other) {
		
		if(isStatic() && !other.isStatic()) return 1;
		if(!isStatic() && other.isStatic()) return -1;
		
		boolean override = getAnnotations().contains(AnnotationSkeleton.OVERRIDE);
		boolean otherOverride = other.getAnnotations().contains(AnnotationSkeleton.OVERRIDE);
		
		if(override && !otherOverride) return 1;
		if(!override && otherOverride) return -1;
		
		int accessOrder = getAccessModifier().compareTo(other.getAccessModifier());
		if(accessOrder != 0) return accessOrder;
		
		int nameOrder = getName().compareTo(other.getName());
		if(nameOrder != 0) return nameOrder;
		
		return other.arguments.size() - arguments.size();
	}

}
