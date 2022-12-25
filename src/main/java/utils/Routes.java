package utils;

public class Routes {
	private static final String[] GET_AAS = new String[] {"GET","/aas/{aas.idShort}/", "core;complete;thumbnail;aasenv"};
	private static final String[] PUT_AAS = new String[] {"PUT", "/aas/"};
	private static final String[] DELETE_AAS = new String[] {"DELETE", "/aas/{aas.idShort}"};
	
	private static final String[] GET_ASSETS = new String[] {"GET", "/assets/{asset.idShort}", ""};
	private static final String[] PUT_ASSETS = new String[] {"PUT", "/assets/"};
	
	private static final String[] GET_SUBMODEL_LIST = new String[] {"GET", "/aas/{aas.idShort}/submodels/", "core;deep;complete"};
	private static final String[] GET_SUBMODEL = new String[] {"GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/", "core;deep;complete"};
	private static final String[] PUT_SUBMODEL = new String[] {"PUT", "/aas/{aas.idShort}/submodels/"};
	private static final String[] DELETE_SUBMODEL = new String[] {"DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}"};
	
	private static final String[] GET_ELEMENT_LIST = new String[] {"GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/table", ""};
	private static final String[] GET_ELEMENT = new String[] {"GET", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/", "core;deep;complete"};
	private static final String[] PUT_ELEMENT = new String[] {"PUT", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}/"};
	private static final String[] DELETE_ELEMENT = new String[] {"DELETE", "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/{element.idShort}"};

	private static final String[] GET_CONCEPT_DESCRIPTION_LIST = new String[] {"GET", "/aas/{aas.idShort}/cds"};
	private static final String[] GET_CONCEPT_DESCRIPTION = new String[] {"GET", "/aas/{aas.idShort}/cds/{cd.idShort}"};
	private static final String[] PUT_CONCEPT_DESCRIPTION = new String[] {"PUT", "/aas/{aas.idShort]/cds/"};
	private static final String[] DELETE_CONCEPT_DESCRIPTION = new String[] {"DELETE", "/aas/{aas.idShort}/cds/{cd.idShort}"};
	
	private static final String[][] ROUTES = new String[][] {GET_AAS, PUT_AAS, DELETE_AAS, GET_ASSETS, PUT_ASSETS, GET_SUBMODEL_LIST, GET_SUBMODEL, PUT_SUBMODEL, DELETE_SUBMODEL, GET_ELEMENT_LIST, GET_ELEMENT, PUT_ELEMENT, DELETE_ELEMENT, GET_CONCEPT_DESCRIPTION_LIST, GET_CONCEPT_DESCRIPTION, PUT_CONCEPT_DESCRIPTION, DELETE_CONCEPT_DESCRIPTION};
	
	public static String[][] getRoutes() {
		return ROUTES;
	}
}
