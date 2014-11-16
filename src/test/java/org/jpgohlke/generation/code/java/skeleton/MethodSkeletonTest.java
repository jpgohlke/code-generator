package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jpgohlke.generation.code.java.attribute.AccessModifier;
import org.junit.Before;
import org.junit.Test;

public class MethodSkeletonTest {
	
	private static final String METHOD_NAME = "toString";
	private static final List<VariableSkeleton> ARGUMENTS = Arrays.asList(
			new VariableSkeleton(List.class, "rawr"),
			new VariableSkeleton(Arrays.class, "doom")
		);
	
	private MethodSkeleton method;
	private MethodSkeleton other;
	
	
	@Before
	public void setup() {
		method = new MethodSkeleton(METHOD_NAME);
		method.isStatic = false;
		method.setArguments(ARGUMENTS);
		
		other = new MethodSkeleton(METHOD_NAME);
		other.isStatic = false;
		other.setArguments(ARGUMENTS);
	}
	
	
	@Test
	public void testEquals_Null() {
		assertFalse(method.equals(null));
	}
	
	@Test
	public void testEquals_This() {
		assertTrue(method.equals(method));
	}
	
	@Test
	public void testEquals_WrongClass() {
		assertFalse(method.equals(""));
	}
	
	@Test
	public void testEquals_Static() {
		other.isStatic = true;
		assertFalse(method.equals(other));
	}
	
	@Test
	public void testEquals_Name() {
		other.setName("kappa");
		assertFalse(method.equals(other));
	}
	
	@Test
	public void testEquals_NullArgumentsBoth() {
		method.setArguments(null);
		other.setArguments(null);
		assertTrue(method.equals(other));
	}
	
	@Test
	public void testEquals_NullArguments() {
		other.setArguments(null);
		assertFalse(method.equals(other));
	}
	
	@Test
	public void testEquals_Legit() {
		assertTrue(method.equals(other));
	}
	
	@Test
	public void testEquals_WrongArguments() {
		other.setArguments(Arrays.asList(new VariableSkeleton(String.class, "rawr")));
		assertFalse(method.equals(other));
	}
	
	@Test
	public void testCompareTo_StaticToNonStatic() {
		method.isStatic = true;
		other.isStatic = false;
		assertEquals(1, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_NonStaticToStatic() {
		method.isStatic = false;
		other.isStatic = true;
		assertEquals(-1, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_OverrideToNotOverride() {
		method.setOverride(true);
		other.setOverride(false);
		assertEquals(1, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_NotOverrideToOverride() {
		method.setOverride(false);
		other.setOverride(true);
		assertEquals(-1, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_DifferentAccessModifier() {
		method.setAccessModifier(AccessModifier.PACKAGE);
		other.setAccessModifier(AccessModifier.PRIVATE);
		assertEquals(-2, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_DifferentName() {
		method.setAccessModifier(AccessModifier.PACKAGE);
		other.setAccessModifier(AccessModifier.PACKAGE);
		other.setName("kappa");
		assertEquals(9, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_SameArguments() {
		assertEquals(0, method.compareTo(other));
	}
	
	@Test
	public void testCompareTo_DifferentArgumentsSize() {
		other.setArguments(Collections.<VariableSkeleton>emptyList());
		assertEquals(-2, method.compareTo(other));
	}

}
