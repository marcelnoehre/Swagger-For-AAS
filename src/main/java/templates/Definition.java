package templates;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import utils.Checks;
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
	private String requiered;
	private Property[] properties;
	
	public Definition(String id, String type, String requiered, Property[] properties) {
		this.id = id;
		this.type = type;
		this.requiered = requiered;
		this.properties = properties;
	}
	
	private String[] getValueArray() {
		ArrayList<String> propertyList = new ArrayList<String>();
		for(Property property : properties) {
			propertyList.add(property.asJson());
		}
		return new String[] {this.type, this.requiered, Transform.arrayToJson((String[]) propertyList.toArray())};
	}
    
    @SuppressWarnings("unchecked")
	public String asJson() {
    	String definition = Transform.instanceToJson(keys, this.getValueArray());
    	if(Checks.valueIsEmpty(definition)) {
    		return null;
    	} else {
    		JSONObject container = new JSONObject();
    		container.put(id, definition);
    		return container.toJSONString();
    	}	
	}
}
