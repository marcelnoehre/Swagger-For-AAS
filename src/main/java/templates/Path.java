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
	
	public Path(String path, Request[] requests) {
		this.path = path;
		this.requests = requests;
	}
	
	public String getPath() {
		return this.path;
	}
    
    @SuppressWarnings("unchecked")
	public String asJson() {
    	JSONObject container = new JSONObject();
    	for(Request request : requests) {
    		container.put(request.getRequestType(), request.asJson());
    	}
    	return container.toJSONString();
	}
}
