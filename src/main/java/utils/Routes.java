package utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;
import templates.Route;

/**
 *
 * Service to handle routes.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class Routes {
    private String baseUrl;
    private String aasIdShort;
    private String submodelIdShort = "";
    private String elementIdShort = "";
    private String cdIdShort = "";
    private static final Route[] ROUTES = new Route[] {
            Constants.PUT_AAS,
            Constants.GET_ASSETS,
            Constants.PUT_ASSETS,
            Constants.GET_SUBMODEL_LIST,
            Constants.PUT_SUBMODEL,
            Constants.GET_ELEMENT_LIST,
            Constants.PUT_ELEMENT,
            Constants.GET_CONCEPT_DESCRIPTION_LIST,
            Constants.PUT_CONCEPT_DESCRIPTION
    };
    private static final Route[][] MULTI_ROUTES = new Route[][] {
            new Route[] {Constants.GET_AAS, Constants.DELETE_AAS},
            new Route[] {Constants.GET_SUBMODEL, Constants.DELETE_SUBMODEL},
            new Route[] {Constants.GET_ELEMENT, Constants.DELETE_ELEMENT},
            new Route[] {Constants.GET_CONCEPT_DESCRIPTION, Constants.DELETE_CONCEPT_DESCRIPTION}
    };
    private static ArrayList<String> INVALID_ROUTES = new ArrayList<String>();

    /**
     * Sets up ids to fill routes.
     *
     * @param restService The service to handle REST requests
     * @param baseUrl The base url of all requests
     * @param aasIdShort The id to get the aas
     */
    public Routes(RestService restService, String baseUrl, String aasIdShort) {
        this.baseUrl = baseUrl;
        this.aasIdShort = aasIdShort;
        try {
            JSONParser parser = new JSONParser();
            ArrayList<String> submodelIds = new ArrayList<String>();
            JSONArray submodels = (JSONArray) parser.parse(restService.httpGet(
                    baseUrl + this.replaceIDs(Constants.GET_SUBMODEL_LIST.getPath()))[1]);
            for (Object submodel : submodels) {
                try {
                    submodelIds.add(((JSONObject) submodel).get("idShort").toString());
                } catch (NullPointerException submodelException) { }
            }
            for (String submodelId : submodelIds) {
                this.submodelIdShort = submodelId;
                boolean activeElementList = true;
                JSONArray elements = null;
                try {
                    elements = (JSONArray) parser.parse(
                            restService.httpGet(baseUrl + this.replaceIDs(Constants.GET_ELEMENT_LIST.getPath()))[1]);
                } catch(Exception e) {
                    elements = (JSONArray) parser.parse(new org.json.JSONObject(restService.httpGet(baseUrl + this.replaceIDs(
                            Constants.GET_SUBMODEL.getPath())+ "/complete")[1]).getJSONArray("submodelElements").toString());
                    activeElementList = false;
                }
                if(elements.toString().equals("[]")) {
                    elements = (JSONArray) parser.parse(new org.json.JSONObject(restService.httpGet(baseUrl + this.replaceIDs(
                            Constants.GET_SUBMODEL.getPath())+ "/complete")[1]).getJSONArray("submodelElements").toString());
                    activeElementList = false;
                }
                for (Object element : elements) {
                    try {
                        JSONObject jsonElement = ((JSONObject) element);
                        String id = activeElementList ? "idShorts" : "idShort";
                        this.elementIdShort = jsonElement.get(id).toString();
                        if(!this.elementIdShort.equals("")) {
                            break;   
                        }
                    } catch (NullPointerException elementException) { }
                }
                if(!this.elementIdShort.equals("")) {
                    break;
                }
            }
            JSONArray cds = (JSONArray) parser.parse(
                    restService.httpGet(baseUrl + this.replaceIDs(Constants.GET_CONCEPT_DESCRIPTION_LIST.getPath()))[1]);
            for (Object cd : cds) {
                try {
                    this.cdIdShort = ((JSONObject) cd).get("idShort").toString();
                    break;
                } catch (NullPointerException cdException) { }
            }
        } catch (ParseException parseException) { }
        for(Route[] routeArray : MULTI_ROUTES) {
            for(Route route : routeArray) {
                validateRoute(restService, route);    
            }
        }
        for(Route route : ROUTES) {
            validateRoute(restService, route);
        }
    }
    
    private void validateRoute(RestService restService, Route route) {
        if(route.getType().equals("get")) {
            String response = restService.httpGet(baseUrl + this.replaceIDs(route.getPath()))[1];
            try {
                new org.json.JSONObject(response);
            } catch(Exception object) {
                try {
                    new org.json.JSONArray(response);
                } catch(Exception array) {
                    INVALID_ROUTES.add(route.getPath());   
                }
            }
        }
    }

    /**
     * Get the base url.
     *
     * @return The base url
     */
    public String getBaseUrl() {
        return this.baseUrl;
    }

    /**
     * Get the list of multi routes.
     *
     * @return The list of multi routes
     */
    public static Route[][] getMultiRoutes() {
        return MULTI_ROUTES;
    }

    /**
     * Get the list of routes.
     *
     * @return The list of routes
     */
    public static Route[] getRoutes() {
        return ROUTES;
    }
    
    /**
     * Get the list of invalid routes.
     * 
     * @return The list of invalid routes
     */
    public static ArrayList<String> getInvalidRoutes() {
        return INVALID_ROUTES;
    }

    /**
     * Get the id of the aas.
     *
     * @return The id of the aas
     */
    public String getAASId() {
        return this.aasIdShort;
    }

    /**
     * Get the id of a submodel.
     *
     * @return The id of a submodel
     */
    public String getSubmodelId() {
        return this.submodelIdShort;
    }

    /**
     * Get the id of a element.
     *
     * @return The id of a element
     */
    public String getElementId() {
        return this.elementIdShort;
    }

    /**
     * Get the id of concept description.
     *
     * @return The id of a concept description
     */
    public String getCdId() {
        return this.cdIdShort;
    }

    /**
     * Get the route to the aas with filled ids.
     *
     * @return The route to the aas with filled ids
     */
    public String getAASRouteWithId() {
        return this.replaceIDs(Constants.GET_AAS.getPath());
    }

    /**
     * Get the route to a assets with filled ids.
     *
     * @return The route to a assets with filled ids
     */
    public String getAssetRouteWithId() {
        return this.replaceIDs(Constants.GET_ASSETS.getPath());
    }

    /**
     * Get the route to a submodellist with filled ids.
     *
     * @return The route to a submodellist with filled ids
     */
    public String getSubmodelListRouteWithId() {
        return this.replaceIDs(Constants.GET_SUBMODEL_LIST.getPath());
    }

    /**
     * Get the route to a submodel with filled ids.
     *
     * @return The route to a submodel with filled ids
     */
    public String getSubmodelRouteWithId() {
        return this.replaceIDs(Constants.GET_SUBMODEL.getPath());
    }

    /**
     * Get the route to a elementlist with filled ids.
     *
     * @return The route to a elementlist with filled ids
     */
    public String getElementListRouteWithId() {
        return this.replaceIDs(Constants.GET_ELEMENT_LIST.getPath());
    }

    /**
     * Get the route to a element with filled ids.
     *
     * @return The route to a element with filled ids
     */
    public String getElementRouteWithId() {
        return this.replaceIDs(Constants.GET_ELEMENT.getPath());
    }

    /**
     * Get the route to a concept description with filled ids.
     *
     * @return The route to a concept description with filled ids
     */
    public String getConceptDescriptionRouteWithId() {
        return this.replaceIDs(Constants.GET_CONCEPT_DESCRIPTION.getPath());
    }

    /**
     * Get the route to a concept description list with filled ids.
     *
     * @return THe route to a concept description list with filled ids
     */
    public String getConceptDescriptionListRouteWithId() {
        return this.replaceIDs(Constants.GET_CONCEPT_DESCRIPTION_LIST.getPath());
    }

    /**
     * Replace the id variables in a route.
     *
     * @param route The route with variables to replace
     * @return The route with replaced variables
     */
    public String replaceIDs(String route) {
        return route
            .replace("{aas.idShort}", this.aasIdShort)
            .replace("{submodel.idShort}", this.submodelIdShort)
            .replace("{element.idShort}", this.elementIdShort)
            .replace("{cd.idShort}", this.cdIdShort);
    }
}
