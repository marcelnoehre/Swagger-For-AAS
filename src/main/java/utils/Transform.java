package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import templates.Path;

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
			if(Checks.valueIsEmpty(values[i])) {
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
}