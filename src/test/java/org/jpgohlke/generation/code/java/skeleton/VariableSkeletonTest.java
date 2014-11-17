package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import org.jpgohlke.generation.code.java.attribute.AccessModifier;
import org.jpgohlke.generation.code.java.skeleton.VariableSkeleton;
import org.junit.Before;
import org.junit.Test;

public class VariableSkeletonTest {
	
	private VariableSkeleton<Integer> variable;
	
	
	@Before
	public void setup() {
		variable = new VariableSkeleton<Integer>(int.class, "asd");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullClass() {
		new VariableSkeleton<Integer>(null, "asd");
	}
	
	@Test
	public void testEquals_Null() {
		assertFalse(variable.equals(null));
	}
	
	@Test
	public void testEquals_This() {
		assertTrue(variable.equals(variable));
	}
	
	@Test
	public void testEquals_WrongClass() {
		assertFalse(variable.equals("dsgdfghdf"));
	}
	
	@Test
	public void testEquals_False() {
		assertFalse(variable.equals(new VariableSkeleton<Integer>(int.class, "dsfd")));
	}
	
	@Test
	public void testEquals_True() {
		assertTrue(variable.equals(new VariableSkeleton<Integer>(int.class, "asd")));
	}
	
	@Test
	public void testToString_Primitive() {
		assertEquals("int asd", variable.toString());
	}
	
	@Test
	public void testToString_Value() {
		variable.setValue(3);
		assertEquals("int asd = 3", variable.toString());
	}
	
	@Test
	public void testToString_WithAccessModifier() {
		variable.setValue(3);
		variable.setAccessModifier(AccessModifier.PUBLIC);
		assertEquals("public int asd = 3", variable.toString());
	}
	
	@Test
	public void testCompareTo_SameName() {
		assertEquals(0, variable.compareTo(new VariableSkeleton<Integer>(int.class, "asd")));
	}
	
	@Test
	public void testCompareTo_DifferentName() {
		assertEquals(-10, variable.compareTo(new VariableSkeleton<Integer>(int.class, "kappa")));
	}

}
