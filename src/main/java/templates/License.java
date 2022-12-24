package templates;

import utils.Transform;

/**
 * 
 * Template for a license instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class License {
	private final String[] keys = new String[] {"name", "url"};
	private String name;
	private String url;
	
	public License(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	private String[] getValueArray() {
		return new String[] {this.name, this.url};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
