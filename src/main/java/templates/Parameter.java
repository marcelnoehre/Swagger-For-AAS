package templates;

import utils.Transform;

/**
 *
 * Template for a parameter instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Parameter {
	private final String[] keys = new String[] {
	        "name", "in", "description", "requiered",
	        "type", "format", "minimum", "maximum",
	        "items", "collectionFormat", "schema"};
	private String name;
	private String in;
	private String description;
	private String requiered;
	private String type;
	private String minimum;
	private String maximum;
	private String format;
	private Items items;
	private String collectionFormat;
	private Schema schema;

	/**
	 * Create a parameter instance.
	 *
	 * @param name the name of the parameter
	 * @param in the input variable
	 * @param description the description of the parameter
	 * @param requiered wheteher the parameter is requiered
	 * @param type the type of the parameter
	 * @param format the format associated with the type
	 * @param minimum the minimal numerical value
	 * @param maximum the maximal numerical value
	 * @param items the items of the parameter array
	 * @param collectionFormat the collection format of the parameter array
	 * @param schema the schema of the parameter
	 */
	public Parameter(
	        String name,
	        String in,
	        String description,
	        String requiered,
	        String type,
	        String format,
	        String minimum,
	        String maximum,
	        Items items,
	        String collectionFormat,
	        Schema schema) {
		this.name = name;
		this.in = in;
		this.description = description;
		this.requiered = requiered;
		this.type = type;
		this.format = format;
		this.minimum = minimum;
		this.maximum = maximum;
		this.items = items;
		this.collectionFormat = collectionFormat;
		this.schema = schema;
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
		String schema;
		try {
			schema = this.schema.asJson();
		} catch (NullPointerException schemaNull) {
			schema = null;
		}
		return new String[] {this.name, this.in,
		        this.description, this.requiered,
		        this.type, this.format, this.minimum,
		        this.maximum, items,
		        this.collectionFormat, schema};
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
