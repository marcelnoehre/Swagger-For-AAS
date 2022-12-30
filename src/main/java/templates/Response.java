package templates;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import utils.Checks;
import utils.Transform;

/**
 * 
 * Template for a response instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Response {
	private String[] keys = new String[] {"description", "schema", "headers"};
	private String resultCode;
	private String description;
	private Schema schema;
	private Header[] headers;
	
	public Response(String resultCode, String description, Schema schema, Header[] headers) {
		this.resultCode = resultCode;
		this.description = description;
		this.schema = schema;
		this.headers = headers;
	}
	
	private String[] getValueArray() {
		ArrayList<String> headerList = new ArrayList<String>();
		for(Header header : headers) {
			String headerCheck;
			try {
				headerCheck = header.asJson();
			} catch(NullPointerException itemsNull) {
				headerCheck = null;
			}
			headerList.add(headerCheck);
		}
		String[] headerArr = new String[headerList.size()];
		int i = 0;
		for(String header : headerList) {
			headerArr[i] = header;
			i++;
		}
		String schema;
		try {
			schema = this.schema.asJson();
		} catch(NullPointerException schemaNull) {
			schema = null;
		}
		return new String[] {this.description, schema, Transform.arrayToJson(headerArr)};
	}
    
    @SuppressWarnings("unchecked")
	public String asJson() {
    	String response = Transform.instanceToJson(keys, this.getValueArray());
    	if(Checks.valueIsEmpty(response)) {
    		return null;
    	} else {
    		JSONObject container = new JSONObject();
    		container.put(this.resultCode, response);
    		return container.toJSONString();
    	}	
	}
}
