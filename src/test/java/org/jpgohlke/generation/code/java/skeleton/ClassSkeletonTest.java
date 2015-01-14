package org.jpgohlke.generation.code.java.skeleton;

import static org.jpgohlke.generation.code.format.CodeFormatter.indent;
import static org.jpgohlke.generation.code.format.CodeFormatter.newLines;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jpgohlke.generation.code.java.attribute.AccessModifier;
import org.jpgohlke.generation.code.java.skeleton.ClassSkeleton;
import org.jpgohlke.generation.code.java.skeleton.PackageSkeleton;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ClassSkeletonTest {
	
	private ClassSkeleton skeleton;
	
	
	@Before
	public void setup() {
		skeleton = new ClassSkeleton("asdf");
		skeleton.setPackage(new PackageSkeleton("qwert"));
	}
	
	
	@Test
	public void testEquals_Null() {
		assertFalse(skeleton.equals(null));
	}
	
	@Test
	public void testEquals_This() {
		assertTrue(skeleton.equals(skeleton));
	}
	
	@Test
	public void testEquals_WrongClass() {
		assertFalse(skeleton.equals(BigDecimal.ZERO));
	}
	
	@Test
	public void testEquals_FalseName() {
		assertFalse(skeleton.equals(new ClassSkeleton("jkl")));
	}
	
	@Test
	public void testEquals_FalsePackage() {
		ClassSkeleton other = new ClassSkeleton("asdf");
		other.setPackage(new PackageSkeleton("asdfgh"));
		assertFalse(skeleton.equals(other));
	}
	
	@Test
	public void testEquals_True() {
		ClassSkeleton other = new ClassSkeleton("asdf");
		other.setPackage(new PackageSkeleton("qwert"));
		assertTrue(skeleton.equals(other));
	}
	
	@Test
	public void testSetExtension_NullClass() {
		skeleton.setExtension((Class<?>) null);
		assertNull(skeleton.getExtension());
	}
	
	@Test
	public void testSetExtension_Class() {
		skeleton.setExtension(String.class);
		assertEquals("String", skeleton.getExtension());
	}
	
	@Test
	public void testSetExtension_NullClassSkeleton() {
		skeleton.setExtension((ClassSkeleton) null);
		assertNull(skeleton.getExtension());
	}
	
	@Test
	public void testSetExtension_ClassSkeleton() {
		skeleton.setExtension(skeleton);
		assertEquals("asdf", skeleton.getExtension());
	}
	
	@Test
	public void testSetImplementation_NullClass() {
		skeleton.setImplementation((Class<?>) null);
		assertNull(skeleton.getImplementation());
	}
	
	@Test
	public void testSetImplementation_Class() {
		skeleton.setImplementation(String.class);
		assertEquals("String", skeleton.getImplementation());
	}
	
	@Test
	public void testSetImplementation_NullClassSkeleton() {
		skeleton.setImplementation((ClassSkeleton) null);
		assertNull(skeleton.getImplementation());
	}
	
	@Test
	public void testSetImplementation_ClassSkeleton() {
		skeleton.setImplementation(skeleton);
		assertEquals("asdf", skeleton.getImplementation());
	}
	
	@Test
	public void testPackageToString_Null() {
		skeleton.setPackage(null);
		assertEquals("", skeleton.packageToString());
	}
	
	@Test
	public void testPackageToString() {
		skeleton.setPackage(new PackageSkeleton("String"));
		assertEquals("package String;", skeleton.packageToString());
	}
	
	@Test
	public void testImportsToString_Null() {
		skeleton.setImports(null);
		assertEquals("", skeleton.importsToString());
	}
	
	@Test
	public void testImportsToString_EmptySet() {
		skeleton.setImports(Collections.<ImportSkeleton>emptySet());
		assertEquals("", skeleton.importsToString());
	}
	
	@Test
	public void testImportsToString_OneImport() {
		Set<ImportSkeleton> imports = new LinkedHashSet<ImportSkeleton>();
		imports.add(new ImportSkeleton(BigDecimal.class));
		skeleton.setImports(imports);
		assertEquals("import java.math.BigDecimal;", skeleton.importsToString());
	}
	
	@Test
	public void testImportsToString_MultipleImports() {
		Set<ImportSkeleton> imports = new LinkedHashSet<ImportSkeleton>();
		imports.add(new ImportSkeleton(BigDecimal.class));
		imports.add(new ImportSkeleton(Connection.class));
		imports.add(new ImportSkeleton(List.class));
		skeleton.setImports(imports);
		String expected = "import java.math.BigDecimal;\n";
		expected += "import java.sql.Connection;\n";
		expected += "import java.util.List;";
		assertEquals(expected, skeleton.importsToString());
	}
	
	@Test
	public void testImportsToString_MultipleSameImports() {
		Set<ImportSkeleton> imports = new LinkedHashSet<ImportSkeleton>();
		imports.add(new ImportSkeleton(BigDecimal.class));
		imports.add(new ImportSkeleton(Connection.class));
		imports.add(new ImportSkeleton(List.class));
		imports.add(new ImportSkeleton(List.class));
		skeleton.setImports(imports);
		String expected = "import java.math.BigDecimal;\n";
		expected += "import java.sql.Connection;\n";
		expected += "import java.util.List;";
		assertEquals(expected, skeleton.importsToString());
	}
	
	@Test
	public void testConstructorsToString_NullConstructors() {
		skeleton.setConstructors(null);
		assertEquals("", skeleton.constructorsToString());
	}
	
	@Test
	public void testConstructorsToString_EmptyConstructors() {
		skeleton.setConstructors(Collections.<MethodSkeleton>emptySet());
		assertEquals("", skeleton.constructorsToString());
	}
	
	@Test
	public void testConstructorsToString_OneConstructor() {
		MethodSkeleton constructor = new MethodSkeleton("Sup");
		constructor.setAccessModifier(AccessModifier.PUBLIC);
		constructor.setReturnType("");
		Set<MethodSkeleton> constructors = new LinkedHashSet<MethodSkeleton>();
		constructors.add(constructor);
		skeleton.setConstructors(constructors);
		String expected = "public Sup() {" + newLines(1);
		expected += indent(newLines(1));
		expected += "}";
		assertEquals(expected, skeleton.constructorsToString());
	}
	
	@Test
	public void testConstructorsToString_MultipleSameConstructors() {
		MethodSkeleton constructor = new MethodSkeleton("Sup");
		constructor.setAccessModifier(AccessModifier.PUBLIC);
		constructor.setReturnType("");
		MethodSkeleton constructor2 = new MethodSkeleton("Sup");
		constructor2.setAccessModifier(AccessModifier.PUBLIC);
		constructor2.setReturnType("");
		Set<MethodSkeleton> constructors = new LinkedHashSet<MethodSkeleton>();
		constructors.add(constructor);
		constructors.add(constructor2);
		skeleton.setConstructors(constructors);
		String expected = "public Sup() {" + newLines(1);
		expected += indent(newLines(1));
		expected += "}";
		assertEquals(expected, skeleton.constructorsToString());
	}
	
	@Test
	@Ignore("until MethodSkeleton toString() works")
	public void testConstructorsToString_MultipleConstructors() {
		MethodSkeleton constructor = new MethodSkeleton("Sup");
		constructor.setAccessModifier(AccessModifier.PUBLIC);
		MethodSkeleton constructor2 = new MethodSkeleton("Sup");
		constructor2.setAccessModifier(AccessModifier.PUBLIC);
		constructor2.setArguments(Arrays.<VariableSkeleton>asList(new VariableSkeleton(String.class, "kappa")));
		Set<MethodSkeleton> constructors = new LinkedHashSet<MethodSkeleton>();
		constructors.add(constructor);
		constructors.add(constructor2);
		skeleton.setConstructors(constructors);
		String expected = "public Sup() {\n";
		expected += "\t\n";
		expected += "}\n";
		expected += "\n";
		expected += "public Sup(String kappa) {\n";
		expected += "\t\n";
		expected += "}";
		assertEquals(expected, skeleton.constructorsToString());
	}
	
	/*
	String declarationToString() {
		String string = "";
		if(getAccessModifier() != AccessModifier.PACKAGE) {
			string += getAccessModifier() + " ";
		}
		string += isStatic() ? "static " : "";
		string += isEnum ? "enum" : "class";
		string += getName();
		if(isNotBlank(extension)) {
			string += " " + extension;
		}
		if(isNotBlank(implementation)) {
			string += " " + implementation;
		}
		string += " {";
		return string;
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
	 */

}
