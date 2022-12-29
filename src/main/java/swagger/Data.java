package swagger;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;
import templates.Contact;
import templates.Definition;
import templates.Property;
import templates.Request;
import templates.Response;
import templates.Route;
import templates.ExternalDocs;
import templates.Info;
import templates.License;
import templates.Parameter;
import templates.Path;
import templates.Tag;
import utils.Checks;
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
			i++;
		}
		return tags;
	}
	
	public static Path[] generatePaths(RestService restService, Routes routes) {
		Path[] paths = new Path[Routes.getRoutes().length];
		int i = 0;
		for(Route route : Routes.getRoutes()) {
			String[] consumes;
			String[] produces;
			if(route.getType().equals("get")) {
				consumes = new String[] {};
				produces = new String[] {"application/json"};
			} else {
				consumes = new String[] {"application/json"};
				produces = new String[] {"text/plain"};
			}
			Parameter[] parameters = routes.getParameters(route);
			//TODO: generate responses
			Response[] responses = new Response[] {};
			Request request = new Request(route.getTag(), new String[] {route.getTag()}, route.getSummary(), "", null, consumes, produces, parameters, responses, "false");
			paths[i] = new Path(route.getRoute(), request);
			i++;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Definition[] generateDefinitions(RestService restService, Routes routes) {
		try {
			JSONParser parser = new JSONParser();
			Definition[] definitions = new Definition[6];
			JSONObject response = (JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getAASRouteWithId())[1]);
			Property[] apiResponseProperties = new Property[] {
					new Property("resultCode", "integer", "int32", null, null, "200", null, null, null),
					new Property("type", "string", null, null, null, "application/json", null, null, null),
					new Property("message", "string", null, null, null, response.toString(), null, null, null)
			};
			definitions[0] = new Definition("ApiResponse", "object", new String[] {"resultCode", "type", "message"}, apiResponseProperties);
			String[] definitionNames = new String[] {"AssetAdministrationShell", "Asset", "Submodel", "SubmodelElement", "ConceptDescription"};
			JSONObject[] definitionExamples = new JSONObject[] {
					(JSONObject)parser.parse(response.get("AAS").toString()),
					(JSONObject) parser.parse(response.get("Asset").toString()),
					(JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getSubmodelRouteWithId())[1]),
					(JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getElementRouteWithId())[1]),
					(JSONObject) parser.parse(restService.httpGet(routes.getBaseUrl()+routes.getConceptDescriptionRouteWithId())[1])
			};
			for(int i = 0; i < definitionExamples.length; i++) {
				Property[] properties;
				if (definitionExamples[i].size() > 0) {
					properties = new Property[definitionExamples[i].size()];
					int j = 0;
			        for (Iterator iterator = definitionExamples[i].keySet().iterator(); iterator.hasNext();) {
			        	String key = (String) iterator.next();
			        	String value;
			        	String type;
			        	String format;
			        	try {
			        		value = (String) definitionExamples[i].get(key).toString();
			        		type = Checks.variableType(value);
			        		format = type.equals("integer")?"int64":null;
			        	} catch(NullPointerException nullPointer) {
			        		value = null;
			        		type = null;
			        		format = null;
			        	}
			        	properties[j] = new Property(key, type, format, null, null, value, null, null, null);
			        	j++;
			        }
			    } else {
			    	properties = null;
			    }
				definitions[i+1] = new Definition(definitionNames[i], "object", new String[]{"idShort"}, properties);
			}
			return definitions;
		} catch(ParseException parse) {
			return null;
		}
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
