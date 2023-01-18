package templates;

import org.json.simple.JSONObject;

/**
 *
 * Template for a route instance.
 *
 * @author Marcel N&oumlhre
 *
 */
public class Path {
    private String path;
    private Request[] requests;

    /**
     * Create a path instance.
     *
     * @param path the path of a request
     * @param requests the request that belongs to the path
     */
    public Path(String path, Request[] requests) {
        this.path = path;
        this.requests = requests;
    }

    /**
     * Get a array of all template values.
     *
     * @return array of all template values
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Get the instance as JSON string.
     *
     * @return json string of the instance
     */
    @SuppressWarnings("unchecked")
    public String asJson() {
        if (this.requests == null) {
            return null;
        } else {
            JSONObject container = new JSONObject();
            for (Request request : this.requests) {
                container.put(request.getRequestType(), request.asJson());
            }
            return container.toJSONString();
        }
    }
}
