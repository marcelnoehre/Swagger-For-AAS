package swagger;

import static org.junit.Assert.assertTrue;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import utils.Constants;

/**
 * Unit test to test the Swagger-Service.
 *
 * @author Marcel N&oumlhre
 *
 */
public class SwaggerServiceTest {
    private static final String[] SCHEMES = new String[] {"http"};
    private static final String HOST = "localhost:1111";
    private static final String BASEPATH = "";

    /**
     * Test if the Swagger-Service generates valid JSON Strings.
     */
    @Test
    public void testSwaggerService() {
        SwaggerService swagger =
                new SwaggerService(SCHEMES, HOST, BASEPATH,
                        Constants.TEST_AAS_ID);
        JSONParser jsonParser = new JSONParser();
        int counter = 0;
        for (String swaggerString : swagger.generateDocumentation()) {
            try {
                jsonParser.parse(swaggerString);
                counter++;
            } catch (ParseException e) { }
        }
        assertTrue(counter == 2);
    }
}
