package org.jpgohlke.generation.code.java.skeleton;

import org.apache.commons.lang3.StringUtils;

public abstract class SkeletonMember {
	
	protected String name;
	protected AccessModifier accessModifier;
	protected boolean isStatic;
	
	
	public SkeletonMember(String name) {
		if(StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Each member of the skeleton must have a name.");
		}
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
