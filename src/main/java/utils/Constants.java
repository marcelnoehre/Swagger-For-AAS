package utils;

import templates.Items;
import templates.Parameter;
import templates.Property;
import templates.Response;
import templates.Route;
import templates.Schema;

/**
 *
 * Collection of constants.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Constants {
    /*
     * AAS idShort to test the functionalities.
     */
    public static final String TEST_AAS_ID = "ExampleMotor";
//     public static final String TEST_AAS_ID = "Bosch_NexoPistolGripNutrunner";
//     public static final String TEST_AAS_ID = "Festo_3S7PM0CP4BD";
//     public static final String TEST_AAS_ID = "Lenze_i950";
    
    /**
     * Template for a http get request to get a AAS.
     */
    public static final Route GET_AAS = new Route(
            "Asset Administration Shell",
            "Information about the Asset Administration Shell",
            "get", "/aas/{aas.idShort}");

    /**
     * Template for a http get request to get the submodel list.
     */
    public static final Route GET_SUBMODEL_LIST = new Route(
            "Asset Administration Shell",
            "A list of all submodels of an AAS", "get",
            "/aas/{aas.idShort}/submodels");

    /**
     * Template for a http put request to add/update a AAS.
     */
    public static final Route PUT_AAS = new Route(
            "Asset Administration Shell",
            "Add or update a Asset Administration Shell",
            "put", "/aas/");

    /**
     * Template for a http delete request to delete a AAS.
     */
    public static final Route DELETE_AAS = new Route(
            "Asset Administration Shell",
            "Delete a Asset Adminstration Shell",
            "delete", "/aas/{aas.idShort}");

    /**
     * Template for a http get request to get a asset.
     */
    public static final Route GET_ASSETS = new Route(
            "Asset", "Information about the Asset", "get",
            "/assets/{aas.idShort}");

    /**
     * Template for a http put request to add/update a asset.
     */
    public static final Route PUT_ASSETS = new Route(
            "Asset", "Add or update a Asset", "put", "/assets/");

    /**
     * Template for a http get request to get a submodel.
     */
    public static final Route GET_SUBMODEL = new Route(
            "Submodel", "Information about the submodel",
            "get", "/aas/{aas.idShort}/submodels/{submodel.idShort}");

    /**
     * Template for a http get request to get the element list.
     */
    public static final Route GET_ELEMENT_LIST = new Route(
            "Submodel", "A list of all elements of an submodel", "get",
            "/aas/{aas.idShort}/submodels/{submodel.idShort}/table");

    /**
     * Template for a http put request to add/update a submodel.
     */
    public static final Route PUT_SUBMODEL = new Route(
            "Submodel", "Add or update asubmodel", "put",
            "/aas/{aas.idShort}/submodels/");

    /**
     * Template for a http delete request to delete a submodel.
     */
    public static final Route DELETE_SUBMODEL = new Route(
            "Submodel", "Delete a submodel", "delete",
            "/aas/{aas.idShort}/submodels/{submodel.idShort}");

    /**
     * Template for a http get request to get a element.
     */
    public static final Route GET_ELEMENT = new Route(
            "Submodelelement", "Information about the submodelelement", "get",
            "/aas/{aas.idShort}/submodels/{submodel.idShort}"
            + "/elements/{element.idShort}");

    /**
     * Template for a http put request to add/update a element.
     */
    public static final Route PUT_ELEMENT = new Route(
            "Submodelelement", "Add or update a submodelelement", "put",
            "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/");

    /**
     * Template for a http delete request to delete a element.
     */
    public static final Route DELETE_ELEMENT = new Route(
            "Submodelelement", "Delete a submodelelement", "delete",
            "/aas/{aas.idShort}/submodels/{submodel.idShort}"
            + "/elements/{element.idShort}");

    /**
     * Template for a http get request to get a concept description.
     */
    public static final Route GET_CONCEPT_DESCRIPTION_LIST = new Route(
            "Concept Description",
            "A list of all concept descriptions of an AAS", "get",
            "/aas/{aas.idShort}/cds");

    /**
     * Template for a http get request to get a concept description.
     */
    public static final Route GET_CONCEPT_DESCRIPTION = new Route(
            "Concept Description", "Information about a concept description",
            "get", "/aas/{aas.idShort}/cds/{cd.idShort}");

    /**
     * Template for a http put request to add/update a concept description.
     */
    public static final Route PUT_CONCEPT_DESCRIPTION = new Route(
            "Concept Description", "Add or update a concept description", "put",
            "/aas/{aas.idShort}/cds/");

    /**
     * Template for a http delete request to delete a concept description.
     */
    public static final Route DELETE_CONCEPT_DESCRIPTION = new Route(
            "Concept Description", "Delete a concept description", "delete",
            "/aas/{aas.idShort}/cds/{cd.idShort}");

    /**
     * Route for a example AAS.
     */
    public static final String EXAMPLE_AAS = "/aas/example";

    /**
     * Route for a example submodel list.
     */
    public static final String EXAMPLE_SUBMODEL_LIST =
            "/aas/example/submodels/";

    /**
     * Route for a example asset.
     */
    public static final String EXAMPLE_ASSETS = "/assets/example";

    /**
     * Route for a example submodel.
     */
    public static final String EXAMPLE_SUBMODEL =
            "/aas/{aas.idShort}/submodels/example";

    /**
     * Route for a example element list.
     */
    public static final String EXAMPLE_ELEMENT_LIST =
            "/aas/{aas.idShort}/submodels/example/table";

    /**
     * Route for a example element.
     */
    public static final String EXAMPLE_ELEMENT =
            "/aas/{aas.idShort}/submodels/{submodel.idShort}/elements/example";

    /**
     * Route for a example concept description list.
     */
    public static final String EXAMPLE_CD_LIST = "/aas/example/cds";

    /**
     * Route for a example concept description.
     */
    public static final String EXAMPLE_CD = "/aas/{aas.idShort}/cds/example";

    /**
     * Parameter definition of the AAS short id.
     */
    public static final Parameter AAS_ID_SHORT = new Parameter(
            "aas.idShort", "path", "The unique ID of an AAS", "true", "string",
            null, null, null, null, null, null);

    /**
     * Parameter definition of the submodel short id.
     */
    public static final Parameter SUBMODEL_ID_SHORT = new Parameter(
            "submodel.idShort", "path", "The unique ID of an Submodel", "true",
            "string", null, null, null, null, null, null);

    /**
     * Parameter definition of the element short id.
     */
    public static final Parameter ELEMENT_ID_SHORT = new Parameter(
            "element.idShort", "path", "The unique ID of an Element", "true",
            "string", null, null, null, null, null, null);

    /**
     * Parameter definition of the concept description short id.
     */
    public static final Parameter CD_ID_SHORT = new Parameter(
            "cd.idShort", "path", "The unique ID of a CD", "true", "string",
            null, null, null, null, null, null);

    /**
     * Schema definition for a api response.
     */
    public static final Schema API_RESPONSE = new Schema(
            null, null, null, "#/definitions/ApiResponse");

    /**
     * Schema definition for a aas.
     */
    public static final Schema AAS_SCHEMA = new Schema(
            null, null, null, "#/definitions/AssetAdministrationShell");

    /**
     * Schema definition for a asset.
     */
    public static final Schema ASSETS_SCHEMA = new Schema(
            "array", new Items(null, null, null,
                "#/definitions/Asset"), null, null);

    /**
     * Schema definition for a item in a submodellist.
     */
    public static final Schema SUBMODEL_LIST_SCHEMA = new Schema(
            "array", new Items(null, null, null,
                    "#/definitions/SubmodelListItem"), null, null);

    /**
     * Schema definition for a submodel.
     */
    public static final Schema SUBMODEL_SCHEMA = new Schema(
            null, null, null, "#/definitions/Submodel");

    /**
     * Schema definition for a item in a submodelelementlist.
     */
    public static final Schema ELEMENT_LIST_SCHEMA = new Schema(
            "array", new Items(null, null, null,
                    "#/definitions/SubmodelelementListItem"), null, null);

    /**
     * Schema definition for a submodelelement.
     */
    public static final Schema ELEMENT_SCHEMA = new Schema(
            null, null, null, "#/definitions/SubmodelElement");

    /**
     * Schema definition for a concept description.
     */
    public static final Schema CD_SCHEMA = new Schema(
            null, null, null, "#/definitions/ConceptDescription");

    /**
     * Schema definition for a concept description list.
     */
    public static final Schema CD_LIST_SCHEMA = new Schema(
                "array", new Items(null, null, null,
                        "#/definitions/ConceptDescriptionListItem"),
                null, null);

    /**
     * Schema definition for a aas input.
     */
    public static final Schema AAS_INPUT_SCHEMA = new Schema(
            null, null, null, "#/definitions/AasInput");

    /**
     * Schema definition for a asset input.
     */
    public static final Schema ASSET_INPUT_SCHEMA = new Schema(
            null, null, null, "#/definitions/AssetInput");

    /**
     * Schema definition for a submodel input.
     */
    public static final Schema SUBMODEL_INPUT_SCHEMA = new Schema(
            null, null, null, "#/definitions/SubmodelInput");

    /**
     * Schema definition for a submodel element input.
     */
    public static final Schema ELEMENT_INPUT_SCHEMA = new Schema(
            null, null, null, "#/definitions/SubmodelElementInput");

    /**
     * Schema definition for a submodel element input.
     */
    public static final Schema CD_INPUT_SCHEMA = new Schema(
            null, null, null, "#/definitions/ConceptDescriptionInput");

    /**
     * Example response for a http put request to add/update a asset.
     */
    public static final Response[] PUT_ASSET_EXAMPLE_RESPONSE = new Response[] {
            new Response("200", "OK (updated)", null, null),
            new Response("400", "No payload or content type is not JSON.",
                    null, null)
    };
    
    public static final Property EXAMPLE_IS_CASE_OF_PROPERTY = new Property(
            "isCaseOf", "array", null, null, null, null, null, new Items(
            "object",null, null, null), null);
}
