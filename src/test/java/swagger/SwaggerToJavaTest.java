package swagger;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import requests.RestService;
import swagger2java.api.AssetAdministrationShellApi;
import swagger2java.api.AssetApi;
import swagger2java.api.ConceptDescriptionApi;
import swagger2java.api.SubmodelApi;
import swagger2java.api.SubmodelelementApi;
import swagger2java.model.AasInput;
import swagger2java.model.ConceptDescriptionInput;
import swagger2java.model.SubmodelElementInput;
import swagger2java.model.SubmodelInput;
import templates.PutExample;
import utils.Constants;
import utils.Routes;

/**
 * Unit test to test the model generation.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class SwaggerToJavaTest {
    private final static AssetAdministrationShellApi AAS = new AssetAdministrationShellApi();
    private final static AssetApi ASSET = new AssetApi();
    private final static SubmodelApi SUBMODEL = new SubmodelApi();
    private final static SubmodelelementApi ELEMENT = new SubmodelelementApi();
    private final static ConceptDescriptionApi CD = new ConceptDescriptionApi();
    private final static RestService restService = new RestService();
    private final static Routes routes = new Routes(restService, "http://localhost:1111/", Constants.TEST_AAS_ID);
    private final static String[] models = new String[] {
            "GET_AAS", "GET_SUBMODEL_LIST", "PUT_AAS", "DELETE_AAS",
            "GET_ASSET", "GET_SUBMODEL", "GET_ELEMENT_LIST", "PUT_SUBMODEL",
            "DELETE_SUBMODEL", "GET_ELEMENT", "PUT_ELEMENT", "DELETE_ELEMENT",
            "GET_CD_LIST", "GET_CD", "PUT_CD", "DELETE_CD"
    };

    /**
     * Unit-Test if all endpoints are available.
     */
    @Test
    public void testGeneratedApi() {
        ArrayList<String> checkedModels = new ArrayList<String>();
        ArrayList<String> failedModels = new ArrayList<String>();
        try {
            AAS.aasAasIdShortGet(routes.getAASId());
            checkedModels.add("GET_AAS");
        } catch (Exception e) {
            failedModels.add("GET_AAS");
        }
        try {
            AAS.aasAasIdShortSubmodelsGet(routes.getAASId());
            checkedModels.add("GET_SUBMODEL_LIST");
        } catch (Exception e) {
            failedModels.add("GET_SUBMODEL_LIST");
        }
        try {
            AasInput aasInput = new AasInput();
            aasInput.idShort("_example");
            aasInput.identification(new PutExample("_example", "_example"));
            AAS.aasPut(aasInput);
            checkedModels.add("PUT_AAS");
            try {
                AAS.aasAasIdShortDelete("_example");
                checkedModels.add("DELETE_AAS");
            } catch (Exception e) {
                failedModels.add("DELETE_AAS");
            }
        } catch (Exception e) {
            failedModels.add("PUT_AAS");
        }
        try {
            ASSET.assetsAasIdShortGet(routes.getAASId());
            checkedModels.add("GET_ASSET");
        } catch (Exception e) {
            failedModels.add("GET_ASSET");
        }
        try {
            SUBMODEL.aasAasIdShortSubmodelsSubmodelIdShortGet(routes.getAASId(), routes.getSubmodelId());
            checkedModels.add("GET_SUBMODEL");
        } catch (Exception e) {
            failedModels.add("GET_SUBMODEL");
        }
        try {
            SUBMODEL.aasAasIdShortSubmodelsSubmodelIdShortTableGet(routes.getAASId(), routes.getSubmodelId());
            checkedModels.add("GET_ELEMENT_LIST");
        } catch (Exception e) {
            failedModels.add("GET_ELEMENT_LIST");
        }
        try {
            SubmodelInput submodelInput = new SubmodelInput();
            submodelInput.idShort("_example");
            submodelInput.identification(new PutExample("_example", "_example"));
            SUBMODEL.aasAasIdShortSubmodelsPut(submodelInput, routes.getAASId());
            checkedModels.add("PUT_SUBMODEL");
            try {
                SUBMODEL.aasAasIdShortSubmodelsSubmodelIdShortDelete(routes.getAASId(), "_example");
                checkedModels.add("DELETE_SUBMODEL");
            } catch (Exception e) {
                failedModels.add("DELETE_SUBMODEL");
            }
        } catch (Exception e) {
            failedModels.add("PUT_SUBMODEL");
        }
        try {
            ELEMENT.aasAasIdShortSubmodelsSubmodelIdShortElementsElementIdShortGet(routes.getAASId(),
                    routes.getSubmodelId(),routes.getElementId());
            checkedModels.add("GET_ELEMENT");
        } catch (Exception e) {
            failedModels.add("GET_ELEMENT");
        }
        try {
            SubmodelElementInput elementInput = new SubmodelElementInput();
            elementInput.idShort("_example");
            elementInput.modelType(new PutExample("Property"));
            ELEMENT.aasAasIdShortSubmodelsSubmodelIdShortElementsPut(elementInput, routes.getAASId(), routes.getSubmodelId());
            checkedModels.add("PUT_ELEMENT");
            try {
                ELEMENT.aasAasIdShortSubmodelsSubmodelIdShortElementsElementIdShortDelete(routes.getAASId(),
                        routes.getSubmodelId(), "_example");
                checkedModels.add("DELETE_ELEMENT");
            } catch (Exception e) {
                failedModels.add("DELETE_ELEMENT");
            }
        } catch (Exception e) {
            failedModels.add("PUT_ELEMENT");
        }
        try {
            CD.aasAasIdShortCdsGet(routes.getAASId());
            checkedModels.add("GET_CD_LIST");
        } catch (Exception e) {
            failedModels.add("GET_CD_LIST");
        }
        try {
            CD.aasAasIdShortCdsCdIdShortGet(routes.getAASId(), routes.getCdId());
            checkedModels.add("GET_CD");
        } catch (Exception e) {
            failedModels.add("GET_CD");
        }
        try {
            ConceptDescriptionInput cdInput = new ConceptDescriptionInput();
            cdInput.idShort("_example");
            cdInput.identification(new PutExample("_example", "_example"));
            CD.aasAasIdShortCdsPut(cdInput, routes.getAASId());
            checkedModels.add("PUT_CD");
            try {
                CD.aasAasIdShortCdsCdIdShortDelete(routes.getAASId(), "_example");
                checkedModels.add("DELETE_CD");
            } catch (Exception e) {
                failedModels.add("DELETE_CD");
            }
        } catch (Exception e) {
            failedModels.add("PUT_CD");
        }
        checkedModels.forEach(System.out::println);
        if (failedModels.size() > 0) {
            System.out.println("--------------------");
        }
        failedModels.forEach(System.err::println);
        for (String model : models) {
            assertTrue(checkedModels.contains(model));
        }
    }
}
