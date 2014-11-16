package org.jpgohlke.generation.code.java.attribute;

import static org.jpgohlke.generation.code.java.attribute.AccessModifier.*;
import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;

public class AccessModifierTest {
	
	@Test
	public void testFromModifier_Public() {
		assertEquals(PUBLIC, fromModifier(Modifier.PUBLIC));
	}
	
	@Test
	public void testFromModifier_Protected() {
		assertEquals(PROTECTED, fromModifier(Modifier.PROTECTED));
	}
	
	@Test
	public void testFromModifier_Private() {
		assertEquals(PRIVATE, fromModifier(Modifier.PRIVATE));
	}
	
	@Test
	public void testFromModifier_Default() {
		assertEquals(PACKAGE, fromModifier(3784923));
	}
	
	@Test
	public void testToString_Package() {
		assertEquals("", PACKAGE.toString());
	}
	
	@Test
	public void testToString_Public() {
		assertEquals("public", PUBLIC.toString());
	}
	
	@Test
	public void testToString_Protected() {
		assertEquals("protected", PROTECTED.toString());
	}
	
	@Test
	public void testToString_Private() {
		assertEquals("private", PRIVATE.toString());
	}

}
