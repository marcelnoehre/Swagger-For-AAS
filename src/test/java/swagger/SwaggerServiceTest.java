package swagger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Service to test the Swagger service.
 *
 * @author Marcel N&oumlhre
 *
 */
public class SwaggerServiceTest {
    private static final String[] SCHEMES = new String[] {"http"};
    private static final String HOST = "localhost:1111";
    private static final String BASEPATH = "";
    private static final String AAS_ID = "Festo_3S7PM0CP4BD";

    /**
     * Test to build a swagger documentation for a asset administration
     * shell.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SwaggerService swagger =
                new SwaggerService(SCHEMES, HOST, BASEPATH, AAS_ID);
        try {
            String[] swaggerStrings = swagger.generateDocumentation();
            File file = new File("./src/test/resources/aas.json");
            OutputStreamWriter out =  new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8");
            out.write(swaggerStrings[0]);
            out.close();
            file = new File("./src/test/resources/aas_gson.json");
            out =  new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            out.write(swaggerStrings[1]);
            out.close();
            System.out.println("Swagger generation completed!");
        } catch (IOException e) {
            System.err.println("Swagger generation failed!");
        }

    }
}
