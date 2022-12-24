package templates;

import utils.Transform;

/**
 * 
 * Template for a items instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Items {
	private final String[] keys = new String[] {"type", "enum", "default", "$ref"};
	private String type;
	private String[] enums;
	private String defaultValue;
	private String $ref;
	
	public Items(String type, String[] enums, String defaultValue, String $ref) {
		this.type = type;
		this.enums = enums;
		this.defaultValue = defaultValue;
		this.$ref = $ref;
	}
	
    private String[] getValueArray() {
		return new String[] {this.type, Transform.arrayToJson(this.enums), this.defaultValue, this.$ref};
	}
    
    public String asJson() {
		return Transform.instanceToJson(this.keys ,this.getValueArray());
	}
}
