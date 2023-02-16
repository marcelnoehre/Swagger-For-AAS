package swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import requests.RestService;
import templates.Definition;
import templates.Property;
import templates.Request;
import templates.Response;
import templates.Route;
import templates.Schema;
import templates.ExternalDocs;
import templates.Info;
import templates.Items;
import templates.Parameter;
import templates.Path;
import templates.Tag;
import utils.Checks;
import utils.Constants;
import utils.Routes;

/**
 *
 * Service to fill templates with generated information.
 *
 * @author Marcel N&oumlhre
 *
 */
public class DataGenerationService {
    private static ArrayList<String> VALID_DEFINITION_NAMES = new ArrayList<>();
    /**
     * Fill the parameter template with information.
     *
     * @param route The route to generate the parameters for
     * @return The list of filled parameter templates
     */
    private static Parameter[] generateParameters(Route route) {
        ArrayList<Parameter> parameterList = new ArrayList<Parameter>();
        String[] possibleParameters = new String[] {"{aas.idShort}",
                "{submodel.idShort}", "{element.idShort}", "{cd.idShort}"};
        for (String possibleParameter : possibleParameters) {
            if (route.getPath().contains(possibleParameter)) {
                switch (possibleParameter.replace("{", "").replace("}", "")) {
                    case "aas.idShort":
                        parameterList.add(Constants.AAS_ID_SHORT);
                        break;
                    case "submodel.idShort":
                        parameterList.add(Constants.SUBMODEL_ID_SHORT);
                        break;
                    case "element.idShort":
                        parameterList.add(Constants.ELEMENT_ID_SHORT);
                        break;
                    case "cd.idShort":
                        parameterList.add(Constants.CD_ID_SHORT);
                        break;
                    default:
                        break;
                }
            }
        }
        if (route.getType().equals("put")) {
            Schema schema = null;
            switch (route.getTag()) {
            case "Asset Administration Shell":
                schema = Constants.AAS_INPUT_SCHEMA;
                break;
            case "Asset":
                schema = Constants.ASSET_INPUT_SCHEMA;      
                break;
            case "Submodel":
                schema = Constants.SUBMODEL_INPUT_SCHEMA;   
                break;
            case "Submodelelement":
                schema = Constants.ELEMENT_INPUT_SCHEMA;
                break;
            case "Concept Description":
                schema = Constants.CD_INPUT_SCHEMA;
                break;
            default:
                break;
            }
            if(!VALID_DEFINITION_NAMES.contains(schema.getRef())) {
                schema = null;
            }
            parameterList.add(
                    new Parameter(route.getTag(),
                            "body", "The new or updated "
                    + route.getTag(), "true", null, null,
                    null, null, null, null, schema));
        }
        Parameter[] parameters = new Parameter[parameterList.size()];
        int i = 0;
        for (Parameter parameter : parameterList) {
            parameters[i] = parameter;
            i++;
        }
        return parameters;
    }

    /**
     * Fill the response template with information.
     *
     * @param restService The service to handle REST requests
     * @param routes The service to handle routes
     * @param route The route to generate the responses for
     * @return The list of filled response templates
     */
    private static Response[] generateResponses(
            RestService restService,
            Routes routes,
            Route route) {
        String[] good = null;
        String[] bad = null;
        String[][] parameter = new String[][] {
            new String[] {"idShort", "example"},
            new String[] {"id", "example"}};
        String path = "";
        String pathPut = "";
        String goodExample = "{\"idShort\":\"example\",\"id\":\"example\"}";
        Schema schema = new Schema("string", null, null, null);
        switch (route.getType()) {
        case "get":
            switch (route.getTag()) {
            case "Asset Administration Shell":
                path = route.getPath().equals("/aas/{aas.idShort}")
                        ? Constants.EXAMPLE_AAS
                        : Constants.EXAMPLE_SUBMODEL_LIST;
                schema = route.getPath().equals("/aas/{aas.idShort}")
                        ? Constants.AAS_SCHEMA
                        : Constants.SUBMODEL_LIST_SCHEMA;
                break;
            case "Asset":
                path = Constants.EXAMPLE_ASSETS;
                schema = Constants.ASSETS_SCHEMA;
                break;
            case "Submodel":
                path = route.getPath().equals(
                        "/aas/{aas.idShort}/submodels/{submodel.idShort}")
                            ? Constants.EXAMPLE_SUBMODEL
                            : Constants.EXAMPLE_ELEMENT_LIST;
                schema = route.getPath().equals(
                        "/aas/{aas.idShort}/submodels/{submodel.idShort}")
                            ? Constants.SUBMODEL_SCHEMA
                            : Constants.ELEMENT_LIST_SCHEMA;
                break;
            case "Submodelelement":
                path = Constants.EXAMPLE_ELEMENT;
                schema = Constants.ELEMENT_SCHEMA;
                goodExample = "{\"idShort\":\"example\",\"modelType\""
                        + ":{\"name\":\"Property\"}}";
                parameter = new String[][] {new String[] {"idShort", "example"},
                    new String[] {"id", "example"}, new String[] {"modelType",
                            "{\"name\":\"Property\"}"}};
                break;
            case "Concept Description":
                path = route.getPath().equals("/aas/{aas.idShort}/cds")
                        ? Constants.EXAMPLE_CD_LIST : Constants.EXAMPLE_CD;
                schema = route.getPath().equals("/aas/{aas.idShort}/cds")
                        ? Constants.CD_LIST_SCHEMA : Constants.CD_SCHEMA;
                break;
            default:
                break;
            }
            good = restService.httpGet(routes.getBaseUrl()
                    + routes.replaceIDs(route.getPath()));
            bad = restService.httpGet(routes.getBaseUrl()
                    + routes.replaceIDs(path));
            if(!VALID_DEFINITION_NAMES.contains(schema.getRef())) {
                schema = null;
            }
            return new Response[] {
                    new Response(bad[0], bad[1], null, null),
                    new Response(good[0], "successful operation", schema, null)
            };
        case "delete":
            switch (route.getTag()) {
            case "Asset Administration Shell":
                path = Constants.EXAMPLE_AAS;
                break;
            case "Submodel":
                path = Constants.EXAMPLE_SUBMODEL;
                break;
            case "Submodelelement":
                path = Constants.EXAMPLE_ELEMENT;
                goodExample = "{\"idShort\":\"example\",\"modelType\""
                        + ":{\"name\":\"Property\"}}";
                parameter = new String[][] {new String[] {"idShort", "example"},
                    new String[] {"id", "example"}, new String[] {"modelType",
                            "{\"name\":\"Property\"}"}};
                break;
            case "Concept Description":
                path = Constants.EXAMPLE_CD;
                break;
            default:
                break;
            }
            restService.httpPut(routes.replaceIDs(path.replace("example", "")),
                    goodExample, parameter);
            good = restService.httpDelete(routes.getBaseUrl()
                    + routes.replaceIDs(path), goodExample);
            bad = restService.httpDelete(routes.getBaseUrl()
                    + routes.replaceIDs(path), goodExample);
            return new Response[] {
                    new Response(bad[0], bad[1], schema, null),
                    new Response(good[0], good[1], schema, null)
            };
        case "put":
            switch (route.getTag()) {
            case "Asset Administration Shell":
                path = routes.replaceIDs(Constants.EXAMPLE_AAS);
                pathPut = routes.replaceIDs(Constants.PUT_AAS.getPath());
                break;
            case "Asset":
                return Constants.PUT_ASSET_EXAMPLE_RESPONSE;
            case "Submodel":
                path = routes.replaceIDs(Constants.EXAMPLE_SUBMODEL);
                pathPut = routes.replaceIDs(Constants.PUT_SUBMODEL.getPath());
                break;
            case "Submodelelement":
                path = routes.replaceIDs(Constants.EXAMPLE_ELEMENT);
                pathPut = routes.replaceIDs(Constants.PUT_ELEMENT.getPath());
                goodExample = "{\"idShort\":\"example\",\"modelType\""
                        + ":{\"name\":\"Property\"}}";
                break;
            case "Concept Description":
                path = routes.replaceIDs(Constants.EXAMPLE_CD);
                pathPut = routes.replaceIDs(
                        Constants.PUT_CONCEPT_DESCRIPTION.getPath());
                break;
            default:
                break;
            }
            restService.httpDelete(routes.getBaseUrl() + path, goodExample);
            good = restService.httpPut(pathPut, goodExample, parameter);
            bad = restService.httpPut(pathPut, "example", new String[][] {});
            restService.httpDelete(routes.getBaseUrl() + path, goodExample);
            return new Response[] {
                    new Response(bad[0], bad[1], schema, null),
                    new Response(good[0], good[1], schema, null)
            };
        default:
            break;
        }
        return null;
    }

    /**
     * Fill the info template with information.
     *
     * @param restService The service to handle REST requests
     * @param routes The service to handle routes
     * @return The filled info template
     */
    public static Info generateInfo(
            RestService restService,
            Routes routes) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl() + routes.getAASRouteWithId())[1]);
            String description = "";
            String title = "Asset Adminstration Shell: " + routes.getAASId();
            try {
                JSONArray descriptions = (JSONArray) ((JSONObject) parser.parse(
                        json.get("Asset").toString())).get("descriptions");
                int i = 0;
                for (Object descriptionObj : descriptions) {
                    JSONObject descriptionValue = (JSONObject) descriptionObj;
                    if (i == 0
                            || descriptionValue.get("language").equals("EN")) {
                        description =
                                "REST server for access to the "
                                + "Asset Administration Shell: "
                                + routes.getAASId()
                                + ". The AAS belongs to the asset: "
                                + descriptionValue.get("text");
                    }
                    i++;
                }
            } catch (NullPointerException desc) {
                description =
                        "REST server for access to the "
                        + "Asset Administration Shell: "
                        + routes.getAASId();
            }
            return new Info(description, "1.0", title, null, null, null);
        } catch (ParseException parse) {
            return null;
        }
    }

    /**
     * Fill the tag template with information.
     *
     * @return The list of filled tag templates
     */
    public static Tag[] generateTags() {
        String[] tagNames = new String[] {
                "Asset Administration Shell", "Asset",
                "Submodel", "Submodelelement", "Concept Description"};
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        for (String tag : tagNames) {
            tagList.add(new Tag(tag, "Operations that belong to "
        + tag + "s", null));
        }
        Tag[] tags = new Tag[tagList.size()];
        int i = 0;
        for (Tag tag : tagList) {
            tags[i] = tag;
            i++;
        }
        return tags;
    }

    /**
     * Fill the Path template with information.
     *
     * @param restService The service to handle REST requests
     * @param routes The service to handle routes
     * @return The list of filled path templates
     */
    public static Path[] generatePaths(
            RestService restService,
            Routes routes) {
        ArrayList<Path> pathList = new ArrayList<Path>();
        for (Route[] routeList : Routes.getMultiRoutes()) {
            Request[] requestList = new Request[routeList.length];
            int j = 0;
            for (Route route : routeList) {
                if(!Routes.getInvalidRoutes().contains(route.getPath())) {
                    String[] consumes;
                    String[] produces;
                    if (route.getType().equals("get")) {
                        consumes = new String[] {};
                        produces = new String[] {"application/json"};
                    } else {
                        consumes = new String[] {"application/json"};
                        produces = new String[] {"text/plain"};
                    }
                    requestList[j] = new Request(route.getType(),
                            new String[] {route.getTag()},
                            route.getSummary(), "", null,
                            consumes, produces, generateParameters(route),
                            generateResponses(restService, routes, route),
                            "false");    
                    j++;
                }
            }
            if(requestList.length > 0) {
                pathList.add(new Path(routeList[0].getPath(), requestList));
            }
        }
        for (Route route : Routes.getRoutes()) {
            if(!Routes.getInvalidRoutes().contains(route.getPath())) {
                String[] consumes;
                String[] produces;
                if (route.getType().equals("get")) {
                    consumes = new String[] {};
                    produces = new String[] {"application/json"};
                } else {
                    consumes = new String[] {"application/json"};
                    produces = new String[] {"text/plain"};
                }
                Request request = new Request(route.getType(),
                        new String[] {route.getTag()},
                        route.getSummary(), "", null,
                        consumes, produces, generateParameters(route),
                        generateResponses(restService, routes, route), "false");
                pathList.add(new Path(route.getPath(), new Request[] {request}));
            }
        }
        int i = 0;
        Path[] paths = new Path[pathList.size()];
        for(Path path : pathList) {
            try {
                path.asJson();
                paths[i] = path;
                i++;
            } catch(Exception e) {
                paths = Arrays.copyOf(paths, paths.length-1);
            }
            
        }
        return paths;
    }

    /**
     * Fill the definition template with information.
     *
     * @param restService The service to handle REST requests
     * @param routes The service to handle routes
     * @return The list of filled definition templates
     */
    @SuppressWarnings("rawtypes")
    public static Definition[] generateDefinitions(
            RestService restService,
            Routes routes) {
        JSONParser parser = new JSONParser();
        Property[] apiResponseProperties = new Property[] {
                new Property("resultCode", "integer", "int32",
                        null, null, "200", null, null, null),
                new Property("type", "string", null, null, null,
                        "application/json", null, null, null),
                new Property("message", "string", null, null, null,
                        "OK (updated)", null, null, null)
        };
        ArrayList<JSONObject> validDefinitions = new ArrayList<JSONObject>();
        try {
            JSONObject aasResponse =
                    (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl() + routes.getAASRouteWithId())[1]);
            validDefinitions.add(aasResponse);
            VALID_DEFINITION_NAMES.add("AssetAdministrationShell");
            try {
                JSONObject aasInput = (JSONObject) aasResponse.get("AAS");
                validDefinitions.add(aasInput);
                VALID_DEFINITION_NAMES.add("AasInput");
            } catch(Exception e) { }
            try {
                JSONObject assetInput = (JSONObject) aasResponse.get("Asset");
                validDefinitions.add(assetInput);
                VALID_DEFINITION_NAMES.add("AssetInput");
            } catch(Exception e) { }
        } catch(Exception e) { }
        try {
            JSONArray assetList = (JSONArray)
                    parser.parse(restService.httpGet(routes.getBaseUrl()
                            + routes.getAssetRouteWithId())[1]);
            JSONObject assetListItem = null;
            for (Object assetListObject : assetList) {
                try {
                    assetListItem = (JSONObject) assetListObject;
                    validDefinitions.add(assetListItem);
                    VALID_DEFINITION_NAMES.add("Asset");
                    break;
                } catch (NullPointerException assetListException) { }
            }
        } catch(Exception e) { }            
        try {
            JSONArray submodelList = (JSONArray)
                    parser.parse(restService.httpGet(routes.getBaseUrl()
                            + routes.getSubmodelListRouteWithId())[1]);
            JSONObject submodelListItem = null;
            for (Object submodelListObject : submodelList) {
                try {
                    submodelListItem = (JSONObject) submodelListObject;
                    validDefinitions.add(submodelListItem);
                    VALID_DEFINITION_NAMES.add("SubmodelListItem");
                    break;
                } catch (NullPointerException submodelListException) { }
            }    
        } catch(Exception e) { }
        try {
            JSONObject submodel = (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl()
                    + routes.getSubmodelRouteWithId())[1]);
            validDefinitions.add(submodel);
            VALID_DEFINITION_NAMES.add("Submodel");
        } catch(Exception e) { }
        try {
            JSONArray elementList = (JSONArray)
                    parser.parse(restService.httpGet(routes.getBaseUrl()
                            + routes.getElementListRouteWithId())[1]);
            JSONObject elementListItem = null;
            for (Object elementListObject : elementList) {
                try {
                    elementListItem = (JSONObject) elementListObject;
                    validDefinitions.add(elementListItem);
                    VALID_DEFINITION_NAMES.add("SubmodelelementListItem");
                    break;
                } catch (NullPointerException elementListException) { }
            }
        } catch(Exception e) { }
        try {
            JSONObject element = (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl()
                    + routes.getElementRouteWithId())[1]);
            validDefinitions.add(element);
            VALID_DEFINITION_NAMES.add("SubmodelElement");
        } catch(Exception e) { }
        try {
            JSONArray cdList = (JSONArray)
                    parser.parse(restService.httpGet(routes.getBaseUrl()
                            + routes.
                            getConceptDescriptionListRouteWithId())[1]);
            JSONObject cdListItem = null;
            for (Object cdListObject : cdList) {
                try {
                    cdListItem = (JSONObject) cdListObject;
                    validDefinitions.add(cdListItem);
                    VALID_DEFINITION_NAMES.add("ConceptDescriptionListItem");
                    break;
                } catch (NullPointerException cdListException) { }
            }
        } catch(Exception e) { }
        try {
            JSONObject cd = (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl()
                    + routes.getConceptDescriptionRouteWithId())[1]);
            validDefinitions.add(cd);
            VALID_DEFINITION_NAMES.add("ConceptDescription");
        } catch(Exception e) { }
        try {
            JSONObject submodelInput =
                    (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl() + routes.getSubmodelRouteWithId())[1]);
            validDefinitions.add(submodelInput);
            VALID_DEFINITION_NAMES.add("SubmodelInput");
        } catch(Exception e) { }
        try {
            JSONObject elementInput = (JSONObject) ((JSONObject) parser.parse(
                    restService.httpGet(routes.getBaseUrl()
                            + routes.getElementRouteWithId())[1])).get("elem");
            validDefinitions.add(elementInput);
            VALID_DEFINITION_NAMES.add("SubmodelElementInput");
        } catch(Exception e) { }
        
        try {
            JSONObject cdInput = (JSONObject) parser.parse(restService.httpGet(
                    routes.getBaseUrl()
                    + routes.getConceptDescriptionRouteWithId())[1]);
            validDefinitions.add(cdInput);
            VALID_DEFINITION_NAMES.add("ConceptDescriptionInput");
        } catch(Exception e) { }
        String[] definitionNames = new String[VALID_DEFINITION_NAMES.size()];
        int i = 0;
        for(String definitionName : VALID_DEFINITION_NAMES) {
            definitionNames[i] = definitionName;
            i++;
        }
        JSONObject[] definitionExamples = new JSONObject[validDefinitions.size()];
        i = 0;
        for(JSONObject definition : validDefinitions) {
            definitionExamples[i] = definition;
            i++;
        }
        Definition[] definitions = new Definition[validDefinitions.size()+1];
        definitions[0] = new Definition("ApiResponse", "object",
                new String[] {"resultCode", "type", "message"},
                apiResponseProperties);
        for (i = 0; i < definitionExamples.length; i++) {
            Property[] properties;
            if (definitionExamples[i].size() > 0) {
                properties = new Property[definitionExamples[i].size()];
                int j = 0;
                for (Iterator iterator =
                        definitionExamples[i].keySet().iterator();
                        iterator.hasNext();) {
                    String key = (String) iterator.next();
                    String value;
                    String type;
                    String format;
                    Items items = null;
                    if (key.equals("isCaseOf")) {
                        properties[j]
                                = Constants.EXAMPLE_IS_CASE_OF_PROPERTY;
                    } else {
                        try {
                            value = (String) definitionExamples[i]
                                    .get(key).toString();
                            type = Checks.variableType(value);
                            format = type.equals("integer")
                                    ? "int64" : null;
                        } catch (NullPointerException nullPointer) {
                            value = null;
                            type = "object";
                            format = null;
                        }
                        if (type.equals("array")) {
                            items = new Items("object", null, null, null);
                            for (Object element
                                    : new org.json.JSONArray(value)) {
                                items = new Items(Checks.variableType(
                                        element.toString()),
                                        null, null, null);
                                break;
                            }
                        }
                        properties[j] = new Property(key, type, format,
                                null, null, value, null, items, null);
                    }
                    j++;
                }
            } else {
                properties = null;
            }
            definitions[i + 1] = new Definition(definitionNames[i],
                    "object", new String[]{"idShort"}, properties);
        }
        return definitions;
    }

    /**
     * Fill the external documnets template with information.
     *
     * @param restService The service to handle REST requests
     * @param routes The service to handle routes
     * @return The filled external documents template
     */
    public static ExternalDocs generateExternalDocs(
            RestService restService,
            Routes routes) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(
                    restService.httpGet(routes.getBaseUrl()
                            + routes.getAASRouteWithId())[1]);
            String description = "";
            String url = "";
            try {
                JSONObject identification =
                        (JSONObject) ((JSONObject) parser.parse(
                                json.get("Asset").toString()))
                        .get("identification");
                if (identification.get("type").toString().equals("IRI")) {
                    url = identification.get("id").toString();
                }
                try {
                    JSONArray descriptions = (JSONArray) ((JSONObject)
                            parser.parse(json.get("Asset").toString()))
                            .get("descriptions");
                    int i = 0;
                    for (Object descriptionObj : descriptions) {
                        JSONObject descriptionValue =
                                (JSONObject) descriptionObj;
                        if (i == 0 || descriptionValue
                                .get("language").equals("EN")) {
                            description = "More information about "
                                + descriptionValue.get("text");
                        }
                        i++;
                    }
                } catch (NullPointerException desc) {
                    description = "More information about " + routes.getAASId();
                }
                return new ExternalDocs(description, url);
            } catch (NullPointerException urlException) {
                return null;
            }
        } catch (ParseException parse) {
            return null;
        }
    }
}
