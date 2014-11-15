package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
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
	
	

}
