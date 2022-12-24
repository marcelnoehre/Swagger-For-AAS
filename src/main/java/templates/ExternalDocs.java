package templates;

import utils.Transform;

/**
 * 
 * Template for a external docs instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class ExternalDocs {
	private final String[] keys = new String[] {"description", "url"};
	private String description;
	private String url;
	
	public ExternalDocs(String description, String url) {
		this.description = description;
		this.url = url;
	}
	
	private String[] getValueArray() {
		return new String[] {this.description, this.url};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
