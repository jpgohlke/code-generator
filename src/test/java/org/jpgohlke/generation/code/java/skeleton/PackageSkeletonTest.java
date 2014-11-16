package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PackageSkeletonTest {
	
	private PackageSkeleton skeleton;
	
	
	@Before
	public void setup() {
		skeleton = new PackageSkeleton("java.math");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSameAs_Null() {
		PackageSkeleton.sameAs(null);
	}
	
	@Test
	public void testSameAs() {
		PackageSkeleton skeleton = PackageSkeleton.sameAs(BigDecimal.class);
		assertNotNull(skeleton);
		assertEquals("java.math", skeleton.getName());
	}
	
	@Test
	public void testToString() {
		assertEquals("package java.math;", skeleton.toString());
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
		assertFalse(skeleton.equals("sfsdfsd"));
	}
	
	@Test
	public void testEquals_False() {
		PackageSkeleton other = new PackageSkeleton("ohjhfs");
		assertFalse(skeleton.equals(other));
	}
	
	@Test
	public void testEquals_True() {
		PackageSkeleton other = new PackageSkeleton("java.math");
		assertTrue(skeleton.equals(other));
	}
	
	@Test
	public void testCompareTo_SameName() {
		PackageSkeleton other = new PackageSkeleton("java.math");
		assertEquals(0, skeleton.compareTo(other));
	}
	
	@Test
	public void testCompareTo_DifferentName() {
		PackageSkeleton other = new PackageSkeleton("sdfsd");
		assertEquals(-9, skeleton.compareTo(other));
	}

}
