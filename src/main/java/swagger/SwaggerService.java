package swagger;

import requests.RestService;
import templates.Definition;
import templates.ExternalDocs;
import templates.GsonSwagger;
import templates.Info;
import templates.Path;
import templates.Swagger;
import templates.Tag;
import utils.Routes;
import utils.Transform;

/**
 *
 * Service to generate a swagger documentation for a specific aas.
 *
 * @author Marcel N&oumlhre
 *
 */
public class SwaggerService {
    private RestService restService;
    private Routes routes;
    private String[] schemes;
    private String host;
    private String basePath;

    /**
     * Service to generate a swagger documentation for a asset
     * administration shell.
     *
     * @param schemes The possible schemes in the url
     * @param host The hostname
     * @param basePath The basepath of all requests
     * @param aasIdShort The id to get the aas
     */
    public SwaggerService(
            String[] schemes,
            String host,
            String basePath,
            String aasIdShort) {
        this.restService = new RestService();
        this.routes = new Routes(
                restService, schemes[0] + "://" + host + basePath,
                aasIdShort);
        this.schemes = schemes;
        this.host = host;
        this.basePath = basePath;
    }

    /**
     * Fill templates to generate the swagger documentation.
     *
     * @return The Swagger documentation as JSON String
     */
    public String[] generateDocumentation() {
        Info info = DataGenerationService
                .generateInfo(restService, routes);
        Tag[] tags = DataGenerationService.generateTags();
        Path[] paths = DataGenerationService
                .generatePaths(restService, routes);
        Definition[] definitions = DataGenerationService
                .generateDefinitions(restService, routes);
        ExternalDocs externalDocs = DataGenerationService
                .generateExternalDocs(restService, routes);
        Swagger swagger = new Swagger(
                "2.0", info, host, basePath, tags, schemes,
                paths, null, definitions, externalDocs);
        GsonSwagger gsonSwagger = new GsonSwagger(
                "2.0", info, host, basePath, tags, schemes,
                paths, null, definitions, externalDocs);
        return new String[] {
                Transform.adjustJson(swagger.asJson()),
                Transform.adjustJson(gsonSwagger.asJson())
        };
    }
}
