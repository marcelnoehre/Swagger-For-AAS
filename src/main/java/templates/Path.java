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
	private Request request;
	
	public Path(String path, Request request) {
		this.path = path;
		this.request = request;
	}
	
	public String getPath() {
		return this.path;
	}
    
    @SuppressWarnings("unchecked")
	public String asJson() {
    	if(request == null) {
    		return null;
    	} else {
        	JSONObject container = new JSONObject();
        	container.put(request.getRequestType(), request.asJson());
        	return container.toJSONString();
    	}
	}
}
