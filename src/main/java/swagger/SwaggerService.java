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
		this.routes = new Routes(schemes[0]+"://"+host, aasIdShort);
		this.schemes = schemes;
		this.host = host;
		this.basePath = basePath;
	}
	
	
	public void generateDocumentation() {
		Data.generateInfo(restService, routes);
		//TODO: generate tags
		for(String[] route : Routes.getRoutes()) {
			//TODO: generate paths
		}
		//TODO: generate definitions
		//TODO: generate externalDocs
	}
}
