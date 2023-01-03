package templates;

public class Route {
	private String tag;
	private String summary;
	private String type;
	private String path;
	private String[] extraParameter;

	/**
	 * Create a route instance.
	 *
	 * @param tag the tag to categorise the route
	 * @param summary the summary of the route
	 * @param type the type of the route endpoint
	 * @param path the path of the route
	 * @param extraParameter possible extra parameters
	 */
	public Route(
	        String tag,
	        String summary,
	        String type,
	        String path,
	        String[] extraParameter) {
		this.tag = tag;
		this.summary = summary;
		this.type = type;
		this.path = path;
		this.extraParameter = extraParameter;
	}

	/**
	 * Get the tag corresponding to the route.
	 *
	 * @return the tag corresponding to the route
	 */
	public String getTag() {
		return this.tag;
	}

	/**
	 * Get the summary of the route.
	 *
	 * @return the summary of the route
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * Get the type of the route endpoint.
	 *
	 * @return the type of the route endpoint
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Get the path of the route.
	 *
	 * @return the path of the route
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Get the possible extra parameters of the route.
	 *
	 * @return the possible extra parameters of the route
	 */
	public String[] getExtraParameter() {
		return this.extraParameter;
	}
}
