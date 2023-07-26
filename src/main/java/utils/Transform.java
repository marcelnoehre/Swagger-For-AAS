package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import templates.Definition;
import templates.Path;
import templates.Property;
import templates.Response;
import templates.Scope;
import templates.SecurityDefinition;

/**
 *
 * Collection of transform functionalities.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class Transform {
    /**
     * Transforms the array to a JSON String.
     *
     * @param array The array to get transformed
     * @return The transformed arary as a JSON String
     */
    @SuppressWarnings("unchecked")
    public static String arrayToJson(String[] array) {
        try {
            if (array.length >= 1) {
                JSONArray jsonArray = new JSONArray();
                for (String element : array) {
                    jsonArray.add(element);
                }
                return jsonArray.toJSONString();
            } else {
                return null;
            }
        } catch (NullPointerException arrayNull) {
            return null;
        }
    }

    /**
     * Transforms the instance of a template to a JSON String.
     *
     * @param keys The list of keys that belongs to the instance
     * @param values The list of values that belongs to the instance
     * @return The transformed instance as a JSON String
     */
    @SuppressWarnings("unchecked")
    public static String instanceToJson(String[] keys, String[] values) {
        JSONObject container = new JSONObject();
        boolean filled = false;
        for (int i = 0; i < keys.length; i++) {
            if (!Checks.valueIsEmpty(values[i])) {
                filled = true;
                if (values[i].equals("true")) {
                    container.put(keys[i], true);
                } else if (values[i].equals("false")) {
                    container.put(keys[i], false);
                } else if (Checks.variableType(values[i]).equals("integer")) {
                    container.put(keys[i], Integer.parseInt(values[i]));
                } else {
                    container.put(keys[i], values[i]);
                }
            }
        }
        if (filled) {
            return container.toJSONString();
        } else {
            return null;
        }
    }

    /**
     * Transform a list of path instaces to a JSON String.
     *
     * @param paths The list of path instances
     * @return The transformed list as JSON String
     */
    @SuppressWarnings("unchecked")
    public static String pathsToJson(Path[] paths) {
        try {
            JSONObject container = new JSONObject();
            for (Path path : paths) {
                container.put(path.getPath(), path.asJson());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }
    }

    /**
     * Transform a list of path instaces to a JSON String fitting the gson
     * format.
     *
     * @param paths The list of path instances
     * @return The transformed list as JSON String fitting the gson format
     */
    @SuppressWarnings("unchecked")
    public static String pathsToGson(Path[] paths) {
        try {
            JSONObject container = new JSONObject();
            for (Path path : paths) {
                container.put(path.getPath().substring(1), path.asJson());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }
    }

    /**
     * Transform a list of response instaces to a JSON String.
     *
     * @param responses The list of response instances
     * @return The transformed list as JSON String
     */
    @SuppressWarnings("unchecked")
    public static String responsesToJson(Response[] responses) {
        try {
            JSONObject container = new JSONObject();
            for (Response response : responses) {
                container.put(response.getResultCode(), response.asJson());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }
    }

    /**
     * Transform a list of definition instaces to a JSON String.
     *
     * @param definitions The list of definition instances
     * @return The transformed list as JSON String
     */
    @SuppressWarnings("unchecked")
    public static String definitionsToJson(Definition[] definitions) {
        try {
            JSONObject container = new JSONObject();
            for (Definition definition : definitions) {
                container.put(definition.getId(), definition.asJson());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }
    }

    /**
     * Transform a list of property instaces to a JSON String.
     *
     * @param properties The list of property instances
     * @return The transformed list as JSON String
     */
    @SuppressWarnings("unchecked")
    public static String propertiesToJson(Property[] properties) {
        try {
            JSONObject container = new JSONObject();
            for (Property property: properties) {
                container.put(property.getId(), property.asJson());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }
    }

    /**
     * Transform a list of security definition instaces to a JSON String.
     *
     * @param securityDefinitions The list of security definition instances
     * @return The transformed list as JSON String
     */
    @SuppressWarnings("unchecked")
    public static String securityDefinitionsToJson(SecurityDefinition[] securityDefinitions) {
        try {
            JSONObject container = new JSONObject();
            for (SecurityDefinition securityDefinition : securityDefinitions) {
                System.out.println(securityDefinition.asJson());
                container.put(securityDefinition.getId(), securityDefinition.asJson());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }

    }

    /**
     * Transform a list of scope instaces to a JSON String.
     *
     * @param scopes The list of scope instances
     * @return The transformed list as JSON String
     */
    @SuppressWarnings("unchecked")
    public static String scopesToJson(Scope[] scopes) {
        try {
            JSONObject container = new JSONObject();
            for (Scope scope: scopes) {
                container.put(scope.getId(), scope.getInfo());
            }
            return container.toJSONString();
        } catch (NullPointerException nullPointer) {
            return "{}";
        }
    }

    /**
     * Adjust the JSON String to remove stacked backslashes.
     *
     * @param json The JSON String to get adjusted
     * @return The adjusted JSON String
     */
public static String adjustJson(String json) {
    String tmp = "";
    while (tmp != json) {
        tmp = json;
        json = json.replace("\\\\", "\\");
    }
    return json.replace("\\/", "/")
            .replace("\\\"", "\"")
            .replace("idShort}\"", "idShort}\"\"")
            .replace("\"{", "{")
            .replace("}\"", "}")
            .replace("\"[\"", "[\"")
            .replace("\"]\"", "\"]")
            .replace("\"[{", "[{")
            .replace("}]\"", "}]");
}

    /**
     * Remove special chars to compare complex strings.
     *
     * @param value string to get transformed
     * @return input without special chars
     */
    public static String removeSpecialChars(String value) {
        return value.replace("ä", "").replace("ö", "").replace("ü", "")
                .replace("Ä", "").replace("Ö", "").replace("Ü", "")
                .replace("ß", "").replace("�", "");
    }

    /**
     * Transform a object to a list.
     *
     * @param obj Object to transform to a list
     * @return the created list
     */
    public static List<?> objectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }
}
