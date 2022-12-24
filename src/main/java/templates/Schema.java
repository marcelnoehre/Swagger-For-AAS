package templates;

import utils.Transform;

/**
 * 
 * Template for a schema instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Schema {
	private final String[] keys = new String[] {"type", "items", "additionalProperties", "$ref"};		
	private String type;
	private Items items;
	private AdditionalProperties additionalProperties;
	private String $ref;
	
	public Schema(String type, Items items, AdditionalProperties additionalProperties, String $ref) {
		this.type = type;
		this.items = items;
		this.additionalProperties = additionalProperties;
		this.$ref = $ref;
	}
	
	private String[] getValueArray() {
		return new String[] {this.type, this.items.asJson(), this.additionalProperties.asJson(), this.$ref};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
