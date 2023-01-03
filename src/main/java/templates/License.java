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

	/**
	 * Create a License instance.
	 *
	 * @param name the name of the license
	 * @param url the url to the license
	 */
	public License(String name, String url) {
		this.name = name;
		this.url = url;
	}

	/**
	 * Get a array of all template values.
	 *
	 * @return array of all template values
	 */
	private String[] getValueArray() {
		return new String[] {this.name, this.url};
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
