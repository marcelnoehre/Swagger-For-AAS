package utils;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Collection of check funktionalties.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Checks {
    /**
     * Check whether a value is empty.
     *
     * @param value The value to get checked
     * @return Whether the value is empty or not
     */
    public static boolean valueIsEmpty(final String value) {
        return (value == null) || value.trim().equals("");
    }

    /**
     * Filters empty elements out of an array.
     *
     * @param array The array to get filtered
     * @return The filtered array without empty elements
     */
    public static String[] filterArray(final String[] array) {
        return Arrays.stream(array).filter(
                value -> !value.trim().equals("")
                && !value.equals(null)).toArray(size -> new String[size]);
    }

    /**
     * Check the type of a variable based on the value.
     *
     * @param variable The variable to get checked
     * @return The type of the variable
     */
    public static String variableType(final String variable) {
        try {
            new JSONObject(variable);
            return "object";
        } catch (JSONException jsonObject) {
            try {
                new JSONArray(variable);
                return "array";
            } catch (JSONException jsonArray) {
                if (Pattern.compile("^\\d+$").matcher(variable).matches()) {
                    return "integer";
                } else {
                    return "string";
                }
            }
        }
    }
}
