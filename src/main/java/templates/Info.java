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
	private final String[] keys =
	        new String[] {
	                "description",
	                "version",
	                "title",
	                "termsOfService",
	                "contact",
	                "license"};
	private String description;
	private String version;
	private String title;
	private String termsOfService;
	private Contact contact;
	private License license;

	/**
	 * Create a info instance.
	 *
	 * @param description the description of the aas
	 * @param version the version of the documentation
	 * @param title the title of the documentation
	 * @param termsOfService the terms of service
	 * @param contact the contact options
	 * @param license the license information
	 */
	public Info(
	        final String description,
	        final String version,
	        final String title,
	        final String termsOfService,
	        final Contact contact,
	        final License license) {
		this.description = description;
		this.version = version;
		this.title = title;
		this.termsOfService = termsOfService;
		this.contact = contact;
		this.license = license;
	}

	/**
	 * Get a array of all template values.
	 *
	 * @return array of all template values
	 */
	private String[] getValueArray() {
		String contact;
		try {
			contact = this.contact.asJson();
		} catch (NullPointerException contactNull) {
			contact = null;
		}
		String license;
		try {
			license = this.license.asJson();
		} catch (NullPointerException licenseNull) {
			license = null;
		}
		return new String[] {
		        this.description,
		        this.version,
		        this.title,
		        this.termsOfService,
		        contact,
		        license};
	}

	/**
	 * Get the instance as JSON string.
	 *
	 * @return json string of the instance
	 */
    public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}
