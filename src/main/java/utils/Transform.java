package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import templates.Definition;
import templates.Path;
import templates.Response;

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
		JSONObject container = new JSONObject();
		for(Path path : paths) {
			container.put(path.getPath(), path.asJson());
		}
		return container.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public static String responsesToJson(Response[] responses) {
		JSONObject container = new JSONObject();
		for(Response response : responses) {
			container.put(response.getResultCode(), response.asJson());
		}
		return container.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public static String definitionsToJson(Definition[] definitions) {
		JSONObject container = new JSONObject();
		for(Definition definition : definitions) {
			container.put(definition.getId(), definition.asJson());
		}
		return container.toJSONString();
	}
	
	public static String adjustFinalJson(String json) {
		String tmp = "";
		while(tmp != json) {
			tmp = json;
			json = json.replace("\\\\", "\\");
		}
		return json.replace("\\/", "/").replace("\\\"", "\"").replace("idShort}\"", "idShort}\"\"").replace("\"{","{").replace("}\"","}").replace("\"[","[").replace("]\"","]");
	}
}