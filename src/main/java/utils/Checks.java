package utils;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Checks {
	public static boolean valueIsEmpty(String value) {
		return (value == null) || value.trim().equals("");
	}
	
	public static String[] filterArray(String[] array) {
		return Arrays.stream(array).filter(value -> !value.trim().equals("") && !value.equals(null)).toArray(size -> new String[size]);
	}
	
	public static String variableType(String variable) {
	    try {
	        new JSONObject(variable);
	        return "object";
	    } catch (JSONException e) {
	        try {
	            new JSONArray(variable);
	            return "array";
	        } catch (JSONException ne) {
	    		if(Pattern.compile("^\\d+$").matcher(variable).matches()) {
	    			return "integer";
	    		} else {
	    			return "string";
	    		}
	        }
	    }				
	}
}