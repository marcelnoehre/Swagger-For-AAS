package templates;

import utils.Transform;

/**
 * 
 * Template for a tag instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Tag {
	private final String[] keys = new String[] {"name", "description", "externalDocs"};
	private String name;
	private String description;
	private ExternalDocs externalDocs;
	
	public Tag(String name, String description, ExternalDocs externalDocs) {
		this.name = name;
		this.description = description;
		this.externalDocs = externalDocs;
	}
	
	private String[] getValueArray() {
		return new String[] {this.name, this.description, this.externalDocs.asJson()};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
