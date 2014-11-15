package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ClassSkeleton extends SkeletonMember {
	
	private boolean isEnum;
	
	private PackageSkeleton packageDeclaration;
	private Set<ImportSkeleton> imports;
	private Set<ClassSkeleton> innerClasses;
	private Set<VariableSkeleton> fields;
	private Set<MethodSkeleton> methods;
	
	
	
	public ClassSkeleton(String name) {
		super(name);
	}
	
	
	public void setPackage(PackageSkeleton packageDeclaration) {
		this.packageDeclaration = packageDeclaration;
	}
	
	public PackageSkeleton getPackage() {
		return packageDeclaration;
	}
	
	public void setEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}
	
	public boolean isEnum() {
		return isEnum;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		ClassSkeleton other = (ClassSkeleton) object;
		return new EqualsBuilder()
						.append(name, other.name)
						.append(packageDeclaration, other.packageDeclaration)
						.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(packageDeclaration).toHashCode();
	}
	
	@Override
	public String toString() {
		
		//TODO: separate these String representations into other
		//methods in order to isolate tests
		
		String string = "";
		
		// Package
		if(packageDeclaration != null && isNotBlank(name)) {
			string += packageDeclaration;
			string += newLines(2);
		}
		
		// Imports
		if(isNotEmpty(imports)) {
			string += join(imports, "\n");
			string += newLines(2);
		}
		
		// Declaration
		String declaration = "";
		if(accessModifier != AccessModifier.PACKAGE) {
			declaration += accessModifier + " ";
		}
		declaration += isStatic ? "static " : "";
		declaration += isEnum ? "enum" : "class";
		declaration += name;
		string += declaration + " {";
		string += indent(newLines(2));
		
		// Inner Classes
		if(isNotEmpty(innerClasses)) {
			string += indent(join(innerClasses, "\n\n"));
			string += indent(newLines(3));
		}
		
		// Fields
		if(isNotEmpty(fields)) {
			string += indent(join(fields, ";\n"));
			string += ";";
			string += indent(newLines(2));
		}
		
		// Methods
		if(isNotEmpty(methods)) {
			string += indent(join(methods, "\n\n"));
			string += indent(newLines(2));
		}
		
		// Closing
		string += "}";
		return string;
	}
	

}