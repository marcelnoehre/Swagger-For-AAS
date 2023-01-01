package utils;

import templates.Parameter;
import templates.Response;
import templates.Route;
import templates.Schema;

public class Constants {
	public static final Route GET_AAS = new Route("Asset Administration Shell", "Information about the Asset Administration Shell", "get", "/aas/{aas.idShort}/", new String[] {"core", "complete", "thumbnail", "aasenv"});
	public static final Route GET_SUBMODEL_LIST = new Route("Asset Administration Shell", "A list of all submodels of an AAS", "get", "/aas/{aas.idShort}/submodels/", new String[] {"core", "deep", "complete"});
	public static final Route PUT_AAS = new Route("Asset Administration Shell", "Add or update a Asset Administration Shell", "put", "/aas/", null);
	public static final Route DELETE_AAS = new Route("Asset Administration Shell", "Delete a Asset Adminstration Shell","delete", "/aas/{aas.idShort}", null);
	
	public static final Route GET_ASSETS = new Route("Asset", "Information about the Asset", "get", "/assets/{aas.idShort}", null);
	public static final Route PUT_ASSETS = new Route("Asset", "Add or update a Asset", "put", "/assets/", null);
	
	public static final Route GET_SUBMODEL = new Route("Submodel", "Information about the submodel", "get", "/aas/{aas.idShort}/submodels/{submodel.idShort}/", new String[] {"core", "deep", "complete"});
	public static final Route GET_ELEMENT_LIST = new Route("Submodel", "A list of all elements of an submodel", "get", "/aas/{aas.idShort}/submodels/{submodel.idShort}/table", null);
	public static final Route PUT_SUBMODEL = new Route("Submodel", "Add or update asubmodel", "put", "/aas/{aas.idShort}/submodels/", null);
	public static final Route DELETE_SUBMODEL = new Route("Submodel", "Delete a submodel", "delete", "/aas/{aas.idShort}/submodels/{submodel.idShort}", null);
	
	public static final Route GET_ELEMENT = new Route("Submodelelement", "Information about the submodelelement", "get", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", new String[] {"core", "deep", "complete"});
	public static final Route PUT_ELEMENT = new Route("Submodelelement", "Add or update a submodelelement", "put", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/", null);
	public static final Route DELETE_ELEMENT = new Route("Submodelelement", "Delete a submodelelement", "delete", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}", null);

	public static final Route GET_CONCEPT_DESCRIPTION_LIST = new Route("Concept Description", "A list of all concept descriptions of an AAS", "get", "/aas/{aas.idShort}/cds", null);
	public static final Route GET_CONCEPT_DESCRIPTION = new Route("Concept Description", "Information about a concept description", "get", "/aas/{aas.idShort}/cds/{cd.idShort}",null);
	public static final Route PUT_CONCEPT_DESCRIPTION = new Route("Concept Description", "Add or update a concept description", "put", "/aas/{aas.idShort}/cds/", null);
	public static final Route DELETE_CONCEPT_DESCRIPTION = new Route("Concept Description", "Delete a concept description", "delete", "/aas/{aas.idShort}/cds/{cd.idShort}", null);
	
	
	
	public static final String EXAMPLE_AAS = "/aas/example";
	public static final String EXAMPLE_SUBMODEL_LIST = "/aas/example/submodels/";
	public static final String EXAMPLE_ASSETS = "/assets/example";
	public static final String EXAMPLE_SUBMODEL = "/aas/{aas.idShort}/submodels/example";
	public static final String EXAMPLE_ELEMENT_LIST = "/aas/{aas.idShort}/submodels/example/table";
	public static final String EXAMPLE_ELEMENT = "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/example";
	public static final String EXAMPLE_CD_LIST = "/aas/example/cds";
	public static final String EXAMPLE_CD = "/aas/{aas.idShort}/cds/example";
	
	public static final Parameter AAS_ID_SHORT = new Parameter("aas.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	public static final Parameter SUBMODEL_ID_SHORT = new Parameter("submodel.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	public static final Parameter ELEMENT_ID_SHORT = new Parameter("element.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	public static final Parameter CD_ID_SHORT = new Parameter("cd.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	
	public static final Schema API_RESPONSE = new Schema(null, null, null, "#/definitions/ApiResponse");
	public static final Response[] PUT_ASSET_EXAMPLE_RESPONSE = new Response[] {
			new Response("200", "OK (updated)", API_RESPONSE, null),
			new Response("400", "No payload or content type is not JSON.", API_RESPONSE, null)
	};
}
