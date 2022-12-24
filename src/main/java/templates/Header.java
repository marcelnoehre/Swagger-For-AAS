package templates;

import org.json.simple.JSONObject;

import utils.Checks;
import utils.Transform;

/**
 * 
 * Template for a header instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Header {
	private final String[] keys = new String[] {"type", "format", "description"};
	private String id;
	private String type;
	private String format;
	private String description;
	
	public Header(String id, String type, String format, String description) {
		this.id = id;
		this.type = type;
		this.format = format;
		this.description = description;
	}
	
	private String[] getValueArray() {
		return new String[] {this.type, this.format, this.description};
	}
    
    @SuppressWarnings("unchecked")
	public String asJson() {
    	String header = Transform.instanceToJson(keys, this.getValueArray());
    	if(Checks.valueIsEmpty(header)) {
    		return null;
    	} else {
    		JSONObject container = new JSONObject();
    		container.put(id, header);
    		return container.toJSONString();
    	}	
	}
}
