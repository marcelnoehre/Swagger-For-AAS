package templates;

import java.util.ArrayList;

import utils.Transform;

/**
 *
 * Template for a definition instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Definition {
	private final String[] keys =
	        new String[] {"type", "requiered", "properties"};
	private String id;
	private String type;
	private String[] requiered;
	private Property[] properties;

	/**
	 * Create a definition instance.
	 *
	 * @param id the unique id of the definition
	 * @param type the type of the definition
	 * @param requiered the requiered properties
	 * @param properties the list of properties
	 */
	public Definition(
	        String id,
	        String type,
	        String[] requiered,
	        Property[] properties) {
		this.id = id;
		this.type = type;
		this.requiered = requiered;
		this.properties = properties;
	}

	/**
	 * Get the unique definition id.
	 *
	 * @return the unique definition id
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
		ArrayList<String> propertyList = new ArrayList<String>();
		for (Property property : properties) {
			propertyList.add(property.asJson());
		}
		return new String[] {
		        this.type, Transform.arrayToJson(this.requiered),
		        Transform.propertiesToJson(properties)};
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
