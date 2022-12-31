package templates;

import java.util.ArrayList;

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
	
	public String getResultCode() {
		return this.resultCode;
	}
	
	private String[] getValueArray() {
		ArrayList<String> headerList = new ArrayList<String>();
		String[] headerArr = new String[headerList.size()];
		if(this.headers != null) {
			for (Header header : headers) {
				String headerCheck;
				try {
					headerCheck = header.asJson();
				} catch(NullPointerException itemsNull) {
					headerCheck = null;
				}
				headerList.add(headerCheck);
			}
			int i = 0;
			for(String header : headerList) {
				headerArr[i] = header;
				i++;
			}
		}
		String schema;
		try {
			schema = this.schema.asJson();
		} catch(NullPointerException schemaNull) {
			schema = null;
		}
		return new String[] {this.description, schema, Transform.arrayToJson(headerArr)};
	}
    
	public String asJson() {
    	return Transform.instanceToJson(keys, this.getValueArray());
	}
}
