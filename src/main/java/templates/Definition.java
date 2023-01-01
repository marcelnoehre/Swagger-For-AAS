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
	private final String[] keys = new String[] {"type", "requiered", "properties"};
	private String id;
	private String type;
	private String[] requiered;
	private Property[] properties;
	
	public Definition(String id, String type, String[] requiered, Property[] properties) {
		this.id = id;
		this.type = type;
		this.requiered = requiered;
		this.properties = properties;
	}
	
	public String getId() {
		return this.id;
	}
	
	private String[] getValueArray() {
		ArrayList<String> propertyList = new ArrayList<String>();
		for(Property property : properties) {
			propertyList.add(property.asJson());
		}
		return new String[] {this.type, Transform.arrayToJson(this.requiered), Transform.propertiesToJson(properties)};
	}
    
	public String asJson() {
    	return Transform.instanceToJson(keys, this.getValueArray());
	}
}
