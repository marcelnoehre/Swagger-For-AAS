package templates;

import java.util.ArrayList;

import utils.Transform;

/**
 * 
 * Template for a path instance.
 * 
 * @author Marcel N&oumlhre
 *
 */
public class Request {
	private final String[] keys = new String[] {"tags", "summary", "description", "operationID", "consumes", "produces", "parameters", "responses", "deprecated"};
	private String requestType;
	private String[] tags;
	private String summary;
	private String description;
	private String operationID;
	private String[] consumes;
	private String[] produces;
	private Parameter[] parameters;
	private Response[] responses;
	private String deprecated;
	
	public Request(String requestType, String[] tags, String summary, String description, String operationID, String[] consumes, String[] produces, Parameter[] parameters, Response[] responses, String deprecated) {
		this.requestType = requestType;
		this.tags = tags;
		this.summary = summary;
		this.description = description;
		this.operationID = operationID;
		this.consumes = consumes;
		this.produces = produces;
		this.parameters = parameters;
		this.responses = responses;
		this.deprecated = deprecated == "true" ? "true" : null;
	}
	
	private String[] getValueArray() {
		ArrayList<String> parameterList = new ArrayList<String>();
		for(Parameter parameter: parameters) {
			parameterList.add(parameter.asJson());
		}
		ArrayList<String> responseList = new ArrayList<String>();
		for(Response response: responses) {
			responseList.add(response.asJson());
		}
		return new String[] {Transform.arrayToJson(this.tags), this.summary, this.description, this.operationID, Transform.arrayToJson(this.consumes), Transform.arrayToJson(this.produces), Transform.arrayToJson((String[]) parameterList.toArray()), Transform.arrayToJson((String[]) responseList.toArray()), this.deprecated};
	}
	
	public String getRequestType() {
		return this.requestType;
	}
    
	public String asJson() {
		return Transform.instanceToJson(keys, this.getValueArray());
	}
}