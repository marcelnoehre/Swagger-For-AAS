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
	
	private static final Route GET_AAS = new Route("AAS", "Information about the Asset Administration Shell", "GET", "/aas/{aas.idShort}/", new String[] {"core", "complete", "thumbnail", "aasenv"});
	private static final Route GET_SUBMODEL_LIST = new Route("AAS", "A list of all submodels of an AAS", "GET", "/aas/{aas.idShort}/submodels/", new String[] {"core", "deep", "complete"});
	private static final Route PUT_AAS = new Route("AAS", "Add or update a Asset Administration Shell", "PUT", "/aas/", null);
	private static final Route DELETE_AAS = new Route("AAS", "Delete a Asset Adminstration Shell","DELETE", "/aas/{aas.idShort}", null);
	
	private static final Route GET_ASSETS = new Route("Asset", "Information about the Asset", "GET", "/assets/{aas.idShort}", null);
	private static final Route PUT_ASSETS = new Route("Asset", "Add or update a Asset", "PUT", "/assets/", null);
	
	private static final Route GET_SUBMODEL = new Route("Submodel", "Information about the submodel", "GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/", new String[] {"core", "deep", "complete"});
	private static final Route GET_ELEMENT_LIST = new Route("Submodel", "A list of all elements of an submodel", "GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/table", null);
	private static final Route PUT_SUBMODEL = new Route("Submodel", "Add or update asubmodel", "PUT", "/aas/{aas.idShort}/submodels/", null);
	private static final Route DELETE_SUBMODEL = new Route("Submodel", "Delete a submodel", "DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}", null);
	
	private static final Route GET_ELEMENT = new Route("Submodelelement", "Information about the submodelelement", "GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", new String[] {"core", "deep", "complete"});
	private static final Route PUT_ELEMENT = new Route("Submodelelement", "Add or update a submodelelement", "PUT", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", null);
	private static final Route DELETE_ELEMENT = new Route("Submodelelement", "Delete a submodelelement", "DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}", null);

	private static final Route GET_CONCEPT_DESCRIPTION_LIST = new Route("Concept Description", "A list of all concept descriptions of an AAS", "GET", "/aas/{aas.idShort}/cds", null);
	private static final Route GET_CONCEPT_DESCRIPTION = new Route("Concept Description", "Information about a concept description", "GET", "/aas/{aas.idShort}/cds/{cd.idShort}",null);
	private static final Route PUT_CONCEPT_DESCRIPTION = new Route("Concept Description", "Add or update a concept description", "PUT", "/aas/{aas.idShort]/cds/", null);
	private static final Route DELETE_CONCEPT_DESCRIPTION = new Route("Concept Description", "Delete a concept description", "DELETE", "/aas/{aas.idShort}/cds/{cd.idShort}", null);
	
	private static final Route[] ROUTES = new Route[] {GET_AAS, GET_SUBMODEL_LIST, PUT_AAS, DELETE_AAS, GET_ASSETS, PUT_ASSETS, GET_SUBMODEL, GET_ELEMENT_LIST, PUT_SUBMODEL, DELETE_SUBMODEL, GET_ELEMENT, PUT_ELEMENT, DELETE_ELEMENT, GET_CONCEPT_DESCRIPTION_LIST, GET_CONCEPT_DESCRIPTION, PUT_CONCEPT_DESCRIPTION, DELETE_CONCEPT_DESCRIPTION};
	
	public Routes(RestService restService, String baseUrl, String aasIdShort) {
		this.baseUrl = baseUrl;
		this.aasIdShort = aasIdShort;
		try {
			JSONParser parser = new JSONParser();
			ArrayList<String> submodelIds = new ArrayList<String>();
			JSONArray submodels = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(GET_SUBMODEL_LIST.getRoute()))[1]);
			for(Object submodel : submodels) {
				try {
					submodelIds.add(((JSONObject) submodel).get("idShort").toString());	
				} catch(NullPointerException submodelException) {}
			}
			for(String submodelId : submodelIds) {
				this.submodelIdShort = submodelId;	
				JSONArray elements = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(GET_ELEMENT_LIST.getRoute()))[1]);
				for(Object element : elements) {
					try {
						this.elementIdShort = ((JSONObject) element).get("idShorts").toString();
						break;
					} catch(NullPointerException elementException) {}
				}
				break;
			}
			JSONArray cds = (JSONArray) parser.parse(restService.httpGet(baseUrl+this.replaceIDs(GET_CONCEPT_DESCRIPTION_LIST.getRoute()))[1]);
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
		return this.replaceIDs(GET_AAS.getRoute());
	}
	
	public String getAssetRouteWithId() {
		return this.replaceIDs(GET_ASSETS.getRoute());
	}
	
	public String getSubmodelRouteWithId() {
		return this.replaceIDs(GET_SUBMODEL.getRoute());
	}
	
	public String getElementRouteWithId() {
		return this.replaceIDs(GET_ELEMENT.getRoute());
	}
	
	public String getConceptDescriptionRouteWithId() {
		return this.replaceIDs(GET_CONCEPT_DESCRIPTION.getRoute());
	}
}
