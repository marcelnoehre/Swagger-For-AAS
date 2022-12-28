package templates;

import org.json.simple.JSONObject;

import utils.Checks;
import utils.Transform;

/**
 * 
 * Template for a definition property instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class DefinitionProperty {
	private final String[] keys = new String[] {"type", "format"};
	private String id;
	private String type;
	private String format;
	
	public DefinitionProperty(String id, String type, String format) {
		this.id = id;
		this.type = type;
		this.format = format;
	}
	
	private String[] getValueArray() {
		return new String[] {this.type, this.format};
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
