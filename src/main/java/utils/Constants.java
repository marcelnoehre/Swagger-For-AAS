package utils;

import templates.Parameter;
import templates.Route;
import templates.Schema;

public class Constants {
	public static final Route GET_AAS = new Route("AAS", "Information about the Asset Administration Shell", "GET", "/aas/{aas.idShort}/", new String[] {"core", "complete", "thumbnail", "aasenv"});
	public static final Route GET_SUBMODEL_LIST = new Route("AAS", "A list of all submodels of an AAS", "GET", "/aas/{aas.idShort}/submodels/", new String[] {"core", "deep", "complete"});
	public static final Route PUT_AAS = new Route("AAS", "Add or update a Asset Administration Shell", "PUT", "/aas/", null);
	public static final Route DELETE_AAS = new Route("AAS", "Delete a Asset Adminstration Shell","DELETE", "/aas/{aas.idShort}", null);
	
	public static final Route GET_ASSETS = new Route("Asset", "Information about the Asset", "GET", "/assets/{aas.idShort}", null);
	public static final Route PUT_ASSETS = new Route("Asset", "Add or update a Asset", "PUT", "/assets/", null);
	
	public static final Route GET_SUBMODEL = new Route("Submodel", "Information about the submodel", "GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/", new String[] {"core", "deep", "complete"});
	public static final Route GET_ELEMENT_LIST = new Route("Submodel", "A list of all elements of an submodel", "GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/table", null);
	public static final Route PUT_SUBMODEL = new Route("Submodel", "Add or update asubmodel", "PUT", "/aas/{aas.idShort}/submodels/", null);
	public static final Route DELETE_SUBMODEL = new Route("Submodel", "Delete a submodel", "DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}", null);
	
	public static final Route GET_ELEMENT = new Route("Submodelelement", "Information about the submodelelement", "GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", new String[] {"core", "deep", "complete"});
	public static final Route PUT_ELEMENT = new Route("Submodelelement", "Add or update a submodelelement", "PUT", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", null);
	public static final Route DELETE_ELEMENT = new Route("Submodelelement", "Delete a submodelelement", "DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}", null);

	public static final Route GET_CONCEPT_DESCRIPTION_LIST = new Route("Concept Description", "A list of all concept descriptions of an AAS", "GET", "/aas/{aas.idShort}/cds", null);
	public static final Route GET_CONCEPT_DESCRIPTION = new Route("Concept Description", "Information about a concept description", "GET", "/aas/{aas.idShort}/cds/{cd.idShort}",null);
	public static final Route PUT_CONCEPT_DESCRIPTION = new Route("Concept Description", "Add or update a concept description", "PUT", "/aas/{aas.idShort]/cds/", null);
	public static final Route DELETE_CONCEPT_DESCRIPTION = new Route("Concept Description", "Delete a concept description", "DELETE", "/aas/{aas.idShort}/cds/{cd.idShort}", null);
	
	public static final String DELETE_EXAMPLE_AAS = "/aas/example";
	public static final String DELETE_EXAMPLE_SUBMODEL = "/aas/{aas.idShort}/submodels/example";
	public static final String DELETE_EXAMPLE_ELEMENT = "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/example";
	public static final String DELETE_EXAMPLE_CD = "/aas/{aas.idShort}/cds/example";
	
	public static final Parameter AAS_ID_SHORT = new Parameter("aas.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	public static final Parameter SUBMODEL_ID_SHORT = new Parameter("submodel.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	public static final Parameter ELEMENT_ID_SHORT = new Parameter("element.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	public static final Parameter CD_ID_SHORT = new Parameter("cd.idShort", "path", "The unique ID of an AAS", "true", "string", null, null, null, null, null, null);
	
	public static final Schema API_RESPONSE = new Schema(null, null, null, "#/definitions/ApiResponse");
}
