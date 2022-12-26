package utils;

import java.util.Arrays;

public class Checks {
	public static boolean valueIsEmpty(String value) {
		return (value.trim().equals("") || value.equals(null));
	}
	
	public static String[] filterArray(String[] array) {
		return Arrays.stream(array).filter(value -> !value.trim().equals("") && !value.equals(null)).toArray(size -> new String[size]);
	}
}