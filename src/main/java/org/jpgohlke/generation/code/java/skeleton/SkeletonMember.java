package org.jpgohlke.generation.code.java.skeleton;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public abstract class SkeletonMember {
	
	private static final AccessModifier DEFAULT_ACCESS_MODIFIER = AccessModifier.PACKAGE;
	
	private String name;
	private AccessModifier accessModifier;
	private boolean isStatic;
	private Set<AnnotationSkeleton> annotations;
	
	
	public SkeletonMember(String name) {
		if(StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Each member of the skeleton must have a name.");
		}
		this.name = name;
		this.accessModifier = DEFAULT_ACCESS_MODIFIER;
		this.annotations = new LinkedHashSet<AnnotationSkeleton>();
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Each member of the skeleton must have a name.");
		}
		this.name = name;
	}
	
	public AccessModifier getAccessModifier() {
		return accessModifier;
	}
	
	public void setAccessModifier(AccessModifier accessModifier) {
		if(accessModifier == null) {
			throw new IllegalArgumentException("Each member of the skeleton must have an access modifier.");
		}
		this.accessModifier = accessModifier;
	}
	
	public boolean isStatic() {
		return isStatic;
	}
	
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public Set<AnnotationSkeleton> getAnnotations() {
		return annotations;
	}
	
	public void addAnnotation(AnnotationSkeleton annotation) {
		annotations.add(annotation);
	}

}
