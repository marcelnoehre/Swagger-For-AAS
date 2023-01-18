package templates;

import utils.Transform;

/**
 *
 * Template for a external docs instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class ExternalDocs {
    private final String[] keys = new String[] {"description", "url"};
    private String description;
    private String url;

    /**
     * Create a external docs properties instance.
     *
     * @param description the description of the external documents
     * @param url the url to the external documents
     */
    public ExternalDocs(String description, String url) {
        this.description = description;
        this.url = url;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        return new String[] {this.description, this.url};
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
