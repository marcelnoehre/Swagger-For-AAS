package templates;

import utils.Transform;

/**
 * 
 * Template for a additional properties instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class AdditionalProperties {
	private final String[] keys = new String[] {"type", "format"};
	private String type;
	private String format;
	
	public AdditionalProperties(String type, String format) {
		this.type = type;
		this.format = format;
	}
	
	private String[] getValueArray() {
		return new String[] {this.type, this.format};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
