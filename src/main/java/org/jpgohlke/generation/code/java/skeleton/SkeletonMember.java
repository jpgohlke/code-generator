package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.StringUtils;

public abstract class SkeletonMember {
	
	private static final AccessModifier DEFAULT_ACCESS_MODIFIER = AccessModifier.PUBLIC;
	
	private String name;
	private AccessModifier accessModifier;
	protected boolean isStatic;
	
	
	public SkeletonMember(String name) {
		if(StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Each member of the skeleton must have a name.");
		}
		this.name = name;
		this.accessModifier = DEFAULT_ACCESS_MODIFIER;
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

}
