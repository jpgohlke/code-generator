package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jpgohlke.generation.code.java.attribute.AccessModifier;

public class ClassSkeleton extends SkeletonMember {
	
	private static final AccessModifier DEFAULT_ACCESS_MODIFIER = AccessModifier.PUBLIC;
		
	private boolean isEnum;
	
	private String extension;
	private String implementation;
	
	private PackageSkeleton packageDeclaration;
	private Set<ImportSkeleton> imports = new TreeSet<ImportSkeleton>();
	private Set<MethodSkeleton> constructors = new TreeSet<MethodSkeleton>();
	private Set<ClassSkeleton> innerClasses = new TreeSet<ClassSkeleton>();
	private Set<VariableSkeleton> fields = new TreeSet<VariableSkeleton>();
	private Set<MethodSkeleton> methods = new TreeSet<MethodSkeleton>();
	
	
	public ClassSkeleton(String name) {
		super(name, DEFAULT_ACCESS_MODIFIER);
	}
	
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public void setExtension(Class<?> extension) {
		this.extension = extension == null ? null : extension.getSimpleName();
	}
	
	public void setExtension(ClassSkeleton extension) {
		this.extension = extension == null ? null : extension.getName();
	}
	
	public String getImplementation() {
		return implementation;
	}
	
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	
	public void setImplementation(Class<?> implementation) {
		this.implementation = implementation == null ? null : implementation.getSimpleName();
	}
	
	public void setImplementation(ClassSkeleton implementation) {
		this.implementation = implementation == null ? null : implementation.getName();
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


	public Set<ImportSkeleton> getImports() {
		return imports;
	}


	public void setImports(Set<ImportSkeleton> imports) {
		this.imports = imports;
	}
	
	public void addImport(ImportSkeleton importSkeleton) {
		if(imports == null) {
			imports = new TreeSet<ImportSkeleton>();
		}
		imports.add(importSkeleton);
	}


	public Set<MethodSkeleton> getConstructors() {
		return constructors;
	}


	public void setConstructors(Set<MethodSkeleton> constructors) {
		this.constructors = constructors;
	}


	public Set<ClassSkeleton> getInnerClasses() {
		return innerClasses;
	}


	public void setInnerClasses(Set<ClassSkeleton> innerClasses) {
		this.innerClasses = innerClasses;
	}


	public Set<VariableSkeleton> getFields() {
		return fields;
	}


	public void setFields(Set<VariableSkeleton> fields) {
		this.fields = fields;
	}
	
	public void addField(VariableSkeleton field) {
		addField(field, false);
	}
	
	public void addField(VariableSkeleton field, boolean addGetterAndSetter) {
		if(fields == null) {
			fields = new TreeSet<VariableSkeleton>();
		}
		if(field != null) {
			fields.add(field);
			if(addGetterAndSetter) {
				methods.add(field.getGetter());
				methods.add(field.getSetter());
			}
		}
	}


	public Set<MethodSkeleton> getMethods() {
		return methods;
	}


	public void setMethods(Set<MethodSkeleton> methods) {
		this.methods = methods;
	}
	
	public void addMethod(MethodSkeleton method) {
		if(methods == null) {
			methods = new TreeSet<MethodSkeleton>();
		}
		methods.add(method);
	}


	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object == this) return true;
		if(object.getClass() != getClass()) return false;
		ClassSkeleton other = (ClassSkeleton) object;
		return new EqualsBuilder()
						.append(getName(), other.getName())
						.append(packageDeclaration, other.packageDeclaration)
						.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).append(packageDeclaration).toHashCode();
	}
	
	@Override
	public String toString() {
		
		String string = "";
		
		string += packageToString();
		string += newLines(2);
		
		String imports = importsToString();
		if(isNotBlank(imports)) {
			string += imports;
			string += newLines(2);
		}
		
		string += declarationToString();
		string += indent(newLines(2));
		
		String fields = fieldsToString();
		if(isNotBlank(fields)) {
			string += indent(fields);
			string += indent(newLines(2));
		}
		
		String constructors = constructorsToString();
		if(isNotBlank(constructors)) {
			string += indent(constructors);
			string += indent(newLines(3));
		}
		
		String methods = methodsToString();
		if(isNotBlank(methods)) {
			string += indent(methods);
			string += indent(newLines(2));
		}
		
		String innerClasses = innerClassesToString();
		if(isNotBlank(innerClasses)) {
			string += indent(innerClasses);
			string += indent(newLines(2));		
		}
		
		string += "}";
		return string;
	}
	
	
	
	
	// Below are isolated for good JUnit tests
	// The @Override toString() is too complex
	
	String packageToString() {
		return packageDeclaration != null ? packageDeclaration.toString() : "";
	}
	
	String importsToString() {
		return isNotEmpty(imports) ? join(imports, newLines(1)) : "";
	}
	
	String declarationToString() {
		String string = "";
		if(getAccessModifier() != AccessModifier.PACKAGE) {
			string += getAccessModifier() + " ";
		}
		string += isStatic() ? "static " : "";
		string += isEnum ? "enum" : "class";
		string += " " + getName();
		if(isNotBlank(extension)) {
			string += " " + extension;
		}
		if(isNotBlank(implementation)) {
			string += " implements " + implementation;
		}
		string += " {";
		return string;
	}
	
	String constructorsToString() {
		return isNotEmpty(constructors) ? join(constructors, newLines(2)) : "";
	}
	
	String innerClassesToString() {
		return isNotEmpty(innerClasses) ? join(innerClasses, newLines(2)) : "";
	}
	
	String fieldsToString() {
		return isNotEmpty(fields) ? join(fields, ";" + newLines(1)) + ";" : "";
	}
	
	String methodsToString() {
		return isNotEmpty(methods) ? join(methods, newLines(2)) : "";
	}

}
