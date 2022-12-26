package swagger;

public class SwaggerServiceTest {	
	private static final String[] SCHEMES = new String[] {"http"};
	private static final String HOST = "localhost:1111";
	private static final String BASEPATH = "/aas";
	
	
	public static void main(String args[]) {
		SwaggerService swagger = new SwaggerService(SCHEMES, HOST, BASEPATH);
		swagger.generateDocumentation();
	}
}
