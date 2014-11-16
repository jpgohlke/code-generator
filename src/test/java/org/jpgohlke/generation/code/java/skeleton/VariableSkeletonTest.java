package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VariableSkeletonTest {
	
	private VariableSkeleton variable;
	
	
	@Before
	public void setup() {
		variable = new VariableSkeleton(int.class, "asd");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullClass() {
		new VariableSkeleton(null, "asd");
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
		assertFalse(variable.equals(new VariableSkeleton(int.class, "dsfd")));
	}
	
	@Test
	public void testEquals_True() {
		assertTrue(variable.equals(new VariableSkeleton(int.class, "asd")));
	}
	
	@Test
	public void testToString_Primitive() {
		assertEquals("int asd", variable.toString());
	}
	
	@Test
	public void testCompareTo_SameName() {
		assertEquals(0, variable.compareTo(new VariableSkeleton(int.class, "asd")));
	}
	
	@Test
	public void testCompareTo_DifferentName() {
		assertEquals(-10, variable.compareTo(new VariableSkeleton(int.class, "kappa")));
	}

}
