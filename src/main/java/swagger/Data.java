package swagger;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;
import templates.Contact;
import templates.ExternalDocs;
import templates.Info;
import templates.License;
import templates.Tag;
import utils.Routes;

public class Data {
	public static Info generateInfo(RestService restService, Routes routes) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getAASRouteWithId())[1]);
			String description = "";
			String version = null;
			String title = "Asset Adminstration Shell: " + routes.getAASId();
			String termsOfService = null;
			Contact contact = null;
			License license = null;
			try {
				JSONArray descriptions = (JSONArray) ((JSONObject) parser.parse(json.get("Asset").toString())).get("descriptions");
				int i = 0; 
				for(Object descriptionObj : descriptions) {
					JSONObject descriptionValue = (JSONObject) descriptionObj;
					if(i == 0 || descriptionValue.get("language").equals("EN")) {
						description = "REST server for access to the Asset Administration Shell: " + routes.getAASId() + ". The AAS belongs to the asset: " + descriptionValue.get("text");
					}
					i++;
				}
			} catch(NullPointerException desc) {
				description = "REST server for access to the Asset Administration Shell: " + routes.getAASId();
			}
			return new Info(description, version, title, termsOfService, contact, license);
		} catch (ParseException parse) {
			return null;
		}
	}
	
	public static Tag[] generateTags() {
		String[] tagNames = new String[] {"Asset Administration Shell", "Asset", "Submodel", "Submodelelement", "Concept Description"};
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		for(String tag : tagNames) {
			tagList.add(new Tag(tag, "Operations that belong to " + tag + "s", null));
		}
		Tag[] tags = new Tag[tagList.size()];
		int i = 0;
		for(Tag tag : tagList) {
			tags[i] = tag;
		}
		return tags;
	}
	
	public static ExternalDocs generateExternalDocs(RestService restService, Routes routes) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getAASRouteWithId())[1]);
			String description = "";
			String url = "";
			try {
				JSONObject identification = (JSONObject)((JSONObject) parser.parse(json.get("Asset").toString())).get("identification");
				if(identification.get("type").toString().equals("IRI")) {
					url = identification.get("id").toString();
				}
				try {
					JSONArray descriptions = (JSONArray) ((JSONObject) parser.parse(json.get("Asset").toString())).get("descriptions");
					int i = 0; 
					for(Object descriptionObj : descriptions) {
						JSONObject descriptionValue = (JSONObject) descriptionObj;
						if(i == 0 || descriptionValue.get("language").equals("EN")) {
							description = "More information about " + descriptionValue.get("text");
						}
						i++;
					}
				} catch(NullPointerException desc) {
					description = "More information about " + routes.getAASId();
				}
				return new ExternalDocs(description, url);
			} catch(NullPointerException urlException) {
				return null;
			}
		} catch(ParseException parse) {
			return null;
		}
	}
}
