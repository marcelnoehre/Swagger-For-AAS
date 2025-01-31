package templates;

import utils.Transform;

/**
 *
 * Template for a schema instance.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class Schema {
    private final String[] keys = new String[] {"type", "items", "additionalProperties", "$ref"};
    private String type;
    private Items items;
    private AdditionalProperties additionalProperties;
    private String $ref;

    /**
     * Create a schema instance.
     *
     * @param type the type of the schema
     * @param items the items that belong to the schema
     * @param additionalProperties the additional properties
     * @param $ref the reference to a existing schema
     */
    public Schema(String type, Items items, AdditionalProperties additionalProperties, String $ref) {
        this.type = type;
        this.items = items;
        this.additionalProperties = additionalProperties;
        this.$ref = $ref;
    }
    
    /**
     * Getter for the items of a definition.
     * 
     * @return The items of a definition
     */
    public Items getItems() {
        return this.items;
    }
    
    /**
     * Getter for the reference to a definition.
     * 
     * @return the reference to a definition
     */
    public String getRef() {
        return this.$ref;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    private String[] getValueArray() {
        String items;
        try {
            items = this.items.asJson();
        } catch (NullPointerException itemsNull) {
            items = null;
        }
        String additionalProperties;
        try {
            additionalProperties = this.additionalProperties.asJson();
        } catch (NullPointerException additionalPropertiesNull) {
            additionalProperties = null;
        }
        return new String[] {this.type, items, additionalProperties, this.$ref};
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
