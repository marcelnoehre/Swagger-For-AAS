package templates;

import utils.Transform;

/**
 *
 * Template for a security definition instance.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class SecurityDefinition {
    private String[] keys = new String[] {"type", "name", "in", "authorizationUrl", "flow", "scopes"};
    private String id;
    private String type;
    private String name;
    private String in;
    private String authorizationUrl;
    private String flow;
    private Scope[] scopes;

    /**
     * Create a security definition instance.
     *
     * @param id the unique id of the security definition
     * @param type the type of the security definition
     * @param name the name of the security definition
     * @param in the input variable
     * @param authorizationUrl The url to the authentication
     * @param flow the flow of the security definition
     * @param scopes the list of scopes of the security definition
     */
    public SecurityDefinition(String id, String type, String name, String in, String authorizationUrl, String flow, Scope[] scopes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.in = in;
        this.authorizationUrl = authorizationUrl;
        this.flow = flow;
        this.scopes = scopes;
    }

    /**
     * Get the unique id of the security definition.
     *
     * @return the unique id of the security definition
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        return new String[] {this.type, this.name,
                this.in, this.authorizationUrl,
                this.flow, Transform.scopesToJson(scopes)};
    }

    /**
     * Get the instance as JSON string.
     *
     * @return json string of the instance
     */
    public String asJson() {
        return Transform.instanceToJson(keys, this.getValueArray());
    }
}
