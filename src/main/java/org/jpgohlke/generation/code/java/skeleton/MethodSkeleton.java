package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public class MethodSkeleton extends SkeletonMember implements Comparable<MethodSkeleton> {
	
	private static final AccessModifier DEFAULT_ACCESS_MODIFIER = AccessModifier.PUBLIC;
	private static final String DEFAULT_RETURN_TYPE = "void";
	
	private boolean isSynchronized;
	private String returnType;
	private List<VariableSkeleton> arguments = new ArrayList<VariableSkeleton>();
	private Set<String> throwables = new TreeSet<String>();
	private String body;
	
	
	public MethodSkeleton(String name) {
		this(DEFAULT_ACCESS_MODIFIER, name);
	}
	
	public MethodSkeleton(AccessModifier accessModifier, String name) {
		this(accessModifier, DEFAULT_RETURN_TYPE, name);
	}
	
	public MethodSkeleton(AccessModifier accessModifier, String returnType, String name) {
		super(name, accessModifier);
		this.returnType = returnType;
		arguments = new ArrayList<VariableSkeleton>();
	}
	
	
	
	public void setArguments(List<VariableSkeleton> arguments) {
		this.arguments = arguments;
	}
	
	public List<VariableSkeleton> getArguments() {
		return arguments;
	}
	
	public void addArgument(VariableSkeleton argument) {
		if(arguments == null) {
			arguments = new ArrayList<VariableSkeleton>();
		}
		arguments.add(argument);
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
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return body;
	}
	
	public Set<String> getThrowables() {
		return throwables;
	}
	
	public void setThrowables(Set<String> throwables) {
		this.throwables = throwables;
	}
	
	public void addThrowable(Class<? extends Throwable> throwable) {
		addThrowable(throwable.getSimpleName());
	}
	
	public void addThrowable(String throwable) {
		throwables.add(throwable);
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
			signature += newLines(1);
		}
		String access = getAccessModifier().toString();
		if(isNotBlank(access)) signature += access + " ";
		if(isSynchronized) signature += "synchronized ";
		if(isNotBlank(returnType)) signature += returnType + " ";
		signature += getName() + "(";
		if(isNotEmpty(arguments)) signature += join(arguments, ", ");;
		signature += ") ";
		if(isNotEmpty(throwables)) signature += "throws " + join(throwables, ", ");
		signature += "{" + newLines(1);
		signature += indent(isEmpty(body) ? newLines(1) : body);
		signature += newLines(1) + "}";
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
