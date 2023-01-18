package templates;

import utils.Transform;

/**
 *
 * Template for a tag instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Tag {
    private final String[] keys = new String[]
            {"name", "description", "externalDocs"};
    private String name;
    private String description;
    private ExternalDocs externalDocs;

    /**
     * Create a tag instance.
     *
     * @param name the name of the instance
     * @param description the description of the instance
     * @param externalDocs the external documents that belong to the tag
     */
    public Tag(
            String name,
            String description,
            ExternalDocs externalDocs) {
        this.name = name;
        this.description = description;
        this.externalDocs = externalDocs;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        String externalDocs;
        try {
            externalDocs = this.externalDocs.asJson();
        } catch (NullPointerException externalDocsNull) {
            externalDocs = null;
        }
        return new String[] {this.name, this.description, externalDocs};
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
