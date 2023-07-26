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
import utils.Compare;
import utils.Constants;
import utils.Routes;

/**
 * Unit test to check the REST responses.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class CompareResponsesTest {
    private final static AssetAdministrationShellApi AAS = new AssetAdministrationShellApi();
    private final static AssetApi ASSET = new AssetApi();
    private final static SubmodelApi SUBMODEL = new SubmodelApi();
    private final static SubmodelelementApi ELEMENT = new SubmodelelementApi();
    private final static ConceptDescriptionApi CD = new ConceptDescriptionApi();
    private final static RestService restService = new RestService();
    private static Routes routes = new Routes(restService, "http://localhost:1111/", Constants.TEST_AAS_ID);
    private final static String[] models = new String[] {
            "GET_AAS", "GET_SUBMODEL_LIST", "GET_ASSET", "GET_SUBMODEL",
            "GET_ELEMENT_LIST", "GET_ELEMENT", "GET_CD_LIST", "GET_CD"
    };

    /**
     * Handle response comparison for multiple Asset Administration Shells.
     */
    @Test
    public void compareResponses() {
        /**
        * Version to test a single Asset Administration Shell.
        */
       compareSingleResponses(Constants.TEST_AAS_ID);
       /**
        * Version to test mutlitple Asset Administration Shells.
        */
        // for(String idShort: Constants.TEST_MULTIPLE_AAS_IDS) {
        //     compareSingleResponses(idShort);
        // }
    }

    /**
     * Compare whether the responses of the generated Java models correspond to
     * the responses of the REST requests.
     */
    public void compareSingleResponses(String idShort) {
        routes = new Routes(restService, "http://localhost:1111/", idShort);
        ArrayList<String> checkedModels = new ArrayList<String>();
        ArrayList<String> failedModels = new ArrayList<String>();
        try {
            if (Compare.compareAASResponse(AAS.aasAasIdShortGet(routes.getAASId()),
                    restService.httpGet(routes.getBaseUrl() + routes.getAASRouteWithId())[1])) {
                checkedModels.add("GET_AAS");
            } else {
                failedModels.add("GET_AAS");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareSubmodelListResponse(AAS.aasAasIdShortSubmodelsGet(routes.getAASId()),
                    restService.httpGet(routes.getBaseUrl() + routes.getSubmodelListRouteWithId())[1])) {
                checkedModels.add("GET_SUBMODEL_LIST");
            } else {
                failedModels.add("GET_SUBMODEL_LIST");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareAssetsResponse(ASSET.assetsAasIdShortGet(routes.getAASId()),
                    restService.httpGet(routes.getBaseUrl() + routes.getAssetRouteWithId())[1])) {
                checkedModels.add("GET_ASSET");
            } else {
                failedModels.add("GET_ASSET");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareSubmodelResponse(
                    SUBMODEL.aasAasIdShortSubmodelsSubmodelIdShortGet(routes.getAASId(), routes.getSubmodelId()),
                    restService.httpGet(routes.getBaseUrl() + routes.getSubmodelRouteWithId())[1])) {
                checkedModels.add("GET_SUBMODEL");
            } else {
                failedModels.add("GET_SUBMODEL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareElementListResponse(
                    SUBMODEL.aasAasIdShortSubmodelsSubmodelIdShortTableGet(routes.getAASId(), routes.getSubmodelId()),
                    restService.httpGet(routes.getBaseUrl() + routes.getElementListRouteWithId())[1])) {
                checkedModels.add("GET_ELEMENT_LIST");
            } else {
                failedModels.add("GET_ELEMENT_LIST");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareElementResponse(
                    ELEMENT.aasAasIdShortSubmodelsSubmodelIdShortElementsElementIdShortGet(routes.getAASId(), routes.getSubmodelId(),
                    routes.getElementId()), restService.httpGet(routes.getBaseUrl() + routes.getElementRouteWithId())[1])) {
                checkedModels.add("GET_ELEMENT");
            } else {
                failedModels.add("GET_ELEMENT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareCDListResponse(CD.aasAasIdShortCdsGet(routes.getAASId()), 
                    restService.httpGet(routes.getBaseUrl() + routes.getConceptDescriptionListRouteWithId())[1])) {
                checkedModels.add("GET_CD_LIST");
            } else {
                failedModels.add("GET_CD_LIST");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Compare.compareCDResponse(CD.aasAasIdShortCdsCdIdShortGet(routes.getAASId(), routes.getCdId()),
                    restService.httpGet(routes.getBaseUrl() + routes.getConceptDescriptionRouteWithId())[1])) {
                checkedModels.add("GET_CD");
            } else {
                failedModels.add("GET_CD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------------------\n" + idShort);
        System.out.println("--------------------");
        checkedModels.forEach(System.out::println);
        if (failedModels.size() > 0) {
            System.out.println("--------------------");
        }
        failedModels.forEach(System.err::println);
        System.out.println("\n");
        for (String model : models) {
            assertTrue(checkedModels.contains(model));
        }
    }
}
