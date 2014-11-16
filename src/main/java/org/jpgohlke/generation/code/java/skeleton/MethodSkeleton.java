package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.join;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MethodSkeleton extends SkeletonMember implements Comparable<MethodSkeleton> {
	
	private boolean override;
	private List<VariableSkeleton> arguments;
	
	
	public MethodSkeleton(String name) {
		super(name);
		arguments = new ArrayList<VariableSkeleton>();
	}
	
	
	public void setOverride(boolean override) {
		this.override = override;
	}
	
	public boolean isOverride() {
		return override;
	}
	
	public void setArguments(List<VariableSkeleton> arguments) {
		this.arguments = arguments;
	}
	
	public List<VariableSkeleton> getArguments() {
		return arguments;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		
		MethodSkeleton other = (MethodSkeleton) object;
		
		if(isStatic != other.isStatic) return false;
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
		signature += override ? "@Override" : "";
		//TODO: access modifiers, etc.
		signature += getName() + "(";
		if(CollectionUtils.isNotEmpty(arguments)) {
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
		
		if(isStatic && !other.isStatic) return 1;
		if(!isStatic && other.isStatic) return -1;
		
		if(override && !other.override) return 1;
		if(!override && other.override) return -1;
		
		int accessOrder = getAccessModifier().compareTo(other.getAccessModifier());
		if(accessOrder != 0) return accessOrder;
		
		int nameOrder = getName().compareTo(other.getName());
		if(nameOrder != 0) return nameOrder;
		
		return other.arguments.size() - arguments.size();
	}

}
