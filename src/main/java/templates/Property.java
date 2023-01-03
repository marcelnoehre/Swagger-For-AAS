package templates;

import utils.Transform;

/**
 *
 * Template for a property instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Property {
	private final String[] keys = new String[] {
	        "type",
	        "format",
	        "description",
	        "enum",
	        "example",
	        "example",
	        "items",
	        "$ref"};
	private String id;
	private String type;
	private String format;
	private String description;
	private String[] enums;
	private String example;
	private String[] exampleArray;
	private Items items;
	private String $ref;

	/**
	 * Create a property instance.
	 *
	 * @param id the unique id of the property
	 * @param type the type of the property
	 * @param format the format associated with the type
	 * @param description the description of the property
	 * @param enums the possible values of the property
	 * @param example the example value of the property
	 * @param exampleArray the example array of the property
	 * @param items the items of the property
	 * @param $ref the reference to a existing property
	 */
	public Property(
	        final String id,
	        final String type,
	        final String format,
	        final String description,
	        final String[] enums,
	        final String example,
	        final String[] exampleArray,
	        final Items items,
	        final String $ref) {
		this.id = id;
		this.type = type;
		this.format = format;
		this.description = description;
		this.enums = enums;
		this.example = example;
		this.exampleArray = exampleArray;
		this.items = items;
		this.$ref = $ref;
	}

	/**
	 * Get the unique id of a property.
	 *
	 * @return the unique id of a property
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
		String items;
		try {
			items = this.items.asJson();
		} catch (NullPointerException itemsNull) {
			items = null;
		}
		return new String[] {
		        this.type,
		        this.format,
		        this.description,
		        Transform.arrayToJson(this.enums),
		        this.example,
		        Transform.arrayToJson(this.exampleArray),
		        items,
		        this.$ref};
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
