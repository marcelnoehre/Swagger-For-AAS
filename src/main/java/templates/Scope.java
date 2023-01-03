package templates;

/**
 *
 * Template for a scope instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Scope {
	private String id;
	private String info;

	/**
	 * Create a scope instance.
	 *
	 * @param id the unique id of the scope
	 * @param info the information about the scope
	 */
	public Scope(final String id, final String info) {
		this.id = id;
		this.info = info;
	}

	/**
	 * Get the unique id of the scope.
	 *
	 * @return the unique id of the scope
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Get the information about the scope.
	 *
	 * @return the information about the scope
	 */
	public String getInfo() {
		return this.info;
	}
}
