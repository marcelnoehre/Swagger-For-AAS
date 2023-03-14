package templates;

import utils.Transform;

/**
 *
 * Template for a additional properties instance.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class AdditionalProperties {
    private final String[] keys = new String[] {"type", "format"};
    private String type;
    private String format;

    /**
     * Create a additional properties instance.
     *
     * @param type the type of the property
     * @param format the format associated with the type
     */
    public AdditionalProperties(String type, String format) {
        this.type = type;
        this.format = format;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        return new String[] {this.type, this.format};
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
