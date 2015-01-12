package org.jpgohlke.generation.code.java.skeleton;

import org.jpgohlke.generation.code.java.attribute.AccessModifier;
import org.jpgohlke.generation.code.java.skeleton.SkeletonMember;
import org.junit.Test;

public class SkeletonMemberTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Null() {
		new SkeletonMember(null, AccessModifier.PACKAGE) { };
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyString() {
		new SkeletonMember("", AccessModifier.PACKAGE) { } ;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_BlankString() {
		new SkeletonMember("    ", AccessModifier.PACKAGE) { };
	}

}
