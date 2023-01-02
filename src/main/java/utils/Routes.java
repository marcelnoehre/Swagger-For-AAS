package utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;
import templates.Route;

public class Routes {
	private String baseUrl;
	private String aasIdShort;
	private String submodelIdShort = "";
	private String elementIdShort = "";
	private String cdIdShort = "";
	
	private static final Route[] ROUTES = new Route[] {Constants.GET_AAS, Constants.GET_SUBMODEL_LIST, Constants.PUT_AAS, Constants.DELETE_AAS, Constants.GET_ASSETS, Constants.PUT_ASSETS, Constants.GET_SUBMODEL, Constants.GET_ELEMENT_LIST, Constants.PUT_SUBMODEL, Constants.DELETE_SUBMODEL, Constants.GET_ELEMENT, Constants.PUT_ELEMENT, Constants.DELETE_ELEMENT, Constants.GET_CONCEPT_DESCRIPTION_LIST, Constants.GET_CONCEPT_DESCRIPTION, Constants.PUT_CONCEPT_DESCRIPTION, Constants.DELETE_CONCEPT_DESCRIPTION};
	
	public Routes(RestService restService, String baseUrl, String aasIdShort) {
		this.baseUrl = baseUrl;
		this.aasIdShort = aasIdShort;
		try {
			JSONParser parser = new JSONParser();
			ArrayList<String> submodelIds = new ArrayList<String>();
			JSONArray submodels = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(Constants.GET_SUBMODEL_LIST.getPath()))[1]);
			for(Object submodel : submodels) {
				try {
					submodelIds.add(((JSONObject) submodel).get("idShort").toString());	
				} catch(NullPointerException submodelException) {}
			}
			for(String submodelId : submodelIds) {
				this.submodelIdShort = submodelId;	
				JSONArray elements = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(Constants.GET_ELEMENT_LIST.getPath()))[1]);
				for(Object element : elements) {
					try {
						this.elementIdShort = ((JSONObject) element).get("idShorts").toString();
						break;
					} catch(NullPointerException elementException) {}
				}
				break;
			}
			JSONArray cds = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(Constants.GET_CONCEPT_DESCRIPTION_LIST.getPath()))[1]);
			for(Object cd : cds) {
				try {
					this.cdIdShort = ((JSONObject) cd).get("idShort").toString();
					break;
				} catch(NullPointerException cdException) {}
			}
		} catch (ParseException parseException) {}
	}
	
	public String getBaseUrl() {
		return this.baseUrl;
	}
	
	public static Route[] getRoutes() {
		return ROUTES;
	}
	
	public String getAASId() {
		return this.aasIdShort;
	}
	
	public String getSubmodelId() {
		return this.submodelIdShort;
	}
	
	public String getElementId() {
		return this.elementIdShort;
	}
	
	public String getCdId() {
		return this.cdIdShort;
	}
	
	public String getAASRouteWithId() {
		return this.replaceIDs(Constants.GET_AAS.getPath());
	}
	
	public String getAssetRouteWithId() {
		return this.replaceIDs(Constants.GET_ASSETS.getPath());
	}
	
	public String getSubmodelRouteWithId() {
		return this.replaceIDs(Constants.GET_SUBMODEL.getPath());
	}
	
	public String getElementRouteWithId() {
		return this.replaceIDs(Constants.GET_ELEMENT.getPath());
	}
	
	public String getConceptDescriptionRouteWithId() {
		return this.replaceIDs(Constants.GET_CONCEPT_DESCRIPTION.getPath());
	}
	
	public String replaceIDs(String route) {
		return route.replace("{aas.idShort}", this.aasIdShort).replace("{submodel.idShort}", this.submodelIdShort).replace("{element.idShort}", this.elementIdShort).replace("{cd.idShort}", this.cdIdShort);
	}
}
