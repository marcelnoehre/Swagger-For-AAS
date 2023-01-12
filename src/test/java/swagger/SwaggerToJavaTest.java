package swagger;

import java.util.List;

import swagger2java.api.AssetAdministrationShellApi;
import swagger2java.model.AssetAdministrationShell;
import swagger2java.model.SubmodellistItem;

public class SwaggerToJavaTest {

	public static void main(String[] args) {
		AssetAdministrationShellApi aasApi = new AssetAdministrationShellApi();
		List<SubmodellistItem> submodelList
			= aasApi.aasAasIdShortSubmodelsGet("Festo_3S7PM0CP4BD");
		submodelList.forEach(System.out::print);
		AssetAdministrationShell aas
		= aasApi.aasAasIdShortInformationScopeGet("Festo_3S7PM0CP4BD", "");
	}
	
}
