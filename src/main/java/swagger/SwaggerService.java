package swagger;

import requests.RestService;

public class SwaggerService {	
	private String[] schemes;
	private String host;
	private String basePath;
	private String baseUrl;
	
	public SwaggerService(String[] schemes, String host, String basePath) {
		this.schemes = schemes;
		this.host = host;
		this.basePath = basePath;
		this.baseUrl = schemes[0] + "://" + host;
	}
	
	
	public void generateDocumentation() {
		RestService rest = new RestService();
		rest.httpGet(this.baseUrl + "/aas/ExampleMotor");
	}
}
