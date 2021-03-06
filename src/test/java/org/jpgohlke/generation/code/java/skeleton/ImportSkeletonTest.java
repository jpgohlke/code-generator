package org.jpgohlke.generation.code.java.skeleton;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jpgohlke.generation.code.java.skeleton.ImportSkeleton;
import org.junit.Before;
import org.junit.Test;

public class ImportSkeletonTest {
	
	private ImportSkeleton skeleton;
	
	
	@Before
	public void setup() {
		skeleton = new ImportSkeleton(BigDecimal.class);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_NullClass() {
		new ImportSkeleton((Class<?>) null);
	}
	
	@Test
	public void testConstructor_Class() {
		assertEquals("java.math.BigDecimal", skeleton.getName());
	}
	
	@Test
	public void testToString() {
		assertEquals("import java.math.BigDecimal;", skeleton.toString());
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
		assertFalse(skeleton.equals("sdfsdf"));
	}
	
	@Test
	public void testEquals_False() {
		ImportSkeleton other = new ImportSkeleton(Connection.class);
		assertFalse(skeleton.equals(other));
	}
	
	@Test
	public void testEquals_True() {
		ImportSkeleton other = new ImportSkeleton(BigDecimal.class);
		assertTrue(skeleton.equals(other));
	}
	
	@Test(expected = NullPointerException.class)
	public void testCompareTo_Null() {
		skeleton.compareTo(null);
	}
	
	@Test
	public void testCompareTo_Equals() {
		assertEquals(0, skeleton.compareTo(new ImportSkeleton(BigDecimal.class)));
	}
	
	@Test
	public void testCompareTo_LessThan() {
		assertTrue(new ImportSkeleton(Connection.class).compareTo(skeleton) > 0);
	}
	
	@Test
	public void testCompareTo_GreaterThan() {
		assertTrue(skeleton.compareTo(new ImportSkeleton(Connection.class)) < 0);
	}

}
