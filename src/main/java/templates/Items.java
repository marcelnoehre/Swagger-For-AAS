package templates;

import utils.Transform;

/**
 *
 * Template for a items instance.
 *
 * @author Marcel N&ouml;hre
 *
 */
public class Items {
    private final String[] keys =
            new String[] {"type", "enum", "default", "$ref"};
    private String type;
    private String[] enums;
    private String defaultValue;
    private String $ref;

    /**
     * Create a items instance.
     *
     * @param type the type of the items
     * @param enums the possible values of an item
     * @param defaultValue the defalut value of an item
     * @param $ref the refernece to existing items
     */
    public Items(
            String type,
            String[] enums,
            String defaultValue,
            String $ref) {
        this.type = type;
        this.enums = enums;
        this.defaultValue = defaultValue;
        this.$ref = $ref;
    }

    /**
     * Getter for the reference to a items object.
     * 
     * @return the reference to a items object
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
        return new String[] {this.type,
                Transform.arrayToJson(this.enums),
                this.defaultValue, this.$ref};
    }

    /**
     * Get the instance as JSON string.
     *
     * @return json string of the instance
     */
    public String asJson() {
        return Transform.instanceToJson(
                this.keys,
                this.getValueArray());
    }
}
