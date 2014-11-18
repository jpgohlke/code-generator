package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.chop;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MethodSkeleton extends SkeletonMember implements Comparable<MethodSkeleton> {
	
	private boolean isSynchronized;
	private String returnType;
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
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType == null ? null : returnType.getSimpleName();
	}
	
	public void setReturnType(ClassSkeleton returnType) {
		this.returnType = returnType == null ? null : returnType.getName();
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		
		MethodSkeleton other = (MethodSkeleton) object;
		
		if(arguments == null) arguments = new ArrayList<>();
		if(other.arguments == null) other.arguments = new ArrayList<>();
		
		return new EqualsBuilder()
						.append(getName(), other.getName())
						.append(isStatic(), other.isStatic())
						.append(arguments, other.arguments)
						.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).append(arguments).toHashCode();
	}
	
	@Override
	public String toString() {
		String signature = "";
		if(isNotEmpty(getAnnotations())) {
			signature += join(getAnnotations(), newLines(1));
			signature += chop(signature);
		}
		String access = getAccessModifier().toString();
		if(isNotBlank(access)) signature += access + " ";
		if(isSynchronized) signature += "synchronized ";
		if(isNotBlank(returnType)) signature += returnType + " ";
		signature += getName() + "(";
		if(isNotEmpty(arguments)) signature += join(arguments, ", ");;
		signature += ") {" + newLines(1);
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
