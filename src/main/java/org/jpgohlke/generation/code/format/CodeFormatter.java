package org.jpgohlke.generation.code.format;

import org.apache.commons.lang3.StringUtils;

public class CodeFormatter {
	
	private static final String NEW_LINE_STRING = StringUtils.LF;
	private static final String INDENTATION_STRING = "\t";
	
	public static String newLines(int number) {
		return StringUtils.repeat(NEW_LINE_STRING, number);
	}
	
	public static String indent(String string) {
		return indent(string, 1);
	}
	
	public static String indent(String string, int level) {
		if(StringUtils.isEmpty(string)) {
			return StringUtils.repeat(INDENTATION_STRING, level);
		}
		String result = "";
		for(String line : string.split("\n")) {
			result += StringUtils.repeat(INDENTATION_STRING, level) + line + "\n";
		}
		result = StringUtils.chomp(result);
		return result;
	}

}
