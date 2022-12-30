package templates;

import org.json.simple.JSONObject;

import utils.Checks;
import utils.Transform;

/**
 * 
 * Template for a property instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Property {
	private final String[] keys = new String[] {"type", "format", "description", "enum", "example", "example", "items", "$ref"};
	private String id;
	private String type;
	private String format;
	private String description;
	private String[] enums;
	private String example;
	private String[] exampleArray;
	private Items items;
	private String $ref;
	
	public Property(String id, String type, String format, String description, String[] enums, String example, String[] exampleArray, Items items, String $ref) {
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
	
	private String[] getValueArray() {
		String items;
		try {
			items = this.items.asJson();
		} catch(NullPointerException itemsNull) {
			items = null;
		}
		return new String[] {this.type, this.format, this.description, Transform.arrayToJson(this.enums), this.example, Transform.arrayToJson(this.exampleArray), items, this.$ref};
	}
    
    @SuppressWarnings("unchecked")
	public String asJson() {
    	String property = Transform.instanceToJson(keys, this.getValueArray());
    	if(Checks.valueIsEmpty(property)) {
    		return null;
    	} else {
    		JSONObject container = new JSONObject();
    		container.put(id, property);
    		return container.toJSONString();
    	}		
	}
}
