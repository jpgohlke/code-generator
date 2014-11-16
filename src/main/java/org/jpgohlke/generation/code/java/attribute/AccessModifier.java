package org.jpgohlke.generation.code.java.attribute;

import java.lang.reflect.Modifier;

public enum AccessModifier {
	
	PUBLIC, PACKAGE, PROTECTED, PRIVATE;
	
	@Override
	public String toString() {
		return this == PACKAGE ? "" : name().toLowerCase();
	}
	
	
	public static AccessModifier fromModifier(int modifier) {
		switch(modifier) {
		case Modifier.PUBLIC: 
			return PUBLIC;
		case Modifier.PROTECTED: 
			return PROTECTED;
		case Modifier.PRIVATE: 
			return PRIVATE;
		default: 
			return PACKAGE;
		}
	}

}
