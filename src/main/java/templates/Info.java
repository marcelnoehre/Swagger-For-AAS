package templates;

import utils.Transform;

/**
 * 
 * Template for a info instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Info {
	private final String[] keys = new String[] {"description", "version", "title", "termsOfService", "contact", "license"};
	private String description;
	private String version;
	private String title;
	private String termsOfService;
	private Contact contact;
	private License license;
	
	public Info(String description, String version, String title, String termsOfService, Contact contact, License license) {
		this.description = description;
		this.version = version;
		this.title = title;
		this.termsOfService = termsOfService;
		this.contact = contact;
		this.license = license;
	}
	
	private String[] getValueArray() {
		return new String[] {this.description, this.version, this.title, this.termsOfService, this.contact.asJson(), this.license.asJson()};
	}
    
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}