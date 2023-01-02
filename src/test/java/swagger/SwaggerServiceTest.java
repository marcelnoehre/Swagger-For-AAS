package swagger;

public class SwaggerServiceTest {	
	private static final String[] SCHEMES = new String[] {"http"};
	private static final String HOST = "localhost:1111";
	private static final String BASEPATH = "";
	private static final String AAS_ID = "Festo_3S7PM0CP4BD";
	
	public static void main(String args[]) {
		SwaggerService swagger = new SwaggerService(SCHEMES, HOST, BASEPATH, AAS_ID);
		System.out.println(swagger.generateDocumentation());
	}
}
