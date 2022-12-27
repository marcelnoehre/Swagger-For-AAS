package utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;

public class Routes {
	private String baseUrl;
	private String aasIdShort;
	private String submodelIdShort = "";
	private String elementIdShort = "";
	private String cdIdShort = "";
	
	private static final String[] GET_AAS = new String[] {"GET","/aas/{aas.idShort}/", "core;complete;thumbnail;aasenv"};
	private static final String[] GET_SUBMODEL_LIST = new String[] {"GET", "/aas/{aas.idShort}/submodels/", "core;deep;complete"};
	private static final String[] PUT_AAS = new String[] {"PUT", "/aas/"};
	private static final String[] DELETE_AAS = new String[] {"DELETE", "/aas/{aas.idShort}"};
	
	private static final String[] GET_ASSETS = new String[] {"GET", "/assets/{aas.idShort}", ""};
	private static final String[] PUT_ASSETS = new String[] {"PUT", "/assets/"};
	
	private static final String[] GET_SUBMODEL = new String[] {"GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/", "core;deep;complete"};
	private static final String[] GET_ELEMENT_LIST = new String[] {"GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/table", ""};
	private static final String[] PUT_SUBMODEL = new String[] {"PUT", "/aas/{aas.idShort}/submodels/"};
	private static final String[] DELETE_SUBMODEL = new String[] {"DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}"};
	
	private static final String[] GET_ELEMENT = new String[] {"GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", "core;deep;complete"};
	private static final String[] PUT_ELEMENT = new String[] {"PUT", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/"};
	private static final String[] DELETE_ELEMENT = new String[] {"DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}"};

	private static final String[] GET_CONCEPT_DESCRIPTION_LIST = new String[] {"GET", "/aas/{aas.idShort}/cds"};
	private static final String[] GET_CONCEPT_DESCRIPTION = new String[] {"GET", "/aas/{aas.idShort}/cds/{cd.idShort}"};
	private static final String[] PUT_CONCEPT_DESCRIPTION = new String[] {"PUT", "/aas/{aas.idShort]/cds/"};
	private static final String[] DELETE_CONCEPT_DESCRIPTION = new String[] {"DELETE", "/aas/{aas.idShort}/cds/{cd.idShort}"};
	
	private static final String[][] ROUTES = new String[][] {GET_AAS, GET_SUBMODEL_LIST, PUT_AAS, DELETE_AAS, GET_ASSETS, PUT_ASSETS, GET_SUBMODEL, GET_ELEMENT_LIST, PUT_SUBMODEL, DELETE_SUBMODEL, GET_ELEMENT, PUT_ELEMENT, DELETE_ELEMENT, GET_CONCEPT_DESCRIPTION_LIST, GET_CONCEPT_DESCRIPTION, PUT_CONCEPT_DESCRIPTION, DELETE_CONCEPT_DESCRIPTION};
	
	public Routes(RestService restService, String baseUrl, String aasIdShort) {
		this.baseUrl = baseUrl;
		this.aasIdShort = aasIdShort;
		try {
			JSONParser parser = new JSONParser();
			ArrayList<String> submodelIds = new ArrayList<String>();
			JSONArray submodels = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(GET_SUBMODEL_LIST[1]))[1]);
			for(Object submodel : submodels) {
				try {
					submodelIds.add(((JSONObject) submodel).get("idShort").toString());	
				} catch(NullPointerException submodelException) {}
			}
			for(String submodelId : submodelIds) {
				this.submodelIdShort = submodelId;	
				JSONArray elements = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(GET_ELEMENT_LIST[1]))[1]);
				for(Object element : elements) {
					try {
						this.elementIdShort = ((JSONObject) element).get("idShorts").toString();
						break;
					} catch(NullPointerException elementException) {}
				}
				break;
			}
			JSONArray cds = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(GET_CONCEPT_DESCRIPTION_LIST[1]))[1]);
			for(Object cd : cds) {
				try {
					this.cdIdShort = ((JSONObject) cd).get("idShort").toString();
					break;
				} catch(NullPointerException cdException) {}
			}
		} catch (ParseException parseException) {}
	}
	
	private String replaceIDs(String route) {
		return route.replace("{aas.idShort}", this.aasIdShort).replace("{submodel.idShort}", this.submodelIdShort).replace("{element.idShort}", this.elementIdShort).replace("{cd.idShort}", this.cdIdShort);
	}
	
	public String getBaseUrl() {
		return this.baseUrl;
	}
	
	public static String[][] getRoutes() {
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
		return this.replaceIDs(GET_AAS[1]);
	}
	
	public String getAssetRouteWithId() {
		return this.replaceIDs(GET_ASSETS[1]);
	}
	
	public String getSubmodelRouteWithId() {
		return this.replaceIDs(GET_SUBMODEL[1]);
	}
	
	public String getElementRouteWithId() {
		return this.replaceIDs(GET_ELEMENT[1]);
	}
	
	public String getConceptDescriptionRouteWithId() {
		return this.replaceIDs(GET_CONCEPT_DESCRIPTION[1]);
	}
}
