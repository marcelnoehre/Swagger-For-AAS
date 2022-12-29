package swagger;

import requests.RestService;
import utils.Routes;

public class SwaggerService {
	private RestService restService;
	private Routes routes;
	private String[] schemes;
	private String host;
	private String basePath;
	
	public SwaggerService(String[] schemes, String host, String basePath, String aasIdShort) {
		this.restService = new RestService();
		this.routes = new Routes(restService, schemes[0]+"://"+host, aasIdShort);
		this.schemes = schemes;
		this.host = host;
		this.basePath = basePath;
	}
	
	
	public void generateDocumentation() {
		Data.generateInfo(restService, routes);
		Data.generateTags();
		Data.generatePaths(restService, routes);
		Data.generateDefinitions(restService, routes);
		Data.generateExternalDocs(restService, routes);
	}
}
