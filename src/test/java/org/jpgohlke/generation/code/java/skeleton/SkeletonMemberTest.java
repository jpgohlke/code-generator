package org.jpgohlke.generation.code.java.skeleton;

import org.jpgohlke.generation.code.java.skeleton.SkeletonMember;
import org.junit.Test;

public class SkeletonMemberTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_Null() {
		new SkeletonMember(null) { };
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_EmptyString() {
		new SkeletonMember("") { } ;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_BlankString() {
		new SkeletonMember("    ") { };
	}

}
