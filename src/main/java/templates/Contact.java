package templates;

import utils.Transform;

/**
 * 
 * Template for a contact instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Contact {
	private final String[] keys = new String[] {"email"};
	private String email;

	public Contact(String email) {
		this.email = email;
	}
	
	private String[] getValueArray() {
		return new String[] {this.email};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
