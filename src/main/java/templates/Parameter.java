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
	private final String[] keys = new String[] {"name", "in", "description", "requiered", "type", "format", "minimum", "maximum", "items", "collectionFormat", "schema"};
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
	
	public Parameter(String name, String in, String description, String requiered, String type, String format, String minimum, String maximum, Items items, String collectionFormat, Schema schema) {
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
	
	private String[] getValueArray() {
		return new String[] {this.name, this.in, this.description, this.requiered, this.type, this.format, this.minimum, this.maximum, this.items.asJson(), this.collectionFormat, this.schema.asJson()};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
 }
