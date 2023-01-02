package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import templates.Definition;
import templates.Path;
import templates.Property;
import templates.Response;
import templates.Scope;
import templates.SecurityDefinition;

public class Transform {
	@SuppressWarnings("unchecked")
	public static String arrayToJson(String[] array) {
		try {
			if(array.length >= 1) {
				JSONArray jsonArray = new JSONArray();
				for(String element : array) {
					jsonArray.add(element);
				}
				return jsonArray.toJSONString();
			} else {
				return null;
			}
		} catch(NullPointerException arrayNull) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String instanceToJson(String[] keys, String[] values) {
		JSONObject container = new JSONObject();
		boolean filled = false;
		for(int i = 0; i < keys.length; i++) {
			if(!Checks.valueIsEmpty(values[i])) {
				filled = true;
				container.put(keys[i], values[i]);
			}
		}
		if(filled) {
			return container.toJSONString();
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String pathsToJson(Path[] paths) {
		try {
			JSONObject container = new JSONObject();
			for(Path path : paths) {
				container.put(path.getPath(), path.asJson());
			}
			return container.toJSONString();
		} catch(NullPointerException nullPointer) {
			return "{}";
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String responsesToJson(Response[] responses) {
		try {
			JSONObject container = new JSONObject();
			for(Response response : responses) {
				container.put(response.getResultCode(), response.asJson());
			}
			return container.toJSONString();
		} catch(NullPointerException nullPointer) {
			return "{}";
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String definitionsToJson(Definition[] definitions) {
		try {
			JSONObject container = new JSONObject();
			for(Definition definition : definitions) {
				container.put(definition.getId(), definition.asJson());
			}
			return container.toJSONString();
		} catch(NullPointerException nullPointer) {
			return "{}";
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String propertiesToJson(Property[] properties) {
		try {
			JSONObject container = new JSONObject();
			for(Property property: properties) {
				container.put(property.getId(), property.asJson());
			}
			return container.toJSONString();
		} catch(NullPointerException nullPointer) {
			return "{}";
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String securityDefinitionsToJson(SecurityDefinition[] securityDefinitions) {
		try {
			JSONObject container = new JSONObject();
			for(SecurityDefinition securityDefinition : securityDefinitions) {
				System.out.println(securityDefinition.asJson());
				container.put(securityDefinition.getId(), securityDefinition.asJson());
			}
			return container.toJSONString();
		} catch(NullPointerException nullPointer) {
			return "{}";
		}

	}
	
	@SuppressWarnings("unchecked")
	public static String scopesToJson(Scope[] scopes) {
		try {
			JSONObject container = new JSONObject();
			for(Scope scope: scopes) {
				container.put(scope.getId(), scope.getInfo());
			}
			return container.toJSONString();
		} catch(NullPointerException nullPointer) {
			return "{}";
		}
	}
	
	public static String adjustFinalJson(String json) {
		String tmp = "";
		while(tmp != json) {
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
}