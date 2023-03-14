package templates;

import org.json.simple.JSONObject;

import utils.Checks;
import utils.Transform;

/**
 *
 * Template for a header instance.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class Header {
    private final String[] keys =
            new String[] {"type", "format", "description"};
    private String id;
    private String type;
    private String format;
    private String description;

    /**
     * Create a header instance.
     *
     * @param id the unique header id
     * @param type the type of the header
     * @param format the format associated with the type
     * @param description the description of the header
     */
    public Header(
            String id,
            String type,
            String format,
            String description) {
        this.id = id;
        this.type = type;
        this.format = format;
        this.description = description;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        return new String[] {this.type, this.format, this.description};
    }

    /**
     * Get the instance as JSON string.
     *
     * @return json string of the instance
     */
    @SuppressWarnings("unchecked")
    public String asJson() {
        String header = Transform.instanceToJson(keys, this.getValueArray());
        if (Checks.valueIsEmpty(header)) {
            return null;
        } else {
            JSONObject container = new JSONObject();
            container.put(id, header);
            return container.toJSONString();
        }
    }
}
