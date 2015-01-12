package org.jpgohlke.generation;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import org.jpgohlke.generation.code.java.attribute.AccessModifier;
import org.jpgohlke.generation.code.java.skeleton.ClassSkeleton;
import org.jpgohlke.generation.code.java.skeleton.ImportSkeleton;
import org.jpgohlke.generation.code.java.skeleton.MethodSkeleton;
import org.jpgohlke.generation.code.java.skeleton.PackageSkeleton;
import org.jpgohlke.generation.code.java.skeleton.VariableSkeleton;

import com.google.common.collect.Sets;

public class Main {
	
	public static void main(String[] args) {
		
		ClassSkeleton sk = new ClassSkeleton("TestClass");
		sk.setAccessModifier(AccessModifier.PUBLIC);
		sk.setPackage(new PackageSkeleton("org.jpgohlke"));
		sk.setImports(Sets.newHashSet(new ImportSkeleton(Date.class), new ImportSkeleton(BigDecimal.class)));
		sk.setFields(Sets.newHashSet(new VariableSkeleton<Integer>(Integer.class, "rawr"), new VariableSkeleton<String>(String.class, "kappa")));
		sk.setMethods(Sets.newHashSet(new MethodSkeleton("sup")));
		
		System.out.println(sk);
		
		/*
		private PackageSkeleton packageDeclaration;
		private Set<ImportSkeleton> imports;
		private Set<MethodSkeleton> constructors;
		private Set<ClassSkeleton> innerClasses;
		private Set<VariableSkeleton<?>> fields;
		private Set<MethodSkeleton> methods;
		*/
	}

}
