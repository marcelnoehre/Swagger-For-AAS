package swagger;

import java.util.List;

import swagger2java.api.AssetAdministrationShellApi;
import swagger2java.api.SubmodelApi;
import swagger2java.model.SubmodelListItem;

public class SwaggerToJavaTest {

	public static void main(String[] args) {
		AssetAdministrationShellApi aasApi = new AssetAdministrationShellApi();
		SubmodelApi submodelApi = new SubmodelApi();
		
		List<SubmodelListItem> submodelList
			= aasApi.aasAasIdShortSubmodelsGet("Festo_3S7PM0CP4BD");
		submodelList.forEach(System.out::print);
		System.out.println(submodelApi.aasAasIdShortSubmodelsSubmodelIdShortInformationScopeGet("Festo_3S7PM0CP4BD", "Nameplate", ""));
	}
	
}
