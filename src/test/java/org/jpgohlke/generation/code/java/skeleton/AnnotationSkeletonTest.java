package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import org.jpgohlke.generation.code.java.skeleton.AnnotationSkeleton;
import org.junit.Before;
import org.junit.Test;

public class AnnotationSkeletonTest {
	
	private AnnotationSkeleton annotation;
	
	
	@Before
	public void setup() {
		annotation = new AnnotationSkeleton("Override");
	}
	
	
	@Test
	public void testToString_NoFields() {
		assertEquals("@Override", annotation.toString());
	}
	
	@Test
	public void testToString_OneField() {
		annotation.getFields().put("hi", "sup");
		assertEquals("@Override(hi = \"sup\")", annotation.toString());
	}
	
	@Test
	public void testToString_MultipleFields() {
		annotation.getFields().put("hi", "sup");
		annotation.getFields().put("yo", "word");
		annotation.getFields().put("kappa", "kappa");
		String expected = "@Override(hi = \"sup\", yo = \"word\", kappa = \"kappa\")";
		assertEquals(expected, annotation.toString());
	}
	
	@Test
	public void testEquals_Null() {
		assertFalse(annotation.equals(null));
	}
	
	@Test
	public void testEquals_This() {
		assertTrue(annotation.equals(annotation));
	}
	
	@Test
	public void testEquals_WrongClass() {
		assertFalse(annotation.equals("sdfsd"));
	}
	
	@Test
	public void testEquals_False() {
		assertFalse(annotation.equals(new AnnotationSkeleton("kappa")));
	}
	
	@Test
	public void testEquals_True() {
		assertTrue(annotation.equals(new AnnotationSkeleton("Override")));
	}
	
	@Test
	public void testAddField() {
		annotation.addField("sup", "yo");
		assertTrue(annotation.getFields().get("sup").equals("yo"));
	}
}
