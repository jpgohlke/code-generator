package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.join;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class MethodSkeleton extends SkeletonMember {
	
	private boolean override;
	private List<VariableSkeleton> arguments;
	
	
	public MethodSkeleton(String name) {
		super(name);
		arguments = new ArrayList<VariableSkeleton>();
	}
	
	
	@Override
	public boolean equals(Object object) {
		//TODO;
		return false;
	}
	
	@Override
	public int hashCode() {
		//TODO;
		return 0;
	}
	
	@Override
	public String toString() {
		String signature = "";
		signature += override ? "@Override" : "";
		//TODO: access modifiers, etc.
		signature += name + "(";
		if(CollectionUtils.isNotEmpty(arguments)) {
			join(arguments.iterator(), ", ");
			signature = signature.substring(0, signature.length() - 2);
		}
		signature += ") " + newLines(1);
		signature += indent(newLines(1));
		signature += "}";
		return signature;
	}

}
