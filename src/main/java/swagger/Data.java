package swagger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;
import templates.Contact;
import templates.Info;
import templates.License;
import utils.Routes;

public class Data {
	public static Info generateInfo(RestService restService, Routes routes) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getAASRoute())[1]);
			String description = "";
			String version = null;
			String title = "Asset Adminstration Shell: " + routes.getAASID();
			String termsOfService = null;
			Contact contact = null;
			License license = null;
			try {
				JSONArray descriptions = (JSONArray) ((JSONObject) parser.parse(json.get("Asset").toString())).get("descriptions");
				int i = 0; 
				for(Object descriptionObj : descriptions) {
					JSONObject descriptionValue = (JSONObject) descriptionObj;
					if(i == 0 || descriptionValue.get("language").equals("EN")) {
						description = "REST server for access to the Asset Administration Shell: " + routes.getAASID() + ". The AAS belongs to the asset: " + descriptionValue.get("text");
					}
					i++;
				}
			} catch(NullPointerException desc) {
				description = "REST server for access to the Asset Administration Shell: " + routes.getAASID();
			}
			return new Info(description, version, title, termsOfService, contact, license);
		} catch (ParseException parse) {
			parse.printStackTrace();
			return null;
		}
	}
}
